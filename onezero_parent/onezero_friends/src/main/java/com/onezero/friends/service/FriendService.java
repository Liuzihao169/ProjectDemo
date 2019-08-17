package com.onezero.friends.service;

import com.onezero.friends.client.UserServiceClient;
import com.onezero.friends.dao.FriendRepistory;
import com.onezero.friends.dao.NoFriendRepistory;
import com.onezero.friends.pojo.Friend;
import com.onezero.friends.pojo.NoFriend;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hao
 * @create 2019-07-28 ${TIM}
 */
@Transactional
@Service
public class FriendService {

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;

    @Qualifier("myredisTemplate")
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private  FriendRepistory friendDao;

    @Autowired
    private NoFriendRepistory noFriendDo;

    public Result operateFriend(String friendid,String type){
        //判断是否有权限
        String token = (String) request.getAttribute("user");
        if (token == null) {
            return new Result(false, StatusCode.ACCESSERROR,"权限不足");
        }
        //获得当前用户的id
        Claims claims = jwtUtil.parseJWT(token);
        String id = claims.getId();

        //喜欢操作
        if (type.equals("1")){
            //判断是否有重复操作
            if(redisTemplate.opsForValue().get("id:"+id+":"+friendid)!=null){
                return new Result(false,StatusCode.ERROR,"不能重复喜欢");
            }
            // 0 单向喜欢 1 双向喜欢
            friendDao.save(new Friend(id,friendid,"0"));

            //更新到缓存中
            redisTemplate.opsForValue().set("id:"+id+":"+friendid,id+friendid);

            //判断对方是否喜欢你
            if(friendDao.findByUseridAndFriendidAndIslike(friendid,id,"0")!=null){
                //把你的islike设置为1
                friendDao.updateLike(id,friendid,"1");
                //把对方的islike设置为1
                friendDao.updateLike(friendid,id,"1");
            }

            //你的关注人+1
            userServiceClient.incrfollow(id,1);
            //你喜欢的人 喜欢+1
            userServiceClient.incrfans(friendid,1);

            return new Result(true,StatusCode.OK,"操作成功");

            //不喜欢操作
        }else if(type.equals("2")){
            //先判断不喜欢列表中是否有数据
            if(noFriendDo.findByUseridAndFriendid(id,friendid)!=null){
                return new Result(false,StatusCode.ERROR,"不能重复不喜欢好友");
            }

            //像非好友表中插入数据
            noFriendDo.save(new NoFriend(id,friendid));

            //如果该用户曾经喜欢 正要用户删除的用户  那么删除喜欢表的记录
            friendDao.deleteAllByUseridAndFriendid(id,friendid);

            //删除缓存中喜欢的记录
            redisTemplate.opsForValue().set("id:"+id+":"+friendid,null);

            //如果要删除的用户与该用户互相喜欢 那么修改该记录为0
            friendDao.updateLike(friendid,id,"0");

            //该用户的关注数目 -1
            userServiceClient.disfollow(id,1);

            //被不喜欢的用户 喜欢数目 -1
            userServiceClient.disfans(friendid,1);


        }else{
            return new Result(false,StatusCode.ERROR,"类型错误");
        }


         return new Result(true,StatusCode.OK,"操作成功");
    }
}
