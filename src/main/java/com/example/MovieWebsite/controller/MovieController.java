package com.example.MovieWebsite.controller;

import com.example.MovieWebsite.services.MovieService;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.movie.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie")
public class MovieController extends BaseController {
    @Autowired
    MovieService MoviesService;

    @RequestMapping(value = "/findAllMovie", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> allMovies(@RequestParam("pageSize") Integer pageSize,
                                               @RequestParam("page") Integer page) {
        BaseResultDTO result = MoviesService.findAllMovies(pageSize, page);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findAllMovie() {
        BaseResultDTO result = MoviesService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/addMovie", method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> addMovie(@RequestBody MovieDTO requestDTO) {
        BaseResultDTO result = MoviesService.addMovie(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateMovie", method = RequestMethod.PUT)
    public ResponseEntity<BaseResultDTO> updateMovie(@RequestBody MovieDTO requestDTO) {
        BaseResultDTO result = MoviesService.updateMovie(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByMovieName", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findMovieEntityByMovieName(@RequestParam("MovieName") String requestDTO) {
        BaseResultDTO result = MoviesService.findMovieEntityByMovieName(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteMovie/{id}", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> deleteMovie(@PathVariable Integer id) {
        BaseResultDTO result = MoviesService.deleteMovie(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByMovieID/{id}", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findMoviebyId(@PathVariable Integer id) {
        BaseResultDTO result = MoviesService.findMovieById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByMovieGenre/{genre}", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findByMovieGenre(@PathVariable String genre) {
        BaseResultDTO result = MoviesService.findMoviesByGenre(genre);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
