package com.twt.controller;


import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.twt.dto.LoginDto;
import com.twt.dto.RegisterDto;
import com.twt.entity.User;
import com.twt.ex.UserException;
import com.twt.service.UserService;
import com.twt.utils.JwtUtils;
import com.twt.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sxd
 * @since 2022-06-20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("email", loginDto.getEmail()));
        Assert.notNull(user, "用户不存在");

        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            System.out.println(SecureUtil.md5(loginDto.getPassword()));
            return Result.fail("密码不正确");
        }

        String jwt = jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");


        return Result.success(MapUtil.builder()
                .put("id",user.getId())
                .put("name",user.getName())
                .put("email",user.getEmail())
                .put("role",userService.getUserRole(user.getId()))
                .put("token",jwt)
                .map());
    }

    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterDto registerDto, HttpServletResponse response){
        User user = userService.getOne(new QueryWrapper<User>().eq("email", registerDto.getEmail()));
        if (user!=null){
            throw new UserException("用户名已存在哦 请重新选择用户名");
        }

        User registerAfterUser = userService.register(registerDto);
        String jwt = jwtUtils.generateToken(registerAfterUser.getId());

        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");

        return Result.success(MapUtil.builder()
                .put("id",registerAfterUser.getId())
                .put("name",registerAfterUser.getName())
                .put("email",registerAfterUser.getEmail())
                .put("role",userService.getUserRole(registerAfterUser.getId()))
                .put("token",jwt)
                .map());
    }

    @RequiresAuthentication
    @RequiresRoles("admin")
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }

}

