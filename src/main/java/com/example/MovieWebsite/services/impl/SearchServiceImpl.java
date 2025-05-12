package com.example.MovieWebsite.services.impl;

import com.example.MovieWebsite.entity.MovieEntity;
import com.example.MovieWebsite.entity.SerieEntity;
import com.example.MovieWebsite.services.BaseService;
import com.example.MovieWebsite.services.SearchService;
import com.example.MovieWebsite.web.dto.movie.MovieDTO;
import com.example.MovieWebsite.web.dto.response.ArrayResultDTO;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.response.MediaDTO;
import com.example.MovieWebsite.web.dto.response.SingleResultDTO;
import com.example.MovieWebsite.web.dto.serie.SerieDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl extends BaseService implements SearchService {
    @Override
    public BaseResultDTO search(String keyword) {
        logger.info("=== SEARCH BY KEYWORD::");
        SingleResultDTO<MediaDTO> responseResultDTO = new SingleResultDTO<>();
        MediaDTO listMedia = new MediaDTO();
        ArrayList<SerieDTO> listSerie = new ArrayList<>();
        ArrayList<MovieDTO> listMovie = new ArrayList<>();
        try{
            ArrayList<MovieEntity> MovieList = (ArrayList<MovieEntity>) movieRepository.searchMovieEntities(keyword);
            ArrayList<SerieEntity> SerieList = (ArrayList<SerieEntity>) serieRepository.searchSerieEntities(keyword);
            if(!MovieList.isEmpty()){
                logger.info("=== SEARCH MOVIES FOUND::");
                for (MovieEntity movie : MovieList) {
                    MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
                    listMovie.add(movieDTO);
                }
                listMedia.setMovieList(listMovie);
            }
            if(!SerieList.isEmpty()){
                logger.info("=== SEARCH SERIES FOUND::");
                for (SerieEntity serie : SerieList) {
                    SerieDTO serieDTO = modelMapper.map(serie, SerieDTO.class);
                    listSerie.add(serieDTO);
                }
                listMedia.setSerieList(listSerie);
            }
            responseResultDTO.setSuccess(listMedia);
        }catch (Exception ex){
            logger.info("=== SEARCH BY KEYWORD ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }
}
