package com.onezero.friends.dao;

import com.onezero.friends.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author hao
 * @create 2019-07-28 ${TIM}
 */
public interface FriendRepistory  extends JpaRepository<Friend,Friend>{
    Friend findByUseridAndFriendidAndIslike(String userid,String friendid, String islike);
    @Modifying
    @Query("update Friend f set f.islike=?3 where f.userid=?1 and f.friendid=?2")
    void updateLike(String userid,String friendid,String islike);

    void deleteAllByUseridAndFriendid(String userid,String friendid);
}
