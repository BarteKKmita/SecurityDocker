package com.learning.securitydocker.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEntity {
    private String name;
    private String surname;
    private char[] password;
}
