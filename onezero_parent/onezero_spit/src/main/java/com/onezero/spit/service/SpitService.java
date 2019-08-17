package com.onezero.spit.service;

import com.onezero.spit.dao.SpitDao;
import com.onezero.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @author hao
 * @create 2019-07-18 ${TIM}
 * 吐槽业务层
 */
@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker worker;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 添加吐槽
     * @param spit
     * 分析:有一些数据要初始化 判断是否是吐槽里的吐槽
     * 是：父comment+1
     * 否：正常添加一个吐槽
     */
    public void add(Spit spit){
        spit.set_id( worker.nextId()+"" );
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        spit.set_id(worker.nextId()+"");
        if(spit.getParentid()!=null&&spit.getParentid().length()>0){
            //父节点吐槽回复数目+1
            mongoTemplate.updateMulti(new Query().addCriteria(Criteria.where("_id").is(spit.getParentid())),
                    new Update().inc("comment",1),"spit");
        }
        spitDao.save(spit);
    }

    /**
     * 查找所有
     * @return
     */
    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    /**
     * 通过id查找
     * @param id
     * @return
     */
    public Spit findById(String id){
        return spitDao.findById(id).get();
    }

    /**
     * 更新操作
     * @param spit
     */
    public void update(Spit spit){
        spitDao.save(spit);
    }

    /**
     * 删除
     * @param id
     */
    public void delteById(String id){
        spitDao.deleteById(id);
    }

    /**
     * 根据分页查找
     * @param page
     * @param size
     * @return
     */
    public Page<Spit>findAll(int page,int size){
        return  spitDao.findAll(PageRequest.of(page-1, size));
    }

    /**
     * 根据父节点查找
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    public Page<Spit> findByParentId(String parentid,int page,int size){
        return spitDao.findByParentid(parentid,PageRequest.of(page-1,size));
    }

    /**
     * 点赞+1 mongo的null
     * @param id
     */
    public void updateThumbup(String id){
        mongoTemplate.updateMulti(new BasicQuery("{_id:\""+id+"\"}"),new BasicUpdate("{$inc:{visits:NumberInt(1)}}"),"spit");
    }
}
