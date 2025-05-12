package com.example.MovieWebsite.services.impl;

import com.example.MovieWebsite.entity.ActorEntity;
import com.example.MovieWebsite.services.ActorService;
import com.example.MovieWebsite.services.BaseService;
import com.example.MovieWebsite.web.dto.actor.ActorDTO;
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
public class ActorServiceImpl extends BaseService implements ActorService {
    @Override
    public BaseResultDTO findAllActors(Integer pageSize, Integer page) {
        logger.info("=== START FIND ALL ACTORS::");
        ArrayResultDTO<ActorDTO> responseResultDTO = new ArrayResultDTO<>();
        List<ActorDTO> listActor = new ArrayList<>();
        try{
            Page<ActorEntity> rawDatas = actorRepository.findAll(PageRequest.of(page, pageSize));
            if (rawDatas != null) {
                if (!rawDatas.getContent().isEmpty()) {
                    rawDatas.getContent().forEach(actor -> {
                        ActorDTO actorDTO = modelMapper.map(actor, ActorDTO.class);
                        listActor.add(actorDTO);
                    });
                }
                responseResultDTO.setSuccess(listActor, rawDatas.getTotalElements(), rawDatas.getTotalPages());
                logger.info("=== FIND ALL USERS RESPONSE::" + responseResultDTO.getErrorCode());
            }
        }catch (Exception ex) {
            responseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO addActor(ActorDTO actorDTO) {
        logger.info("=== START ADD NEW ACTOR::");
        BaseResultDTO responseResultDTO = new BaseResultDTO();
        try {
            if ((actorRepository.findActorEntityByActorName(actorDTO.getActorName())) != null) {
                responseResultDTO.setFail("-3", "diễn viên này đã tồn tại !!!!");
                logger.info("=== ADD NEW ACTOR STOP RESPONES: " + responseResultDTO.getErrorCode());
                return responseResultDTO;
            }
            logger.info("a\t\t==="+actorDTO.getActorName()+"\t\t");

            ActorEntity actor = modelMapper.map(actorDTO, ActorEntity.class);
            actor = actorRepository.save(actor);
            if (actor != null) {
                logger.info("new actor id = " + actor.getActorID());
                responseResultDTO.setSuccess();
            }
            logger.info("=== ADD NEW ACTOR RESPONSE::" + responseResultDTO.getErrorCode());
        } catch (Exception ex) {
            responseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO updateActor(ActorDTO actorDTO) {
        logger.info("=== START UPDATE ACTOR::");
        SingleResultDTO<ActorEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            ActorEntity actor = actorRepository.findActorEntityByActorID(actorDTO.getActorID());
            if(actor!=null){
                ActorEntity newActor = modelMapper.map(actorDTO, ActorEntity.class);
                actorRepository.save(newActor);
                responseResultDTO.setSuccess();
            }
            logger.info("UPDATE ACTOR RESPONSE" + responseResultDTO.getErrorCode());
        }catch (Exception ex){
            logger.info("=== UPDATE ACTOR ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findActorEntityByActorName(String actorName) {
        logger.info("=== START D ACTOR BY NAME::");
        SingleResultDTO<ActorEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            ActorEntity actor = actorRepository.findActorEntityByActorName(actorName);
            if(actor!=null){
                responseResultDTO.setSuccess(actor);
            }
        }catch (Exception ex){
            logger.info("=== FIND ACTOR BY NAME ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO deleteActor(Integer id) {
        logger.info("=== START FIND ACTOR BY ID::");
        SingleResultDTO<ActorEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            ActorEntity actor = actorRepository.findActorEntityByActorID(id);
            if(actor!=null){
                actorRepository.delete(actor);
                responseResultDTO.setSuccess();
                logger.info("DELETE ACTOR RESPONSE" + responseResultDTO.getErrorCode());
            }
        }catch (Exception ex){
            logger.info("=== DELETE ACTOR ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findActorById(Integer id) {
        logger.info("=== START D ACTOR BY ID::");
        SingleResultDTO<ActorEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            ActorEntity actor = actorRepository.findActorEntityByActorID(id);
            if(actor!=null){
                responseResultDTO.setSuccess(actor);
            }
        }catch (Exception ex){
            logger.info("=== FIND ACTOR BY ID ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findAll() {
        logger.info("=== START FIND ALL ENTITY::");
        ArrayResultDTO<ActorEntity> responseResultDTO = new ArrayResultDTO<>();
        try{
            ArrayList<ActorEntity> actorList = (ArrayList<ActorEntity>) actorRepository.findAll();
            if(!actorList.isEmpty()){
                responseResultDTO.setSuccess(actorList);
            }
        }catch (Exception ex){
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }
}
