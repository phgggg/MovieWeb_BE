package com.example.MovieWebsite.controller;
import com.example.MovieWebsite.services.GenreService;
import com.example.MovieWebsite.web.dto.genre.GenreDTO;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/addGenre", method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> addGenre(@RequestBody GenreDTO requestDTO) {
        BaseResultDTO result = genreService.addGenre(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateGenre", method = RequestMethod.PUT)
    public ResponseEntity<BaseResultDTO> updateGenre(@RequestBody GenreDTO requestDTO) {
        BaseResultDTO result = genreService.updateGenre(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteGenre/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<BaseResultDTO> deleteGenre(@PathVariable Integer id) {
        BaseResultDTO result = genreService.deleteGenre(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
