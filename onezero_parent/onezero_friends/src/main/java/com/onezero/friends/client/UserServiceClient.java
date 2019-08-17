package com.onezero.friends.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author hao
 * @create 2019-07-28 ${TIM}
 */
@FeignClient(value = "ONEZERO-USER")
public interface UserServiceClient {

    /**
     * 增加关注
     * @param uid
     * @param num
     * @return
     */
    @RequestMapping(value = "/user/follow/incrfollow/{uid}/{num}", method = RequestMethod.PUT)
    Result incrfollow(@PathVariable(value = "uid") String uid, @PathVariable(value = "num") int num);

    /**
     * 减少关注
     * @param uid
     * @param num
     * @return
     */
    @RequestMapping(value = "/user/follow/disfollow/{uid}/{num}", method = RequestMethod.PUT)
    Result disfollow(@PathVariable(value = "uid") String uid, @PathVariable(value = "num") int num);

    /**
     * 增加粉丝
     * @param uid
     * @param num
     * @return
     */
    @RequestMapping(value = "/user/follow/incrfans/{uid}/{num}", method = RequestMethod.PUT)
    Result incrfans(@PathVariable(value = "uid") String uid, @PathVariable(value = "num") int num);

    /**
     * 减少粉丝
     * @param uid
     * @param num
     * @return
     */
    @RequestMapping(value = "/user/follow/disfans/{uid}/{num}", method = RequestMethod.PUT)
    Result disfans(@PathVariable(value = "uid") String uid, @PathVariable(value = "num") int num);
}