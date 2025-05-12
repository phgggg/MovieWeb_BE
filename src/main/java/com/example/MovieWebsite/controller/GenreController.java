package com.example.MovieWebsite.controller;
import com.example.MovieWebsite.services.GenreService;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/genre")
public class GenreController extends BaseController {

    @Autowired
    GenreService genreService;

    @RequestMapping(value = "/findAllGenre", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> allGenres() {
        BaseResultDTO result = genreService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
