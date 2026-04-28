package com.my.kipawa.modules.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;   // primary identifier
    private String password;
}
