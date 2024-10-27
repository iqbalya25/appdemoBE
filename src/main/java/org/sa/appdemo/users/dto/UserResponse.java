package org.sa.appdemo.users.dto;

import lombok.Data;
import org.sa.appdemo.users.entity.UserRole;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

