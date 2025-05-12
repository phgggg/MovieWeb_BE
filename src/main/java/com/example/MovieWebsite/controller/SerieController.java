package com.example.MovieWebsite.controller;

import com.example.MovieWebsite.services.SerieService;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.serie.SerieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/serie")
public class SerieController extends BaseController {
    @Autowired
    SerieService SeriesService;

    @RequestMapping(value = "/findAllSerie", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> allSeries(@RequestParam("pageSize") Integer pageSize,
                                               @RequestParam("page") Integer page) {
        BaseResultDTO result = SeriesService.findAllSeries(pageSize, page);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findAllSerie() {
        BaseResultDTO result = SeriesService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/addSerie", method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> addSerie(@RequestBody SerieDTO requestDTO) {
        BaseResultDTO result = SeriesService.addSerie(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateSerie", method = RequestMethod.PUT)
    public ResponseEntity<BaseResultDTO> updateSerie(@RequestBody SerieDTO requestDTO) {
        BaseResultDTO result = SeriesService.updateSerie(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/findBySerieName", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findSerieEntityBySerieName(@RequestParam("SerieName") String requestDTO) {
        BaseResultDTO result = SeriesService.findSerieEntityBySerieName(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteSerie/{id}", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> deleteSerie(@PathVariable Integer id) {
        BaseResultDTO result = SeriesService.deleteSerie(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/findBySerieID/{id}", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findSeriebyId(@PathVariable Integer id) {
        BaseResultDTO result = SeriesService.findSerieById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/findBySerieGenre/{genre}", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findBySerieGenre(@PathVariable String genre) {
        BaseResultDTO result = SeriesService.findSeriesByGenre(genre);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/findSerieEpisode/{id}", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findSerieEpisode(@PathVariable Integer id) {
        BaseResultDTO result = SeriesService.findSerieEpisode(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
