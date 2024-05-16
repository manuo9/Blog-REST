package com.spring.blog.service;

import com.spring.blog.payload.LoginDto;

public interface AuthService {
    String  login(LoginDto loginDto);
}
