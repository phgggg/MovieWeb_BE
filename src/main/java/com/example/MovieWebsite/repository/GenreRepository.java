package com.example.MovieWebsite.repository;


import com.example.MovieWebsite.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {
    GenreEntity getGenreEntitiesByGenreID (Integer id);
    List<GenreEntity> findAll();
    GenreEntity findGenreEntityByGenreName(String genreName);
    GenreEntity findGenreEntityByGenreID(Integer id);
}
