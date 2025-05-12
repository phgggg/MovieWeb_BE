package com.example.MovieWebsite.services;

import com.example.MovieWebsite.web.dto.genre.GenreDTO;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;


public interface GenreService {
    BaseResultDTO findAllGenres(Integer pageSize, Integer page);
    BaseResultDTO addGenre(GenreDTO genreDTO);
    BaseResultDTO updateGenre(GenreDTO genreDTO);
    BaseResultDTO findGenreEntityByGenreName(String genreName);
    BaseResultDTO deleteGenre(Integer id);
    BaseResultDTO findAll();
}
