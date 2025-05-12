package com.example.MovieWebsite.services;

import com.example.MovieWebsite.web.dto.actor.ActorDTO;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;

public interface ActorService {
    BaseResultDTO findAllActors(Integer pageSize, Integer page);
    BaseResultDTO addActor(ActorDTO actorDTO);
    BaseResultDTO updateActor(ActorDTO actorDTO);
    BaseResultDTO findActorEntityByActorName(String actorName);
    BaseResultDTO deleteActor(Integer id);
    BaseResultDTO findActorById(Integer id);
    BaseResultDTO findAll();
}
