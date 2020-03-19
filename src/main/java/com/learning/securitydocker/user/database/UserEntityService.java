package com.learning.securitydocker.user.database;

import com.learning.securitydocker.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("FromDatabase")
public class UserEntityService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        var userEntity = repository.findById(username)
                .orElseThrow(() -> new UserNotFoundException("User with specified username: " + username + " does not exists!"));
        return getUserDetails(userEntity);
    }

    private UserDetails getUserDetails(UserEntity userEntity){
        return User.builder()
                .username(userEntity.getUserName())
                .password(userEntity.getPassword())
                .authorities(userEntity.getRole().getRolePermissions())
                .accountExpired(userEntity.isAccountExpired())
                .accountLocked(userEntity.isAccountLocked())
                .credentialsExpired(userEntity.isCredentialsExpired())
                .disabled(userEntity.isDisabled())
                .build();
    }
}
