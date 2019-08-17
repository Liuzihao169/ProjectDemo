package com.onezero.spit.controller;

import com.onezero.spit.pojo.Spit;
import com.onezero.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hao
 * @create 2019-07-18 ${TIM}
 * 吐槽控制层
 */
@RequestMapping("/spit")
@RestController
public class SpitController {

    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result list(){
        List<Spit> all = spitService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",all);
    }

    /**
     * 查询byid
     * @param spitId
     * @return
     */
    @RequestMapping(value = "/{spitId}",method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "spitId") String spitId){
        Spit byId = spitService.findById(spitId);
        return new Result(true,StatusCode.OK,"查询成功",byId);
    }

    /**
     * 分页+查询
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.GET)
    public Result findserarch(@PathVariable int page,@PathVariable int size){
        Page<Spit> all = spitService.findAll(page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(all.getTotalElements(),all.getContent()));
    }
    /**
     * 添加
     * @param spit
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result findById(@RequestBody Spit spit){
        spitService.add(spit);
        return new Result(true,StatusCode.OK,"添加");
    }

    /**
     * 修改
     * @param spitId
     * @param spit
     * @return
     */
    @RequestMapping(value = "/{spitId}",method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "spitId") String spitId,@RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除通过id
     * @param spitId
     * @return
     */
    @RequestMapping(value = "/{spitId}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable(value = "spitId") String spitId){
        spitService.delteById(spitId);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 根据父 ID 查询子id
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/comment/{parentid}/{page}/{size}",method = RequestMethod.GET)
    public Result findByparentId(@PathVariable(value = "parentid") String parentid,@PathVariable int page,@PathVariable int size){
        Page<Spit> all = spitService.findByParentId(parentid,page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(all.getTotalElements(),all.getContent()));
    }

    /**
     * 点赞功能
     * @param id
     * @return
     * 业务需求：每个用户只能点赞一次
     * 解决方案：每次点赞从redis查看点赞辨识，点赞之后就存在辨识
     */
    @RequestMapping(value="thumbup/{spitId}",method = RequestMethod.PUT)
    public Result updateThumbup(@PathVariable(value = "spitId") String id ){
        Object ob = redisTemplate.opsForValue().get("tensqusre_Thumbup001"+id);
        if (ob != null) {
            return new Result(false,StatusCode.REPERROR,"重复点赞");
        }
        spitService.updateThumbup(id);
        redisTemplate.opsForValue().set("tensqusre_Thumbup001"+id,"1");
        return  new Result(true,StatusCode.OK,"点赞成功");
    }
}
