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
                        "$2a$10$A8CVfxOGzPO4.hv7o20NTOSeKKIpwfJUrQuQT/6GiR0Zi9HQSwNPu", ADMIN),
                new UserEntity("Leonardo", "DiCaprio",
                        "$2a$10$AgwzVwKy1uT2xwCEYB.5u.swT3bAjkH.AvAyw0mVUGSwG8XQhHeUW", USER),
                new UserEntity("Sophie", "Marceau",
                        "$2a$10$/NXDaALRD5iqE3YcvFybmekxij0ZUbm7xJLKt.ROVonZ4RzAKBYbK",
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
