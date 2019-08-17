package com.onezero.friends.controller;

import com.onezero.friends.service.FriendService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hao
 * @create 2019-07-28 ${TIM}
 */
@RequestMapping("/friend")
@RestController
public class FriendController {

    @Autowired
    private FriendService friendService;

    /**
     * 操作好友 添加新的好友 或者设置为不喜欢好友
     * @param friendid
     * @param type
     * @return
     */
    @RequestMapping(value = "/like/{friendid}/{type}",method = RequestMethod.PUT)
    public Result addFriend(
            @PathVariable(value="friendid") String friendid,
            @PathVariable(value="type")String type){

        Result result = friendService.operateFriend(friendid, type);
        return result;
    }
}
