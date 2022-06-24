package com.twt.service;

import com.twt.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.twt.vo.HomeNoticeVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sxd
 * @since 2022-06-20
 */
public interface NoticeService extends IService<Notice> {

    List<HomeNoticeVO> getHomeNotices();
}
