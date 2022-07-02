package com.twt.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.twt.entity.User;
import com.twt.service.UserService;
import com.twt.utils.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    // 支持的token
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }


    // 权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        AccountProfile accountProfile  = (AccountProfile)principalCollection.getPrimaryPrincipal();
        String userRole = userService.getUserRole(accountProfile.getId());
        authorizationInfo.addRole(userRole);
        return authorizationInfo;
    }


    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 根据Token.Body.Subject中的uid 反查数据库
        JWTToken jwtToken = (JWTToken) authenticationToken;
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        User user = userService.getById(userId);

        if (user==null){
            throw new UnknownAccountException("账户不存在");
        }

        // 赋给Shiro的用户对象
        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(user,profile);

        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }
}
