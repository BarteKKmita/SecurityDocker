package com.learning.securitydocker.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private DummyUserRepository repository;

    public UserDTO getUserByName(String username){
        var userEntity = repository.getUserByName(username);
        return UserDTO.builder()
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .build();
    }

}
