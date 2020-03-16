package com.learning.securitydocker.security.roles;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.learning.securitydocker.security.roles.Permissions.READ;
import static com.learning.securitydocker.security.roles.Permissions.WRITE;

public enum Role {

    ADMIN(Sets.newHashSet(READ, WRITE)) {
        @Override
        public Set<SimpleGrantedAuthority> getRolePermissions(){
            return getPermissionsCollection(ADMIN);
        }
    },
    USER(Sets.newHashSet(READ)) {
        @Override
        public Set<SimpleGrantedAuthority> getRolePermissions(){
            return getPermissionsCollection(USER);
        }
    };

    private Set<Permissions> permissions;

    Role(HashSet<Permissions> permissions){
        this.permissions = permissions;
    }

    private Set<Permissions> getPermissions(){
        return permissions;
    }

    public abstract Set<SimpleGrantedAuthority> getRolePermissions();

    private static Set<SimpleGrantedAuthority> getPermissionsCollection(Role role){
        return role.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }
}
