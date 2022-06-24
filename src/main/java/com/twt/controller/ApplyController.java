package com.twt.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twt.dto.ApplyDto;
import com.twt.entity.Apply;
import com.twt.service.ApplyService;
import com.twt.utils.Result;
import com.twt.vo.ApplierVO;
import com.twt.vo.ApplyUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
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
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    ApplyService applyService;

    @PostMapping
    public Result commitApply(@RequestBody ApplyDto applyDto){
        return applyService.commitApply(applyDto);
    }

    @GetMapping
    public Result getApplyUser(){
        List<ApplyUserVO> applyUserVOList = applyService.getApplyUser();
        return Result.success(applyUserVOList);
    }

    @PostMapping("/admit")
    public Result admitUser(@RequestParam("uid") Integer uid){
        Apply applyOld = applyService.getOne(new LambdaQueryWrapper<Apply>().eq(Apply::getUid, uid));
        applyOld.setAdmit(1);
        applyOld.setUpdatedAt(LocalDateTime.now());
        applyService.updateById(applyOld);
        return Result.success(null);
    }

    @GetMapping("/user/{id}")
    public Result getApplierDetail(@PathVariable("id") Integer id){
        Apply apply = applyService.getOne(new LambdaQueryWrapper<Apply>().eq(Apply::getUid, id));
        ApplierVO applierVO = new ApplierVO();
        BeanUtil.copyProperties(apply,applierVO);
        return Result.success(applierVO);
    }


}

