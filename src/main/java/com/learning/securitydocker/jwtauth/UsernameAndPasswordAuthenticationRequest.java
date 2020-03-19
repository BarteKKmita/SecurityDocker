package com.learning.securitydocker.jwtauth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UsernameAndPasswordAuthenticationRequest {
    private String userName;
    private String password;
}
