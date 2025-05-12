package com.example.MovieWebsite.controller;

import com.example.MovieWebsite.services.ReviewService;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;

import com.example.MovieWebsite.web.dto.user.ReviewRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class ReviewController extends BaseController {
    @Autowired
    ReviewService ReviewsService;

    @RequestMapping(value = "/findAllReview", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> allReviews(@RequestParam("pageSize") Integer pageSize,
                                               @RequestParam("page") Integer page) {
        BaseResultDTO result = ReviewsService.findAllReviews(pageSize, page);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findAllReview() {
        BaseResultDTO result = ReviewsService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByMovieId/{id}", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findAllReviewByMovieId(@PathVariable Integer id) {
        BaseResultDTO result = ReviewsService.findAllByMovieId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/findBySerieId/{id}", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findAllReviewBySerieId(@PathVariable Integer id) {
        BaseResultDTO result = ReviewsService.findAllBySerieId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/addReview", method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> addReview(@RequestBody ReviewRequestDTO requestDTO) {
        BaseResultDTO result = ReviewsService.addReview(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateReview", method = RequestMethod.PUT)
    public ResponseEntity<BaseResultDTO> updateReview(@RequestBody ReviewRequestDTO requestDTO) {
        BaseResultDTO result = ReviewsService.updateReview(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteReview/{id}", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> deleteReview(@PathVariable Integer id) {
        BaseResultDTO result = ReviewsService.deleteReview(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
