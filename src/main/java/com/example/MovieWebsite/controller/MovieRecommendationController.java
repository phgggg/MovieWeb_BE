package com.example.MovieWebsite.controller;

import com.example.MovieWebsite.repository.MovieRepository;
import com.example.MovieWebsite.repository.SerieRepository;
import com.example.MovieWebsite.utils.MovieRecommendationClient;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.response.MediaDTO;
import com.example.MovieWebsite.web.dto.response.SingleResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/recommendations")
public class MovieRecommendationController extends BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MovieRecommendationClient recommendationClient;

    @Autowired
    public MovieRecommendationController(MovieRepository movieRepository, SerieRepository serieRepository) {
        this.recommendationClient = new MovieRecommendationClient(movieRepository, serieRepository);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<BaseResultDTO> getRecommendationsForUser(@PathVariable int userId) {
        SingleResultDTO<MediaDTO> result = new SingleResultDTO<>();
        try {
            logger.info("find for user " + userId);
            MediaDTO recommendedList = recommendationClient.getRecommendedList(userId);
            result.setSuccess(recommendedList);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.setFail(e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

}