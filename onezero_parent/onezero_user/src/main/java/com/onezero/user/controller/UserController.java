package com.onezero.user.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.onezero.user.pojo.User;
import com.onezero.user.service.UserService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import util.JwtUtil;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
    private JwtUtil jwtUtil;

	@RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody User user){
        User login = userService.login(user);

        if (login == null) {
            return new Result(false,StatusCode.LOGINERROR,"用户名或密码错误");
        }
        //登陆成功之后 封装令牌
        String token = jwtUtil.createJWT(login.getId(), login.getMobile(), "user");
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("roles","user");
        return new Result(false,StatusCode.OK,"登陆成功",map);

    }	/**
	 * 发送验证码
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/sendsms/{mobile}",method = RequestMethod.POST)
	public Result Sendsms(@PathVariable(value = "mobile") String mobile){
		userService.sendsms(mobile);
		return new Result(true,StatusCode.OK,"发送成功");
	}

	@RequestMapping(value = "/register/{code}",method = RequestMethod.POST)
	public Result regist(@PathVariable String code,@RequestBody User user){
		userService.regist(code,user);
		return new Result(true,StatusCode.OK,"注册成功");
	}
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",userService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param user
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody User user  ){
		userService.add(user);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.update(user);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		userService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}


	@RequestMapping(value = "/follow/incrfollow/{uid}/{num}",method = RequestMethod.PUT)
    public Result incrfollow(@PathVariable(value = "uid") String uid,@PathVariable(value = "num") int num){
        userService.incrfollow(uid,num);
	    return  new Result(true,StatusCode.OK,"增加关注成功");
    }

    @RequestMapping(value = "/follow/disfollow/{uid}/{num}",method = RequestMethod.PUT)
    public Result disfollow(@PathVariable(value = "uid") String uid,@PathVariable(value = "num") int num){
        userService.disfollow(uid,num);
        return  new Result(true,StatusCode.OK,"减少关注成功");
    }

    @RequestMapping(value = "/follow/incrfans/{uid}/{num}",method = RequestMethod.PUT)
    public Result incrfans(@PathVariable(value = "uid") String uid,@PathVariable(value = "num") int num){
        userService.incrfans(uid,num);
        return  new Result(true,StatusCode.OK,"增加粉丝成功");
    }

    @RequestMapping(value = "/follow/disfans/{uid}/{num}",method = RequestMethod.PUT)
    public Result disfans(@PathVariable(value = "uid") String uid,@PathVariable(value = "num") int num){
        userService.disfans(uid,num);
        return  new Result(true,StatusCode.OK,"减少粉丝成功");
    }
}
