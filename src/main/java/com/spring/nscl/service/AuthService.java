package com.spring.nscl.service;

import com.spring.nscl.payload.LoginDto;

public interface AuthService {
    String  login(LoginDto loginDto);
}
