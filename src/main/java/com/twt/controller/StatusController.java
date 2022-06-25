package com.twt.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twt.entity.Status;
import com.twt.service.StatusService;
import com.twt.utils.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 史熹东
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
    @RequiresRoles("admin")
    public Result openApplySystem(){
        Status status = statusService.getOne(new LambdaQueryWrapper<>());
        status.setApply(1);
        statusService.updateById(status);
        return Result.success(null);
    }

    @PutMapping("closeApplySystem")
    @RequiresRoles("admin")
    public Result closeApplySystem(){
        Status status = statusService.getOne(new LambdaQueryWrapper<>());
        status.setApply(0);
        statusService.updateById(status);
        return Result.success(null);
    }

    @PutMapping("openConfirmSystem")
    @RequiresRoles("admin")
    public Result openConfirmSystem(){
        Status status = statusService.getOne(new LambdaQueryWrapper<>());
        status.setConfirm(1);
        statusService.updateById(status);
        return Result.success(null);
    }

    @PutMapping("closeConfirmSystem")
    @RequiresRoles("admin")
    public Result closeConfirmSystem(){
        Status status = statusService.getOne(new LambdaQueryWrapper<>());
        status.setConfirm(0);
        statusService.updateById(                                                                                                                                                                                                                                                                                                                                                                                                                                                                           status);
        return Result.success(null);
    }

}

