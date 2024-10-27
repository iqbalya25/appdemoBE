package org.sa.appdemo.users.service;


import org.sa.appdemo.users.dto.UserDto;
import org.sa.appdemo.users.dto.UserResponse;
import org.sa.appdemo.users.entity.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserDto userDto);
    UserResponse updateUser(Long id, UserDto userDto);
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();
    void deleteUser(Long id);
    UserResponse getUserByUsername(String username);
    User getCurrentAuthenticatedUser();
}
