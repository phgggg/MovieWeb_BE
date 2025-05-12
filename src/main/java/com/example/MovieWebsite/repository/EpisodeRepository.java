package com.example.MovieWebsite.repository;


import com.example.MovieWebsite.entity.EpisodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EpisodeRepository extends JpaRepository<EpisodeEntity, Integer> {
    EpisodeEntity getEpisodeEntitiesByEpisodeID (Integer id);
    List<EpisodeEntity> findAll();
    EpisodeEntity findEpisodeEntityByEpisodeName(String episodeName);
    EpisodeEntity findEpisodeEntityByEpisodeID(Integer id);
    @Query(value = "SELECT * FROM moviewebsite.episode where serieid = :id;", nativeQuery = true)
    List<EpisodeEntity> findEpisodeEntitiesBySerieID(@Param("id") Integer id);
}
