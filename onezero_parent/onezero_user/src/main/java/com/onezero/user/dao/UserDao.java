package com.onezero.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.onezero.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{
	//通过手机号查询用户
    User findByMobile(String mobile);

    @Modifying
    @Query(value = "update User u set u.followcount = u.followcount +?2 where u.id = ?1")
    void incrFollow(String uid,int num);

    @Modifying
    @Query(value = "update User u set u.followcount = u.followcount -?2 where u.id = ?1")
    void disFollow(String uid,int num);

    @Modifying
    @Query(value = "update User u set u.fanscount = u.fanscount +?2 where u.id = ?1")
    void incrFans(String uid,int num);

    @Modifying
    @Query(value = "update User u set u.fanscount = u.fanscount -?2 where u.id = ?1")
    void disFans(String uid,int num);
}
