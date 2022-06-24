package com.twt.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.twt.dto.RegisterDto;
import com.twt.entity.User;
import com.twt.mapper.UserMapper;
import com.twt.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sxd
 * @since 2022-06-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User register(RegisterDto registerDto) {
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(SecureUtil.md5(registerDto.getPassword()));
        userMapper.insert(user);
        // 注册完之后是user角色
        userMapper.setUserRole(user.getId());
        return user;
    }

    @Override
    public String getUserRole(Integer id) {
        return userMapper.getUserRole(id);
    }
}
