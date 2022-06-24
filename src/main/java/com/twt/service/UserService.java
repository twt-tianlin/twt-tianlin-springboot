package com.twt.service;

import com.twt.dto.RegisterDto;
import com.twt.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sxd
 * @since 2022-06-20
 */
public interface UserService extends IService<User> {

    User register(RegisterDto registerDto);

    String getUserRole(Integer id);
}
