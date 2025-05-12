package com.example.MovieWebsite.repository;


import com.example.MovieWebsite.entity.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ActorRepository extends JpaRepository<ActorEntity, Integer> {
    ActorEntity getActorEntitiesByActorID (Integer id);
    List<ActorEntity> findAll();
    ActorEntity findActorEntityByActorName(String userName);
    ActorEntity findActorEntityByActorID(Integer id);
}
