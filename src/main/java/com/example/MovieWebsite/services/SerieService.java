package com.example.MovieWebsite.services;

import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.serie.*;


public interface SerieService {
    BaseResultDTO findAllSeries(Integer pageSize, Integer page);
    BaseResultDTO addSerie(SerieDTO SerieDTO);
    BaseResultDTO updateSerie(SerieDTO SerieDTO);
    BaseResultDTO deleteSerie(Integer id);
    BaseResultDTO findSerieEntityBySerieName(String SerieName);
    BaseResultDTO findSerieById(Integer id);
    BaseResultDTO findAll();
    BaseResultDTO findSeriesByGenre(String genre);
    BaseResultDTO findSerieEpisode(Integer id);

    BaseResultDTO findAllEpisode();
    BaseResultDTO addEpisode(EpisodeRequestDTO episodeDTO);
    BaseResultDTO updateEpisode(EpisodeRequestDTO episodeDTO);
    BaseResultDTO deleteEpisode(Integer id);

    BaseResultDTO findAllSeason();
    BaseResultDTO addSeason(SeasonRequestDTO seasonDTO);
    BaseResultDTO updateSeason(SeasonRequestDTO seasonDTO);
    BaseResultDTO deleteSeason(Integer id);
}
