package com.example.MovieWebsite.services.impl;

import com.example.MovieWebsite.entity.EpisodeEntity;
import com.example.MovieWebsite.entity.MovieEntity;
import com.example.MovieWebsite.entity.SeasonEntity;
import com.example.MovieWebsite.entity.SerieEntity;
import com.example.MovieWebsite.repository.SeasonRepository;
import com.example.MovieWebsite.services.BaseService;
import com.example.MovieWebsite.services.SerieService;
import com.example.MovieWebsite.web.dto.movie.MovieDTO;
import com.example.MovieWebsite.web.dto.serie.*;
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

    @Override
    public BaseResultDTO findAllEpisode() {
        logger.info("=== START FIND ALL Episode::");
        ArrayResultDTO<EpisodeRequestDTO> responseResultDTO = new ArrayResultDTO<>();
        List<EpisodeRequestDTO> listEpisode = new ArrayList<>();
        try{
            ArrayList<EpisodeEntity> episodeEntities = (ArrayList<EpisodeEntity>) episodeRepository.findAll();
            if(!episodeEntities.isEmpty()){
                logger.info("=== FOUND Episode::");
                for (EpisodeEntity episode : episodeEntities) {
                    EpisodeRequestDTO episodeDTO = modelMapper.map(episode, EpisodeRequestDTO.class);
                    episodeDTO.setSeasonID(episode.getSeason().getSeasonID());
                    episodeDTO.setSerieID(episode.getSeries().getSerieID());
                    listEpisode.add(episodeDTO);
                }
                responseResultDTO.setSuccess(listEpisode);
            }
        }catch (Exception ex){
            logger.info("=== FAILED FIND Episode::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO addEpisode(EpisodeRequestDTO episodeDTO) {
        logger.info("=== START ADD NEW Episode::");
        BaseResultDTO responseResultDTO = new BaseResultDTO();
        try {
            SeasonEntity season = seasonRepository.findSeasonEntityBySeasonID(episodeDTO.getSeasonID());
            SerieEntity serie = serieRepository.findSerieEntityBySerieID(episodeDTO.getSerieID());
            EpisodeEntity episode = modelMapper.map(episodeDTO, EpisodeEntity.class);
            episode.setSeries(serie);
            episode.setSeason(season);
            episode = episodeRepository.save(episode);
            if (episode != null) {
                logger.info("new Episode id = " + episode.getEpisodeID());
                responseResultDTO.setSuccess();
            }
            logger.info("=== ADD NEW Episode RESPONSE::" + responseResultDTO.getErrorCode());
        } catch (Exception ex) {
            responseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO updateEpisode(EpisodeRequestDTO episodeDTO) {
        logger.info("=== START UPDATE Episode::");
        SingleResultDTO<EpisodeEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            SeasonEntity season = seasonRepository.findSeasonEntityBySeasonID(episodeDTO.getSeasonID());
            SerieEntity serie = serieRepository.findSerieEntityBySerieID(episodeDTO.getSerieID());
            EpisodeEntity episode = episodeRepository.findEpisodeEntityByEpisodeID(episodeDTO.getEpisodeID());
            if(episode!=null){
                EpisodeEntity newEpisode = modelMapper.map(episodeDTO, EpisodeEntity.class);
                newEpisode.setSeries(serie);
                newEpisode.setSeason(season);
                episodeRepository.save(newEpisode);
                responseResultDTO.setSuccess();
            }
            logger.info("UPDATE Episode RESPONSE" + responseResultDTO.getErrorCode());
        }catch (Exception ex){
            logger.info("=== UPDATE Episode ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO deleteEpisode(Integer id) {
        logger.info("=== START FIND Episode BY ID::");
        SingleResultDTO<EpisodeEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            EpisodeEntity episode = episodeRepository.findEpisodeEntityByEpisodeID(id);
            if(episode!=null){
                episodeRepository.delete(episode);
                responseResultDTO.setSuccess();
                logger.info("DELETE Episode RESPONSE" + responseResultDTO.getErrorCode());
            }
        }catch (Exception ex){
            logger.info("=== DELETE Episode ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findAllSeason() {
        logger.info("=== START FIND ALL Season::");
        ArrayResultDTO<SeasonRequestDTO> responseResultDTO = new ArrayResultDTO<>();
        List<SeasonRequestDTO> listSeason = new ArrayList<>();
        try{
            ArrayList<SeasonEntity> seasonEntities = (ArrayList<SeasonEntity>) seasonRepository.findAll();
            if(!seasonEntities.isEmpty()){
                logger.info("=== FOUND Season::");
                for (SeasonEntity season : seasonEntities) {
                    SeasonRequestDTO seasonDTO = modelMapper.map(season, SeasonRequestDTO.class);
                    seasonDTO.setSerieID(season.getSeries().getSerieID());
                    listSeason.add(seasonDTO);
                }
                responseResultDTO.setSuccess(listSeason);
            }
        }catch (Exception ex){
            logger.info("=== FAILED FIND Season::" + ex.getMessage());
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO addSeason(SeasonRequestDTO seasonDTO) {
        logger.info("=== START ADD NEW Season::");
        BaseResultDTO responseResultDTO = new BaseResultDTO();
        try {
            SerieEntity serie = serieRepository.findSerieEntityBySerieID(seasonDTO.getSerieID());
            SeasonEntity season = modelMapper.map(seasonDTO, SeasonEntity.class);
            season.setSeries(serie);
            season = seasonRepository.save(season);
            if (season != null) {
                logger.info("new Season id = " + season.getSeasonID());
                responseResultDTO.setSuccess();
            }
            logger.info("=== ADD NEW Season RESPONSE::" + responseResultDTO.getErrorCode());
        } catch (Exception ex) {
            responseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO updateSeason(SeasonRequestDTO seasonDTO) {
        logger.info("=== START UPDATE Season::");
        SingleResultDTO<SeasonEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            SerieEntity serie = serieRepository.findSerieEntityBySerieID(seasonDTO.getSerieID());
            SeasonEntity season = seasonRepository.findSeasonEntityBySeasonID(seasonDTO.getSeasonID());
            if(season!=null){
                SeasonEntity newSeason = modelMapper.map(seasonDTO, SeasonEntity.class);
                newSeason.setSeries(serie);
                seasonRepository.save(newSeason);
                responseResultDTO.setSuccess();
            }
            logger.info("UPDATE Season RESPONSE" + responseResultDTO.getErrorCode());
        }catch (Exception ex){
            logger.info("=== UPDATE Season ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO deleteSeason(Integer id) {
        logger.info("=== START FIND Season BY ID::");
        SingleResultDTO<SeasonEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            SeasonEntity season = seasonRepository.findSeasonEntityBySeasonID(id);
            if(season!=null){
                seasonRepository.delete(season);
                responseResultDTO.setSuccess();
                logger.info("DELETE Season RESPONSE" + responseResultDTO.getErrorCode());
            }
        }catch (Exception ex){
            logger.info("=== DELETE Season ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }
}
