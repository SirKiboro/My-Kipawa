package com.my.kipawa.modules.auth.controller;

import com.my.kipawa.common.response.ApiResponse;
import com.my.kipawa.modules.auth.dto.AuthResponse;
import com.my.kipawa.modules.auth.dto.LoginRequest;
import com.my.kipawa.modules.auth.dto.RegisterRequest;
import com.my.kipawa.modules.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService  authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register (@RequestBody RegisterRequest request){
        return ResponseEntity.ok(new ApiResponse<>(
                true, "Registration successful", authService.register(request)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> authenticate (@RequestBody LoginRequest request){
        return ResponseEntity.ok(new ApiResponse<>(
                true, "Login successful", authService.authenticate(request)));

    }

}
