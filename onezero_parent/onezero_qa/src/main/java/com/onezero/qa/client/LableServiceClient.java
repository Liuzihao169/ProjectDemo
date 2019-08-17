package com.onezero.qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author hao
 * @create 2019-07-28 ${TIM}
 */
@FeignClient(value = "ONEZERO-BASE")
public interface LableServiceClient {

    @GetMapping("/label/{labelId}")
    public Result findById(@PathVariable(value = "labelId") String labelId);
}
