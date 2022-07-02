package com.twt.shiro;

import org.apache.shiro.authc.AuthenticationToken;


// 自定义Token JWT自动包含了principal和credential
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String jwt){
        this.token=jwt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
