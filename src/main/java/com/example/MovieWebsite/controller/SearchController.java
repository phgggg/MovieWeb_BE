package com.example.MovieWebsite.controller;

import com.example.MovieWebsite.services.SearchService;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    SearchService searchService;

    @RequestMapping(value = "/{keyword}", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findByMovieGenre(@PathVariable String keyword) {
        BaseResultDTO result = searchService.search(keyword);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
