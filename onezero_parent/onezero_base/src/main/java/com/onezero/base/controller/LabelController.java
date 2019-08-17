package com.onezero.base.controller;

import com.onezero.base.entity.Label;
import com.onezero.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author hao
 * @create 2019-07-13 ${TIM}
 */
@RestController
@CrossOrigin
public class LabelController {

    @Autowired
    private LabelService labelService;

    @Autowired
    private HttpServletRequest request;

    // 查询所有
    @GetMapping("/label/list")
    public Result findAll(){
        String token = request.getHeader("token");
        System.out.println("头部token信息=====>"+token);
        List<Label> all = labelService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",all);
    }

    // 通过id查询
    @GetMapping("/label/{labelId}")
    public Result findById(@PathVariable(value = "labelId") String labelId){
        Label label = labelService.findById(labelId);
        return new Result(true,StatusCode.OK,"查询成功",label);
    }

    // 新增标签
    @PostMapping("/label")
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true,StatusCode.OK,"新增成功");
    }

    //删除标签
    @DeleteMapping("/label/{labelId}")
    public Result deleteById(@PathVariable(value = "labelId") String labelId){
        labelService.deleteById(labelId);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    //修改标签
    @PutMapping("/label/{labelId}")
    public Result update(@PathVariable(value = "labelId") String labelId,@RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    //标签查询
    @PostMapping("/label/search")
    public Result findSearch(@RequestBody Label label){
        List<Label> search = labelService.findSearch(label);
        return new Result(true,StatusCode.OK,"查询成功",search);
    }

    //分页
    @PostMapping("/label/search/{page}/{size}")
        public Result pageQuery(@RequestBody Label label,@PathVariable(value = "page") int page,@PathVariable(value = "size") int size){
        PageResult<Label> pageResult = labelService.pageQuery(label, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

}
