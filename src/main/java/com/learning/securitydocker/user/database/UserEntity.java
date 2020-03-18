package com.learning.securitydocker.user.database;

import com.learning.securitydocker.security.roles.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "user")
class UserEntity {

    @Id
    @Column(name = "userName")
    private String userName;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    private char[] password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "isAccountExpired")
    private boolean isAccountExpired;

    @Column(name = "isCredentialsExpired")
    private boolean isCredentialsExpired;

    @Column(name = "isAccountLocked")
    private boolean isAccountLocked;

    @Column(name = "isDisabled")
    private boolean isDisabled;
}