package com.example.MovieWebsite.repository;

import com.example.MovieWebsite.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
    MovieEntity getMovieEntitiesByMovieID (Integer id);
    List<MovieEntity> findAll();
    MovieEntity findMovieEntityByMovieName(String movieName);
    MovieEntity findMovieEntityByMovieID(Integer id);
    @Query("SELECT m FROM MovieEntity m JOIN m.genres g WHERE LOWER(g.genreName) = LOWER(:genreName)")
    List<MovieEntity> findMovieEntitiesByMovieGenre(@Param("genreName") String genre);

//    @Query(value = "SELECT * FROM movies " +
//            "where MATCH (movie_name) AGAINST (:keyword in boolean mode) " +
//            "or MATCH (original_movie_name) AGAINST (:keyword  in boolean mode);", nativeQuery = true)
    @Query(value = "SELECT * FROM movies " +
            "WHERE movie_name LIKE CONCAT('%', :keyword, '%') " +
            "OR original_movie_name LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    List<MovieEntity> searchMovieEntities(@Param("keyword") String keyword);
}
