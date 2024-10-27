package org.sa.appdemo.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.sa.appdemo.users.entity.UserRole;


@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private UserRole role;
}