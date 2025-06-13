package com.example.MovieWebsite.repository;

import com.example.MovieWebsite.entity.MovieEntity;
import com.example.MovieWebsite.entity.SerieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SerieRepository extends JpaRepository<SerieEntity, Integer> {
    SerieEntity getSerieEntitiesBySerieID (Integer id);
    List<SerieEntity> findAll();
    SerieEntity findSerieEntityBySerieName(String SerieName);
    SerieEntity findSerieEntityBySerieID(Integer id);
    @Query("SELECT m FROM SerieEntity m JOIN m.genres g WHERE LOWER(g.genreName) = LOWER(:genreName)")
    List<SerieEntity> findSerieEntitiesBySerieGenre(@Param("genreName") String genre);

    @Query(value = "SELECT * FROM series " +
            "where MATCH (serie_name) AGAINST (:keyword in boolean mode) " +
            "or MATCH (original_serie_name) AGAINST (:keyword  in boolean mode);", nativeQuery = true)
//    @Query(value = "SELECT * FROM series " +
//        "WHERE serie_name LIKE CONCAT('%', :keyword, '%') " +
//        "OR original_serie_name LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    List<SerieEntity> searchSerieEntities(@Param("keyword") String keyword);
}
