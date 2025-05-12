package com.example.MovieWebsite.services.impl;

import com.example.MovieWebsite.entity.MovieEntity;
import com.example.MovieWebsite.services.BaseService;
import com.example.MovieWebsite.services.MovieService;
import com.example.MovieWebsite.web.dto.movie.MovieDTO;
import com.example.MovieWebsite.web.dto.response.ArrayResultDTO;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.response.SingleResultDTO;
import com.example.MovieWebsite.web.dto.serie.SerieDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MovieServiceImpl extends BaseService implements MovieService {
    @Override
    public BaseResultDTO findAllMovies(Integer pageSize, Integer page) {
        logger.info("=== START FIND ALL MovieS::");
        ArrayResultDTO<MovieDTO> responseResultDTO = new ArrayResultDTO<>();
        List<MovieDTO> listMovie = new ArrayList<>();
        try{
            Page<MovieEntity> rawDatas = movieRepository.findAll(PageRequest.of(page, pageSize));
            if (rawDatas != null) {
                if (!rawDatas.getContent().isEmpty()) {
                    rawDatas.getContent().forEach(Movie -> {
                        MovieDTO MovieDTO = modelMapper.map(Movie, MovieDTO.class);
                        listMovie.add(MovieDTO);
                    });
                }
                responseResultDTO.setSuccess(listMovie, rawDatas.getTotalElements(), rawDatas.getTotalPages());
                logger.info("=== FIND ALL USERS RESPONSE::" + responseResultDTO.getErrorCode());
            }
        }catch (Exception ex) {
            responseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO addMovie(MovieDTO MovieDTO) {
        logger.info("=== START ADD NEW Movie::");
        BaseResultDTO responseResultDTO = new BaseResultDTO();
        try {
            if ((movieRepository.findMovieEntityByMovieName(MovieDTO.getMovieName())) != null) {
                responseResultDTO.setFail("-3", "Phim này đã tồn tại !!!!");
                logger.info("=== ADD NEW Movie STOP RESPONES: " + responseResultDTO.getErrorCode());
                return responseResultDTO;
            }
            logger.info("a\t\t==="+MovieDTO.getMovieName()+"\t\t");

            MovieEntity Movie = modelMapper.map(MovieDTO, MovieEntity.class);
            Movie = movieRepository.save(Movie);
            if (Movie != null) {
                logger.info("new Movie id = " + Movie.getMovieID());
                responseResultDTO.setSuccess();
            }
            logger.info("=== ADD NEW Movie RESPONSE::" + responseResultDTO.getErrorCode());
        } catch (Exception ex) {
            responseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO updateMovie(MovieDTO MovieDTO) {
        logger.info("=== START UPDATE Movie::");
        SingleResultDTO<MovieEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            MovieEntity Movie = movieRepository.findMovieEntityByMovieID(MovieDTO.getMovieID());
            if(Movie!=null){
                MovieEntity newMovie = modelMapper.map(MovieDTO, MovieEntity.class);
                movieRepository.save(newMovie);
                responseResultDTO.setSuccess();
            }
            logger.info("UPDATE Movie RESPONSE" + responseResultDTO.getErrorCode());
        }catch (Exception ex){
            logger.info("=== UPDATE Movie ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findMovieEntityByMovieName(String MovieName) {
        logger.info("=== START D Movie BY NAME::");
        SingleResultDTO<MovieEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            MovieEntity Movie = movieRepository.findMovieEntityByMovieName(MovieName);
            if(Movie!=null){
                responseResultDTO.setSuccess(Movie);
            }
        }catch (Exception ex){
            logger.info("=== FIND Movie BY NAME ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO deleteMovie(Integer id) {
        logger.info("=== START FIND Movie BY ID::");
        SingleResultDTO<MovieEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            MovieEntity Movie = movieRepository.findMovieEntityByMovieID(id);
            if(Movie!=null){
                movieRepository.delete(Movie);
                responseResultDTO.setSuccess();
                logger.info("DELETE Movie RESPONSE" + responseResultDTO.getErrorCode());
            }
        }catch (Exception ex){
            logger.info("=== DELETE Movie ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findAll() {
        logger.info("=== START FIND ALL ENTITY::");
        ArrayResultDTO<MovieDTO> responseResultDTO = new ArrayResultDTO<>();
        List<MovieDTO> listMovie = new ArrayList<>();
        try{
            ArrayList<MovieEntity> MovieList = (ArrayList<MovieEntity>) movieRepository.findAll();
            if(!MovieList.isEmpty()){
                logger.info("=== FOUND::");
                for (MovieEntity movie : MovieList) {
                    MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
                    listMovie.add(movieDTO);
                }
                responseResultDTO.setSuccess(listMovie);
            }
        }catch (Exception ex){
            logger.info("=== FAILED::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findMovieById(Integer id) {
        logger.info("=== START D Movie BY ID::");
        SingleResultDTO<MovieEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            MovieEntity Movie = movieRepository.findMovieEntityByMovieID(id);
            if(Movie!=null){
                responseResultDTO.setSuccess(Movie);
            }
        }catch (Exception ex){
            logger.info("=== FIND Movie BY ID ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findMoviesByGenre(String genre) {
        logger.info("=== START D Movie BY GENRE::");
        ArrayResultDTO<MovieDTO> responseResultDTO = new ArrayResultDTO<>();
        List<MovieDTO> listMovie = new ArrayList<>();
        List<SerieDTO> listSerie = new ArrayList<>();
        try{
            ArrayList<MovieEntity> MovieList = (ArrayList<MovieEntity>) movieRepository.findMovieEntitiesByMovieGenre(genre);
            if(!MovieList.isEmpty()){
                for (MovieEntity movie : MovieList) {
                    MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
                    listMovie.add(movieDTO);
                }
                responseResultDTO.setSuccess(listMovie);
            }
        }catch (Exception ex){
            logger.info("=== FIND Movie BY GENRE ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }
}
