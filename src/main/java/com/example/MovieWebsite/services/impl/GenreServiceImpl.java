package com.example.MovieWebsite.services.impl;

import com.example.MovieWebsite.entity.ActorEntity;
import com.example.MovieWebsite.entity.GenreEntity;
import com.example.MovieWebsite.services.BaseService;
import com.example.MovieWebsite.services.GenreService;
import com.example.MovieWebsite.web.dto.actor.ActorDTO;
import com.example.MovieWebsite.web.dto.genre.GenreDTO;
import com.example.MovieWebsite.web.dto.response.ArrayResultDTO;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.response.SingleResultDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl extends BaseService implements GenreService {
    @Override
    public BaseResultDTO findAllGenres(Integer pageSize, Integer page) {
        logger.info("=== START FIND ALL GENRES::");
        ArrayResultDTO<GenreDTO> responseResultDTO = new ArrayResultDTO<>();
        List<GenreDTO> listGenre = new ArrayList<>();
        try{
            Page<GenreEntity> rawDatas = genreRepository.findAll(PageRequest.of(page, pageSize));
            if (rawDatas != null) {
                if (!rawDatas.getContent().isEmpty()) {
                    rawDatas.getContent().forEach(genre -> {
                        GenreDTO genreDTO = modelMapper.map(genre, GenreDTO.class);
                        listGenre.add(genreDTO);
                    });
                }
                responseResultDTO.setSuccess(listGenre, rawDatas.getTotalElements(), rawDatas.getTotalPages());
                logger.info("=== FIND ALL GENRES RESPONSE::" + responseResultDTO.getErrorCode());
            }
        }catch (Exception ex) {
            responseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO addGenre(GenreDTO genreDTO) {
        logger.info("=== START ADD NEW GENRE::");
        BaseResultDTO responseResultDTO = new BaseResultDTO();
        try {
            logger.info("a\t\t==="+genreDTO.getGenreName()+"\t\t");

            GenreEntity genre = modelMapper.map(genreDTO, GenreEntity.class);
            genre = genreRepository.save(genre);
            if (genre != null) {
                logger.info("new genre id = " + genre.getGenreID());
                responseResultDTO.setSuccess();
            }
            logger.info("=== ADD NEW GENRE RESPONSE::" + responseResultDTO.getErrorCode());
        } catch (Exception ex) {
            responseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO updateGenre(GenreDTO genreDTO) {
        logger.info("=== START UPDATE GENRE::");
        SingleResultDTO<GenreEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            GenreEntity genre = genreRepository.findGenreEntityByGenreID(genreDTO.getGenreID());
            if(genre!=null){
                GenreEntity newGenre = modelMapper.map(genreDTO, GenreEntity.class);
                genreRepository.save(newGenre);
                responseResultDTO.setSuccess();
            }
            logger.info("UPDATE GENRE RESPONSE" + responseResultDTO.getErrorCode());
        }catch (Exception ex){
            logger.info("=== UPDATE GENRE ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findGenreEntityByGenreName(String genreName) {
        logger.info("=== START FIND GENRE::");
        SingleResultDTO<GenreEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            GenreEntity genre = genreRepository.findGenreEntityByGenreName(genreName);
            if(genre!=null){
                responseResultDTO.setSuccess(genre);
            }
            logger.info("FIND GENRE RESPONSE" + responseResultDTO.getErrorCode());
        }catch (Exception ex){
            logger.info("=== FIND GENRE ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO deleteGenre(Integer id) {
        logger.info("=== START DEL GENRE::");
        SingleResultDTO<GenreEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            GenreEntity genre = genreRepository.findGenreEntityByGenreID(id);
            if(genre!=null){
                genreRepository.delete(genre);
                responseResultDTO.setSuccess();
                logger.info("DEL GENRE RESPONSE" + responseResultDTO.getErrorCode());
            }
            logger.info("DEL GENRE RESPONSE" + responseResultDTO.getErrorCode());
        }catch (Exception ex){
            logger.info("=== DEL GENRE ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findAll() {
        logger.info("=== START FIND ALL ENTITY::");
        ArrayResultDTO<GenreEntity> responseResultDTO = new ArrayResultDTO<>();
        try{
            ArrayList<GenreEntity> genrelist = (ArrayList<GenreEntity>) genreRepository.findAll();
            if(!genrelist.isEmpty()){
                responseResultDTO.setSuccess(genrelist);
            }
        }catch (Exception ex){
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }
}
