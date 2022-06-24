package com.twt.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twt.entity.Notice;
import com.twt.mapper.NoticeMapper;
import com.twt.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twt.vo.HomeNoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sxd
 * @since 2022-06-20
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public List<HomeNoticeVO> getHomeNotices() {
        LambdaQueryWrapper<Notice> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Notice::getCreatedAt).last("limit 3");
        List<Notice> noticeList = noticeMapper.selectList(lambdaQueryWrapper);
        return copyList(noticeList);
    }

    private List<HomeNoticeVO> copyList(List<Notice> notices) {
        List<HomeNoticeVO> homeNoticeList = new ArrayList<>();
        for (Notice notice : notices) {
            HomeNoticeVO  homeNotice = new HomeNoticeVO();
            BeanUtil.copyProperties(notice,homeNotice);
            homeNoticeList.add(homeNotice);
        }
        return homeNoticeList;
    }
}
