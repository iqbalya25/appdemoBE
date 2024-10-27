package org.sa.appdemo.auth.service;

import org.sa.appdemo.auth.dto.LoginRequest;
import org.sa.appdemo.auth.dto.LoginResponse;
import org.sa.appdemo.users.dto.UserDto;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    LoginResponse register(UserDto userDto);
    void logout();
}
