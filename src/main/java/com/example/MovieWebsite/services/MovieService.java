package com.example.MovieWebsite.services;

import com.example.MovieWebsite.web.dto.movie.MovieDTO;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;


public interface MovieService {
    BaseResultDTO findAllMovies(Integer pageSize, Integer page);
    BaseResultDTO addMovie(MovieDTO MovieDTO);
    BaseResultDTO updateMovie(MovieDTO MovieDTO);
    BaseResultDTO findMovieEntityByMovieName(String MovieName);
    BaseResultDTO deleteMovie(Integer id);
    BaseResultDTO findAll();
    BaseResultDTO findMovieById(Integer id);
    BaseResultDTO findMoviesByGenre(String genre);
}
