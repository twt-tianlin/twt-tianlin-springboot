package com.twt.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.twt.dto.ConfirmDto;
import com.twt.entity.Confirm;
import com.twt.service.ConfirmService;
import com.twt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sxd
 * @since 2022-06-20
 */
@RestController
@RequestMapping("/confirm")
public class ConfirmController {

    @Autowired
    ConfirmService confirmService;

    @PostMapping
    public Result commitConfirm(@RequestBody ConfirmDto confirmDto){
        Confirm confirm = new Confirm();
        BeanUtil.copyProperties(confirmDto,confirm);
        confirm.setBedNeed(StrUtil.strip(confirmDto.getBedNeed().toString(),"[]"));
        confirm.setCreatedAt(LocalDateTime.now());
        confirm.setUpdatedAt(LocalDateTime.now());
        confirm.setYear(new Date().getYear());
        confirmService.save(confirm);
        return Result.success(null);
    }

}

