package com.my.kipawa.modules.user.controller;

import com.my.kipawa.common.response.ApiResponse;
import com.my.kipawa.modules.user.dto.UserResponseDTO;
import com.my.kipawa.modules.user.entity.User;
import com.my.kipawa.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getCurrentUser(@AuthenticationPrincipal User user) {
        UserResponseDTO userDto = modelMapper.map(user, UserResponseDTO.class);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Profile fetched successfully", userDto)
        );
    }

    @GetMapping("/{id:[0-9]+}")
    @PreAuthorize("hasRole ('ADMIN')")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserById(@PathVariable Long id){
        UserResponseDTO user = userService.getUserById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "User fetched successfully", user)
        );
    }

}
