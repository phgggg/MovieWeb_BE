package com.example.MovieWebsite.web.dto.user;

import com.example.MovieWebsite.entity.MovieEntity;
import com.example.MovieWebsite.entity.SerieEntity;
import com.example.MovieWebsite.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDTO {
    private Integer reviewID;
    private String content;
    private Float rating;
    private String timestamp;
    private Integer movie;
    private Integer serie;
    private Integer user;
}
