package com.onezero.user.service;

import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import com.onezero.user.dao.UserDao;
import com.onezero.user.pojo.User;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Transactional
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private IdWorker idWorker;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
    private BCryptPasswordEncoder bCrypt;

    @Autowired
    private HttpServletRequest request;

    /**
     * 登陆
     * @param user
     * @return
     */
	public User login(User user){
        User byMobile = userDao.findByMobile(user.getMobile());
        if(byMobile!=null&&bCrypt.matches(user.getPassword(),byMobile.getPassword())) return byMobile;
        return null;
    }

	/**
	 * 用户注册
	 */
	public void regist(String code, User user) {
		String redisCode = (String) redisTemplate.opsForValue().get("randCode:" + user.getMobile());

		if (StringUtils.isEmpty(redisCode)) throw new RuntimeException("请先获取验证码");
		if(!redisCode.equals(code)) throw new RuntimeException("输入正确验证码");

		//维护user数据完整
		user.setFollowcount(0);//关注数
		user.setFanscount(0);//粉丝数
		user.setOnline(0L);//在线时长
		user.setRegdate(new Date());//注册日期
		user.setUpdatedate(new Date());//更新日期
		user.setLastdate(new Date());//最后登陆日期
		user.setId(idWorker.nextId()+"");//id

        //加密密码
        user.setPassword(bCrypt.encode(user.getPassword()));
		userDao.save(user);
	}
	/**
	 * 发送手机验证码
	 * @param mobile
	 */
	public void sendsms(String mobile) {
		//生成验证码
		final String randCode = RandomStringUtils.randomNumeric(6);
		Map<String,Object>map = new HashMap<>();
		map.put("mobile",mobile);
		map.put("randCode",randCode);
		//缓存中存储一份
		redisTemplate.opsForValue().set("randCode:"+mobile,randCode,5, TimeUnit.HOURS);

		//消息队列中发送一份 验证码和手机号的信息 用于阿里云发送短信
//		rabbitTemplate.convertAndSend("smsDirect","gosms",map);
		//用户发送一份 使用阿里云

		//控制台打印一份用于测试
		System.out.println("【验证码】"+randCode);
	}
	/**
	 * 查询全部列表
	 * @return
	 */
	public List<User> findAll() {
		return userDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<User> findSearch(Map whereMap, int page, int size) {
		Specification<User> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return userDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<User> findSearch(Map whereMap) {
		Specification<User> specification = createSpecification(whereMap);
		return userDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public User findById(String id) {
		return userDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param user
	 */
	public void add(User user) {
		user.setId( idWorker.nextId()+"" );
		userDao.save(user);
	}

	/**
	 * 修改
	 * @param user
	 */
	public void update(User user) {
		userDao.save(user);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
	    // 拥有admin权限才可以删除
        Object admin = request.getAttribute("admin");
        if (admin == null) {
            throw new RuntimeException("权限不足");
        }
		userDao.deleteById(id);
	}

    /**
     * 增加关注
     * @param uid
     * @param num
     */
	public void incrfollow(String uid ,int num){
        userDao.incrFollow(uid,num);
    }

    /**
     * 减少关注
     * @param uid
     * @param num
     */
	public void disfollow(String uid ,int num){
	    userDao.disFollow(uid,num);
    }

    /**
     * 增加粉丝
     * @param uid
     * @param num
     */
	public void incrfans(String uid ,int num){
	    userDao.incrFans(uid,num);
    }

    /**
     * 减少粉丝
     * @param uid
     * @param num
     */
	public void disfans(String uid ,int num){
	    userDao.disFans(uid,num);
    }

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<User> createSpecification(Map searchMap) {

		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 手机号码
                if (searchMap.get("mobile")!=null && !"".equals(searchMap.get("mobile"))) {
                	predicateList.add(cb.like(root.get("mobile").as(String.class), "%"+(String)searchMap.get("mobile")+"%"));
                }
                // 密码
                if (searchMap.get("password")!=null && !"".equals(searchMap.get("password"))) {
                	predicateList.add(cb.like(root.get("password").as(String.class), "%"+(String)searchMap.get("password")+"%"));
                }
                // 昵称
                if (searchMap.get("nickname")!=null && !"".equals(searchMap.get("nickname"))) {
                	predicateList.add(cb.like(root.get("nickname").as(String.class), "%"+(String)searchMap.get("nickname")+"%"));
                }
                // 性别
                if (searchMap.get("sex")!=null && !"".equals(searchMap.get("sex"))) {
                	predicateList.add(cb.like(root.get("sex").as(String.class), "%"+(String)searchMap.get("sex")+"%"));
                }
                // 头像
                if (searchMap.get("avatar")!=null && !"".equals(searchMap.get("avatar"))) {
                	predicateList.add(cb.like(root.get("avatar").as(String.class), "%"+(String)searchMap.get("avatar")+"%"));
                }
                // E-Mail
                if (searchMap.get("email")!=null && !"".equals(searchMap.get("email"))) {
                	predicateList.add(cb.like(root.get("email").as(String.class), "%"+(String)searchMap.get("email")+"%"));
                }
                // 兴趣
                if (searchMap.get("interest")!=null && !"".equals(searchMap.get("interest"))) {
                	predicateList.add(cb.like(root.get("interest").as(String.class), "%"+(String)searchMap.get("interest")+"%"));
                }
                // 个性
                if (searchMap.get("personality")!=null && !"".equals(searchMap.get("personality"))) {
                	predicateList.add(cb.like(root.get("personality").as(String.class), "%"+(String)searchMap.get("personality")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}


}
