package com.coderabbit.security.handler;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String md5Str = DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
        return encodedPassword.equals(md5Str);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
    }
}