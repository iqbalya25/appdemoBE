package org.sa.appdemo.auth.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.sa.appdemo.auth.dto.LoginRequest;
import org.sa.appdemo.auth.dto.LoginResponse;
import org.sa.appdemo.auth.service.AuthService;
import org.sa.appdemo.users.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(authService.register(userDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout() {
        authService.logout();
        return ResponseEntity.ok()
                .body(new MessageResponse("Successfully logged out"));
    }
}

@Data
@AllArgsConstructor
class MessageResponse {
    private String message;
}
