package com.example.MovieWebsite.services.impl;

import com.example.MovieWebsite.entity.MovieEntity;
import com.example.MovieWebsite.entity.ReviewEntity;
import com.example.MovieWebsite.entity.SerieEntity;
import com.example.MovieWebsite.entity.UserEntity;
import com.example.MovieWebsite.services.BaseService;
import com.example.MovieWebsite.services.ReviewService;
import com.example.MovieWebsite.web.dto.user.ReviewRequestDTO;
import com.example.MovieWebsite.web.dto.response.ArrayResultDTO;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.response.SingleResultDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ReviewServiceImpl extends BaseService implements ReviewService {
    @Override
    public BaseResultDTO findAllReviews(Integer pageSize, Integer page) {
        logger.info("=== START FIND ALL ReviewS::");
        ArrayResultDTO<ReviewRequestDTO> responseResultDTO = new ArrayResultDTO<>();
        List<ReviewRequestDTO> listReview = new ArrayList<>();
        try{
            Page<ReviewEntity> rawDatas = reviewRepository.findAll(PageRequest.of(page, pageSize));
            if (rawDatas != null) {
                if (!rawDatas.getContent().isEmpty()) {
                    rawDatas.getContent().forEach(Review -> {
                        ReviewRequestDTO ReviewDTO = modelMapper.map(Review, ReviewRequestDTO.class);
                        listReview.add(ReviewDTO);
                    });
                }
                responseResultDTO.setSuccess(listReview, rawDatas.getTotalElements(), rawDatas.getTotalPages());
                logger.info("=== FIND ALL USERS RESPONSE::" + responseResultDTO.getErrorCode());
            }
        }catch (Exception ex) {
            responseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO addReview(ReviewRequestDTO ReviewDTO) {
        logger.info("=== START ADD NEW Review::");
        BaseResultDTO responseResultDTO = new BaseResultDTO();
        try {
            logger.info("a\t\t==="+ReviewDTO.getContent()+"\t\t");
            Integer movieID = ReviewDTO.getMovie();
            Integer serieID = ReviewDTO.getSerie();
            Integer userID = ReviewDTO.getUser();
            MovieEntity movie = null;
            SerieEntity serie = null;
            UserEntity user = userRepository.findUserEntityByUserID(userID);
            if(movieID!=null){
                movie = movieRepository.findMovieEntityByMovieID(movieID);
            }else {
                serie = serieRepository.findSerieEntityBySerieID(serieID);
            }
            ReviewEntity Review = new ReviewEntity();
            Review.setUser(user);
            Review.setMovie(movie);
            Review.setSerie(serie);
            Review.setContent(ReviewDTO.getContent());
            Review.setRating(ReviewDTO.getRating());
            Review.setTimestamp(ReviewDTO.getTimestamp());
            Review = reviewRepository.save(Review);
            if (Review != null) {
                logger.info("new Review id = " + Review.getReviewID());
                responseResultDTO.setSuccess();
            }
            logger.info("=== ADD NEW Review RESPONSE::" + responseResultDTO.getErrorCode());
        } catch (Exception ex) {
            responseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO updateReview(ReviewRequestDTO ReviewDTO) {
        logger.info("=== START UPDATE Review::");
        SingleResultDTO<ReviewEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            ReviewEntity Review = reviewRepository.findReviewEntityByReviewID(ReviewDTO.getReviewID());
            if(Review!=null){
                ReviewEntity newReview = modelMapper.map(ReviewDTO, ReviewEntity.class);
                reviewRepository.save(newReview);
                responseResultDTO.setSuccess();
            }
            logger.info("UPDATE Review RESPONSE" + responseResultDTO.getErrorCode());
        }catch (Exception ex){
            logger.info("=== UPDATE Review ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO deleteReview(Integer id) {
        logger.info("=== START FIND Review BY ID::");
        SingleResultDTO<ReviewEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            ReviewEntity Review = reviewRepository.findReviewEntityByReviewID(id);
            if(Review!=null){
                reviewRepository.delete(Review);
                responseResultDTO.setSuccess();
                logger.info("DELETE Review RESPONSE" + responseResultDTO.getErrorCode());
            }
        }catch (Exception ex){
            logger.info("=== DELETE Review ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findAll() {
        logger.info("=== START FIND ALL REVIEW::");
        ArrayResultDTO<ReviewEntity> responseResultDTO = new ArrayResultDTO<>();
        try{
            ArrayList<ReviewEntity> ReviewList = (ArrayList<ReviewEntity>) reviewRepository.findAll();
            if(!ReviewList.isEmpty()){
                responseResultDTO.setSuccess(ReviewList);
            }
        }catch (Exception ex){
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findAllByMovieId(Integer id) {
        logger.info("=== START FIND ALL REVIEW BY MOVIE::");
        ArrayResultDTO<ReviewEntity> responseResultDTO = new ArrayResultDTO<>();
        try{
            ArrayList<ReviewEntity> ReviewList = (ArrayList<ReviewEntity>) reviewRepository.findReviewEntitiesByMovieId(id);
            if(!ReviewList.isEmpty()){
                responseResultDTO.setSuccess(ReviewList);
            }
        }catch (Exception ex){
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findAllBySerieId(Integer id) {
        logger.info("=== START FIND ALL REVIEW BY SERIE::");
        ArrayResultDTO<ReviewEntity> responseResultDTO = new ArrayResultDTO<>();
        try{
            ArrayList<ReviewEntity> ReviewList = (ArrayList<ReviewEntity>) reviewRepository.findReviewEntitiesBySerieId(id);
            if(!ReviewList.isEmpty()){
                responseResultDTO.setSuccess(ReviewList);
            }
        }catch (Exception ex){
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }
}
