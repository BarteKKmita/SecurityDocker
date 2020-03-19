package com.learning.securitydocker.user;

import com.learning.securitydocker.user.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public UserDTO getUserByName(String username){
        var userEntity = repository.findUserByName(username);
        return UserDTO.builder()
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .build();
    }
}