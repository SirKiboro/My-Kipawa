package com.my.kipawa.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // TEMPORARY MOCK
        // inject UserRepository here

        if ("system_admin".equals(username)) {
            return new org.springframework.security.core.userdetails.User(
                    "system_admin",
                    "password_hash", // be a BCrypt hash
                    new ArrayList<>()
            );
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
