package com.twt.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twt.entity.Status;
import com.twt.service.StatusService;
import com.twt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sxd
 * @since 2022-06-20
 */
@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    StatusService statusService;

    @GetMapping
    public Result getStatus(){
        return Result.success(statusService.getOne(new LambdaQueryWrapper<>()));
    }

    @PutMapping("openApplySystem")
    public Result openApplySystem(){
        Status status = statusService.getOne(new LambdaQueryWrapper<>());
        status.setApply(1);
        statusService.updateById(status);
        return Result.success(null);
    }

    @PutMapping("closeApplySystem")
    public Result closeApplySystem(){
        Status status = statusService.getOne(new LambdaQueryWrapper<>());
        status.setApply(0);
        statusService.updateById(status);
        return Result.success(null);
    }

    @PutMapping("openConfirmSystem")
    public Result openConfirmSystem(){
        Status status = statusService.getOne(new LambdaQueryWrapper<>());
        status.setConfirm(1);
        statusService.updateById(status);
        return Result.success(null);
    }

    @PutMapping("closeConfirmSystem")
    public Result closeConfirmSystem(){
        Status status = statusService.getOne(new LambdaQueryWrapper<>());
        status.setConfirm(0);
        statusService.updateById(                                                                                                                                                                                                                                                                                                                                                                                                                                                                           status);
        return Result.success(null);
    }

}

