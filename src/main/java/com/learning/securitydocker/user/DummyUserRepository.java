package com.learning.securitydocker.user;

import com.learning.securitydocker.exceptions.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.learning.securitydocker.security.roles.Role.ADMIN;
import static com.learning.securitydocker.security.roles.Role.USER;

@Repository("Dummy")
public class DummyUserRepository {
    private static final List<UserEntity> USER_ENTITIES = initUsers();

    public static List<UserEntity> initUsers(){
        return List.of(new UserEntity("James", "Bond",
                        new char[]{'G', 'o', 'l', 'd', 'e', 'n', 'E', 'y', 'e'}, ADMIN),
                new UserEntity("Leonardo", "DiCaprio",
                        new char[]{'I', 'n', 'c', 'e', 'p', 't', 'i', 'o', 'n'}, USER),
                new UserEntity("Sophie", "Marceau",
                        new char[]{'T', 'o', 'm', 'o', 'r', 'r', 'o', 'w', 'N', 'e', 'v', 'e', 'r', 'D', 'i', 'e', 's'},
                        ADMIN));
    }

    public List<UserEntity> getUsers(){
        return USER_ENTITIES;
    }

    //TODO: custom exceptions
    UserEntity getUserByName(String username){
        return USER_ENTITIES.stream()
                .filter(userEntity -> username.equals(userEntity.getName()))
                .findFirst().orElseThrow(() -> new UserNotFoundException("Specified user doest not exists"));
    }
}
