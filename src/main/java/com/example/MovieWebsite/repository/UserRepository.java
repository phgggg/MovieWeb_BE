package com.example.MovieWebsite.repository;

import com.example.MovieWebsite.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity getUserEntitiesByUserID (Integer id);
    List<UserEntity> findAll();
    UserEntity getUserByUserName(String username); //function for generate token
    UserEntity findUserEntityByUserName(String userName);
    UserEntity findUserEntityByUserID(Integer id);
    UserEntity findUsersEntityByEmail(String email);
}
