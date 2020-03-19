package com.learning.securitydocker.user;

import com.learning.securitydocker.security.roles.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEntity {
    private String name;

    private String surname;

    private String password;

    private Role role;
}
