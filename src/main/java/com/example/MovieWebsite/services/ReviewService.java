package com.example.MovieWebsite.services;

import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.user.ReviewRequestDTO;


public interface ReviewService {
    BaseResultDTO findAllReviews(Integer pageSize, Integer page);
    BaseResultDTO addReview(ReviewRequestDTO ReviewDTO);
    BaseResultDTO updateReview(ReviewRequestDTO ReviewDTO);
    BaseResultDTO deleteReview(Integer id);
    BaseResultDTO findAll();
    BaseResultDTO findAllByMovieId(Integer id);
    BaseResultDTO findAllBySerieId(Integer id);
}
