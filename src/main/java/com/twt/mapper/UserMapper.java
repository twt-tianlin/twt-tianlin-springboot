package com.twt.mapper;

import com.twt.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sxd
 * @since 2022-06-20
 */
public interface UserMapper extends BaseMapper<User> {
    String getUserRole(Integer id);
}
