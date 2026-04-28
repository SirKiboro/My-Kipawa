package com.my.kipawa.security;

import com.my.kipawa.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginInput) throws UsernameNotFoundException {

        // Search the database for a match in either column
        return userRepository.findByEmailOrUsername(loginInput, loginInput)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with email or username: " + loginInput));

    }
}
