package com.coderabbit.security.controller;


import com.coderabbit.security.constant.SecurityConstant;
import com.coderabbit.security.exception.UtilException;
import com.coderabbit.security.model.LoginToken;
import com.coderabbit.security.result.Result;
import com.coderabbit.security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
public class HomeController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtTokenUtil;

    /**
     * 刷新令牌
     * @return
     */
    @PostMapping("/refreshToken")
    public Result refreshToken(HttpServletRequest request){
        //从请求头中获取refreshToken
        String oldRefreshToken = request.getHeader(SecurityConstant.REFRESH_TOKEN_HEADER);
        //校验refreshToken，如果令牌没有过期
        if (jwtUtils.isTokenExpired(oldRefreshToken)){
            throw new UtilException("刷新令牌已过期，请重新登录！");
        }
        //解析refreshToken
        String username = jwtUtils.getUsernameFromToken(oldRefreshToken);
        //生成新的accessToken
        String newAccessToken = jwtUtils.createToken(username);
        String newRefreshToken = jwtUtils.refreshToken(newAccessToken);
        LoginToken loginToken = new LoginToken(newAccessToken,newRefreshToken);
        return Result.ok().data(loginToken);
    }

}
