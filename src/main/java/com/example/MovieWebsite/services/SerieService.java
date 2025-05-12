package com.example.MovieWebsite.services;

import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.serie.SerieDTO;


public interface SerieService {
    BaseResultDTO findAllSeries(Integer pageSize, Integer page);
    BaseResultDTO addSerie(SerieDTO SerieDTO);
    BaseResultDTO updateSerie(SerieDTO SerieDTO);
    BaseResultDTO findSerieEntityBySerieName(String SerieName);
    BaseResultDTO deleteSerie(Integer id);
    BaseResultDTO findSerieById(Integer id);
    BaseResultDTO findAll();
    BaseResultDTO findSeriesByGenre(String genre);
    BaseResultDTO findSerieEpisode(Integer id);
}
