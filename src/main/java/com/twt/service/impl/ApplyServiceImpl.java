package com.twt.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twt.dto.ApplyDto;
import com.twt.entity.Apply;
import com.twt.entity.Notice;
import com.twt.mapper.ApplyMapper;
import com.twt.service.ApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twt.utils.Result;
import com.twt.vo.ApplyUserVO;
import com.twt.vo.HomeNoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sxd
 * @since 2022-06-20
 */
@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements ApplyService {

    @Autowired
    ApplyMapper applyMapper;

    @Override
    public Result commitApply(ApplyDto applyDto) {
        // 新建Apply对象 并拷贝属性
        Apply apply = new Apply();
        BeanUtil.copyProperties(applyDto,apply);
        apply.setUpdatedAt(LocalDateTime.now());
        apply.setYear(LocalDateTime.now().getYear());

        Apply oldApply = applyMapper.selectOne(new LambdaQueryWrapper<Apply>().eq(Apply::getUid, apply.getUid()));
        if (oldApply!=null){
            applyMapper.update(apply,new LambdaQueryWrapper<Apply>().eq(Apply::getUid,apply.getUid()));
        }else {
            apply.setCreatedAt(LocalDateTime.now());
            applyMapper.insert(apply);
        }


        return Result.success("操作成功");
    }

    @Override
    public List<ApplyUserVO> getApplyUser() {
        LambdaQueryWrapper<Apply> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<Apply> applies = applyMapper.selectList(lambdaQueryWrapper);
        return copyList(applies);
    }

    private List<ApplyUserVO> copyList(List<Apply> applies) {
        List<ApplyUserVO> applyUserVOList = new ArrayList<>();
        for (Apply apply : applies) {
            ApplyUserVO  applyUserVO = new ApplyUserVO();
            BeanUtil.copyProperties(apply,applyUserVO);
            // 转化性别
            if (apply.getGender()==1){
                applyUserVO.setGender("男");
            }else{
                applyUserVO.setGender("女");
            }
            // 转化入党意愿
            if (apply.getPartyWill()==1){
                applyUserVO.setPartyWill("是");
            }else{
                applyUserVO.setPartyWill("否");
            }
            applyUserVOList.add(applyUserVO);
        }
        return applyUserVOList;
    }
}
