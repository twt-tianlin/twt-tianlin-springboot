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
 * @author 史熹东
 * @since 2022-06-20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    // 登录接口
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        // 数据库查询用户  进行校验
        User user = userService.getOne(new QueryWrapper<User>().eq("email", loginDto.getEmail()));
        Assert.notNull(user, "用户不存在");
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            return Result.fail("密码不正确");
        }

        // 生成token  放置响应头
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");

        // 返回用户信息
        return Result.success(MapUtil.builder()
                .put("id",user.getId())
                .put("name",user.getName())
                .put("email",user.getEmail())
                .put("role",userService.getUserRole(user.getId()))
                .put("token",jwt)
                .map());
    }

    // 注册接口
    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterDto registerDto, HttpServletResponse response){
        // 查用邮箱是否被注册过
        User user = userService.getOne(new QueryWrapper<User>().eq("email", registerDto.getEmail()));
        if (user!=null){
            throw new UserException("邮箱已被注册 如有问题请联系管理员");
        }

        // 注册用户
        User registerAfterUser = userService.register(registerDto);

        // 生成token  放置响应头
        String jwt = jwtUtils.generateToken(registerAfterUser.getId());
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");

        // 返回用户信息
        return Result.success(MapUtil.builder()
                .put("id",registerAfterUser.getId())
                .put("name",registerAfterUser.getName())
                .put("email",registerAfterUser.getEmail())
                .put("role",userService.getUserRole(registerAfterUser.getId()))
                .put("token",jwt)
                .map());
    }


    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }

}

