package com.example.MovieWebsite.controller;

import com.example.MovieWebsite.services.ActorService;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/actor")
public class ActorController extends BaseController {
/*
http://localhost:8888/api/actor/findAllActors
http://localhost:8888/api/actor/actorByName/George Lucas
*/
    @Autowired
    ActorService actorService;

    @RequestMapping(value = "/findAllActors", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> allActors() {
        BaseResultDTO result = actorService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/actorByName/{name}", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> actorByName(@PathVariable String name) {
        BaseResultDTO result = actorService.findActorEntityByActorName(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}