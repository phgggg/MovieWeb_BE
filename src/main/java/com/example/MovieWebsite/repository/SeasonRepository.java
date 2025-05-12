package com.example.MovieWebsite.repository;

import com.example.MovieWebsite.entity.SeasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeasonRepository extends JpaRepository<SeasonEntity, Integer> {
    SeasonEntity getSeasonEntitiesBySeasonID (Integer id);
    List<SeasonEntity> findAll();
    SeasonEntity findSeasonEntityBySeasonName(String SeasonName);
    SeasonEntity findSeasonEntityBySeasonID(Integer id);
}
