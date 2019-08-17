package com.onezero.friends.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author hao
 * @create 2019-07-28 ${TIM}
 */
@Entity
@Table(name = "tb_friend")
@IdClass(Friend.class)
public class Friend implements Serializable{
    @Id
    private String userid;
    @Id
    private String friendid;

    private String  islike;

    public Friend() {
    }

    public Friend(String userid, String friendid, String islike) {
        this.userid = userid;
        this.friendid = friendid;
        this.islike = islike;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFriendid() {
        return friendid;
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }

    public String getIslike() {
        return islike;
    }

    public void setIslike(String islike) {
        this.islike = islike;
    }
}
