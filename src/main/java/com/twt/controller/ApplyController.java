package com.twt.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twt.dto.ApplyDto;
import com.twt.entity.Apply;
import com.twt.service.ApplyService;
import com.twt.utils.Result;
import com.twt.vo.ApplierVO;
import com.twt.vo.ApplyUserVO;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 史熹东
 * @since 2022-06-20
 */
@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    ApplyService applyService;

    @PostMapping
    @RequiresRoles("user")
    public Result commitApply(@RequestBody ApplyDto applyDto){
        return applyService.commitApply(applyDto);
    }

    @GetMapping
    @RequiresRoles("admin")
    public Result getApplyUser(){
        List<ApplyUserVO> applyUserVOList = applyService.getApplyUser();
        return Result.success(applyUserVOList);
    }

    @PostMapping("/admit")
    @RequiresRoles("admin")
    public Result admitUser(@RequestParam("uid") Integer uid){
        Apply applyOld = applyService.getOne(new LambdaQueryWrapper<Apply>().eq(Apply::getUid, uid));
        applyOld.setAdmit(1);
        applyOld.setUpdatedAt(LocalDateTime.now());
        applyService.updateById(applyOld);
        return Result.success(null);
    }

    @PostMapping("/unAdmit")
    @RequiresRoles("admin")
    public Result unAdmitUser(@RequestParam("uid") Integer uid){
        Apply applyOld = applyService.getOne(new LambdaQueryWrapper<Apply>().eq(Apply::getUid, uid));
        applyOld.setAdmit(0);
        applyOld.setUpdatedAt(LocalDateTime.now());
        applyService.updateById(applyOld);
        return Result.success(null);
    }

    @GetMapping("/user/{id}")
    @RequiresRoles("admin")
    public Result getApplierDetail(@PathVariable("id") Integer id){
        Apply apply = applyService.getOne(new LambdaQueryWrapper<Apply>().eq(Apply::getUid, id));
        ApplierVO applierVO = new ApplierVO();
        BeanUtil.copyProperties(apply,applierVO);

        if (apply.getGender()==1){
            applierVO.setGender("男");
        }else{
            applierVO.setGender("女");
        }

        if (apply.getPartyWill()==1){
            applierVO.setPartyWill("是");
        }else{
            applierVO.setPartyWill("否");
        }

        if (apply.getAdmit()==1){
            applierVO.setAdmit("已录取");
        }else{
            applierVO.setAdmit("未录取");
        }

        return Result.success(applierVO);
    }

    @GetMapping("/admit")
    @RequiresAuthentication
    public Result getUserAdmit(@RequestParam Integer uid){
        Apply applier = applyService.getOne(new LambdaQueryWrapper<Apply>().eq(Apply::getUid, uid));
        if (applier.getAdmit()==1){
            return Result.success("已录取");
        }else{
            return Result.success("未录取");
        }
    }


}

