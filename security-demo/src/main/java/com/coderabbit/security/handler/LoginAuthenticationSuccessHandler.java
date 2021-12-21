package com.coderabbit.security.handler;


import com.coderabbit.security.model.LoginToken;
import com.coderabbit.security.result.Result;
import com.coderabbit.security.utils.JwtUtils;
import com.coderabbit.security.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功操作
 */
@Component
@Slf4j
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtils jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //TODO 根据业务需要进行处理，陈某这里只返回两个token
        //生成令牌
        String accessToken = jwtTokenUtil.createToken(userDetails.getUsername());
        //生成刷新令牌，如果accessToken令牌失效，则使用refreshToken重新获取令牌（refreshToken过期时间必须大于accessToken）
        String refreshToken = jwtTokenUtil.refreshToken(accessToken);
        renderToken(httpServletResponse, LoginToken.builder().accessToken(accessToken).refreshToken(refreshToken).build());
    }

    /**
     * 渲染返回 token 数据,因为前端页面接收的都是Result对象，故使用application/json返回
     */
    public void renderToken(HttpServletResponse response, LoginToken token) throws IOException {
        ResponseUtils.result(response, Result.ok().msg("登录成功！").data(token));
    }
}