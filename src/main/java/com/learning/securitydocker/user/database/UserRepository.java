package com.learning.securitydocker.user.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Transactional
    @Query("FROM UserEntity u WHERE u.name=:name")
    UserEntity findUserByName(String name);
}
