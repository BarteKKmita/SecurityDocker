package com.learning.securitydocker.user;

import com.learning.securitydocker.security.roles.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DummyUser {
    private String name;

    private String surname;

    private char[] password;

    private Role role;
}