package com.learning.securitydocker.user;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("Dummy")
public class DummyUserRepository {
    private static final List<UserEntity> USER_ENTITIES=getUsers();

    public static List<UserEntity> getUsers(){
        return List.of(new UserEntity("James", "Bond", new char[]{'G', 'o', 'l', 'd', 'e', 'n', 'E', 'y', 'e'}),
                new UserEntity("Leonardo", "DiCaprio", new char[]{'I', 'n', 'c', 'e', 'p', 't', 'i', 'o', 'n'}),
                new UserEntity("Sophie", "Marceau",
                        new char[]{'T', 'o', 'm', 'o', 'r', 'r', 'o', 'w', 'N', 'e', 'v', 'e', 'r', 'D', 'i', 'e', 's'}));
    }

    //TODO: custom exceptions
    UserEntity getUserByName(String username){
        return USER_ENTITIES.stream()
                .filter(userEntity -> username.equals(userEntity.getName()))
                .findFirst().orElseThrow();
    }
}
