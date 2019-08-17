package com.onezero.friends.dao;

import com.onezero.friends.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hao
 * @create 2019-07-29 ${TIM}
 */
public interface NoFriendRepistory extends JpaRepository<NoFriend,NoFriend> {

    NoFriend findByUseridAndFriendid(String userid,String friendid);

}
