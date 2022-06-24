package com.twt.service;

import com.twt.dto.ApplyDto;
import com.twt.entity.Apply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.twt.utils.Result;
import com.twt.vo.ApplyUserVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sxd
 * @since 2022-06-20
 */
public interface ApplyService extends IService<Apply> {

    Result commitApply(ApplyDto applyDto);

    List<ApplyUserVO> getApplyUser();
}
