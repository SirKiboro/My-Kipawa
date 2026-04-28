package com.my.kipawa.modules.auth.service;

import com.my.kipawa.common.exception.BadRequestException;
import com.my.kipawa.modules.auth.dto.AuthResponse;
import com.my.kipawa.modules.auth.dto.LoginRequest;
import com.my.kipawa.modules.auth.dto.RegisterRequest;
import com.my.kipawa.modules.user.entity.Role;
import com.my.kipawa.modules.user.entity.User;
import com.my.kipawa.modules.user.repository.UserRepository;
import com.my.kipawa.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register (RegisterRequest request){

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email is already registered");

        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username is already taken");

        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .enabled(true)
                .build();

        // Save
        userRepository.save(user);

        // Generate Token
        var jwtToken = jwtUtil.generateToken(user.getEmail());

        return AuthResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }

    public AuthResponse authenticate(LoginRequest request) {

        //request.getEmail() acts as the 'Identifier' (username or email)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmailOrUsername(request.getEmail(), request.getEmail())
                .orElseThrow(() -> new BadRequestException("User not found after authentication"));

        var jwtToken = jwtUtil.generateToken(user.getEmail());

        return AuthResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .username(user.getActualUsername())
                .build();
    }
}
