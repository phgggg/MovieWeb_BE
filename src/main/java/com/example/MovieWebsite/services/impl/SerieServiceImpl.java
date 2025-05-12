package com.example.MovieWebsite.services.impl;

import com.example.MovieWebsite.entity.EpisodeEntity;
import com.example.MovieWebsite.entity.MovieEntity;
import com.example.MovieWebsite.entity.SerieEntity;
import com.example.MovieWebsite.services.BaseService;
import com.example.MovieWebsite.services.SerieService;
import com.example.MovieWebsite.web.dto.movie.MovieDTO;
import com.example.MovieWebsite.web.dto.serie.EpisodeDTO;
import com.example.MovieWebsite.web.dto.serie.SerieDTO;
import com.example.MovieWebsite.web.dto.response.ArrayResultDTO;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.response.SingleResultDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SerieServiceImpl extends BaseService implements SerieService {
    @Override
    public BaseResultDTO findAllSeries(Integer pageSize, Integer page) {
        logger.info("=== START FIND ALL SerieS::");
        ArrayResultDTO<SerieDTO> responseResultDTO = new ArrayResultDTO<>();
        List<SerieDTO> listSerie = new ArrayList<>();
        try{
            Page<SerieEntity> rawDatas = serieRepository.findAll(PageRequest.of(page, pageSize));
            if (rawDatas != null) {
                if (!rawDatas.getContent().isEmpty()) {
                    rawDatas.getContent().forEach(Serie -> {
                        SerieDTO SerieDTO = modelMapper.map(Serie, SerieDTO.class);
                        listSerie.add(SerieDTO);
                    });
                }
                responseResultDTO.setSuccess(listSerie, rawDatas.getTotalElements(), rawDatas.getTotalPages());
                logger.info("=== FIND ALL USERS RESPONSE::" + responseResultDTO.getErrorCode());
            }
        }catch (Exception ex) {
            responseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO addSerie(SerieDTO SerieDTO) {
        logger.info("=== START ADD NEW Serie::");
        BaseResultDTO responseResultDTO = new BaseResultDTO();
        try {
            if ((serieRepository.findSerieEntityBySerieName(SerieDTO.getSerieName())) != null) {
                responseResultDTO.setFail("-3", "Phim này đã tồn tại !!!!");
                logger.info("=== ADD NEW Serie STOP RESPONES: " + responseResultDTO.getErrorCode());
                return responseResultDTO;
            }
            logger.info("a\t\t==="+SerieDTO.getSerieName()+"\t\t");

            SerieEntity Serie = modelMapper.map(SerieDTO, SerieEntity.class);
            Serie = serieRepository.save(Serie);
            if (Serie != null) {
                logger.info("new Serie id = " + Serie.getSerieID());
                responseResultDTO.setSuccess();
            }
            logger.info("=== ADD NEW Serie RESPONSE::" + responseResultDTO.getErrorCode());
        } catch (Exception ex) {
            responseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO updateSerie(SerieDTO SerieDTO) {
        logger.info("=== START UPDATE Serie::");
        SingleResultDTO<SerieEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            SerieEntity Serie = serieRepository.findSerieEntityBySerieID(SerieDTO.getSerieID());
            if(Serie!=null){
                SerieEntity newSerie = modelMapper.map(SerieDTO, SerieEntity.class);
                serieRepository.save(newSerie);
                responseResultDTO.setSuccess();
            }
            logger.info("UPDATE Serie RESPONSE" + responseResultDTO.getErrorCode());
        }catch (Exception ex){
            logger.info("=== UPDATE Serie ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findSerieEntityBySerieName(String SerieName) {
        logger.info("=== START D Serie BY NAME::");
        SingleResultDTO<SerieEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            SerieEntity Serie = serieRepository.findSerieEntityBySerieName(SerieName);
            if(Serie!=null){
                responseResultDTO.setSuccess(Serie);
            }
        }catch (Exception ex){
            logger.info("=== FIND Serie BY NAME ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO deleteSerie(Integer id) {
        logger.info("=== START FIND Serie BY ID::");
        SingleResultDTO<SerieEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            SerieEntity Serie = serieRepository.findSerieEntityBySerieID(id);
            if(Serie!=null){
                serieRepository.delete(Serie);
                responseResultDTO.setSuccess();
                logger.info("DELETE Serie RESPONSE" + responseResultDTO.getErrorCode());
            }
        }catch (Exception ex){
            logger.info("=== DELETE Serie ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findSerieById(Integer id) {
        logger.info("=== START D Serie BY ID::");
        SingleResultDTO<SerieEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            SerieEntity Serie = serieRepository.findSerieEntityBySerieID(id);
            if(Serie!=null){
                responseResultDTO.setSuccess(Serie);
            }
        }catch (Exception ex){
            logger.info("=== FIND Serie BY ID ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findAll() {
        logger.info("=== START FIND ALL ENTITY::");
        ArrayResultDTO<SerieDTO> responseResultDTO = new ArrayResultDTO<>();
        List<SerieDTO> listSerie = new ArrayList<>();
        try{
            ArrayList<SerieEntity> SerieList = (ArrayList<SerieEntity>) serieRepository.findAll();
            if(!SerieList.isEmpty()){
                logger.info("=== FOUND::");
                for (SerieEntity serie : SerieList) {
                    SerieDTO serieDTO = modelMapper.map(serie, SerieDTO.class);
                    listSerie.add(serieDTO);
                }
                responseResultDTO.setSuccess(listSerie);
            }
        }catch (Exception ex){
            logger.info("=== FAILED::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findSeriesByGenre(String genre) {
        logger.info("=== START FIND Serie BY GENRE::");
        ArrayResultDTO<SerieDTO> responseResultDTO = new ArrayResultDTO<>();
        List<SerieDTO> listSerie = new ArrayList<>();
        try{
            ArrayList<SerieEntity> SerieList= (ArrayList<SerieEntity>) serieRepository.findSerieEntitiesBySerieGenre(genre);
            if(!SerieList.isEmpty()){
                for (SerieEntity serie : SerieList) {
                    SerieDTO serieDTO = modelMapper.map(serie, SerieDTO.class);
                    listSerie.add(serieDTO);
                }
                responseResultDTO.setSuccess(listSerie);
            }
        }catch (Exception ex){
            logger.info("=== FIND Serie BY GENRE ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findSerieEpisode(Integer id) {
        logger.info("=== START FIND SERIE EPISODE::");
        ArrayResultDTO<EpisodeDTO> responseResultDTO = new ArrayResultDTO<>();
        List<EpisodeDTO> listEpisode = new ArrayList<>();
        try{
            ArrayList<EpisodeEntity> episodeEntities= (ArrayList<EpisodeEntity>) episodeRepository.findEpisodeEntitiesBySerieID(id);
            if(!episodeEntities.isEmpty()){
                for (EpisodeEntity episode : episodeEntities) {
                    EpisodeDTO episodeDTO = modelMapper.map(episode, EpisodeDTO.class);
                    listEpisode.add(episodeDTO);
                }
                responseResultDTO.setSuccess(listEpisode);
            }
        }catch (Exception ex){
            logger.info("=== FIND SERIE EPISODE ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }
}
