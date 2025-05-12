package com.example.MovieWebsite.repository;

import com.example.MovieWebsite.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
    ReviewEntity getReviewEntitiesByReviewID (Integer id);
    List<ReviewEntity> findAll();
    ReviewEntity findReviewEntityByReviewID(Integer id);
    @Query(value = "SELECT * FROM moviewebsite.reviews where movieid = :id;", nativeQuery = true)
    List<ReviewEntity> findReviewEntitiesByMovieId(@Param("id")Integer id);
    @Query(value = "SELECT * FROM moviewebsite.reviews where serieid = :id;", nativeQuery = true)
    List<ReviewEntity> findReviewEntitiesBySerieId(@Param("id")Integer id);
}
