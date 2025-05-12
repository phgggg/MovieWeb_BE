package com.example.MovieWebsite.web.dto.movie;

import com.example.MovieWebsite.entity.ActorEntity;
import com.example.MovieWebsite.entity.GenreEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class MovieDTO {
    private Integer movieID;
    private String movieName;
    private String originalMovieName;
    private String status;
    private String tagline;
    private String overview;
    private String homepage;
    private String popularity;
    private Float voteAverage;
    private Integer voteCount;
    private String posterPath;
    private String backdropPath;
    private String trailer;
    private Integer runtime;
    private String originalLanguage;
    private String tags;
    private String adult;
    private Integer genreID;
    private String releaseDate;
    private Integer budget;
    private Integer revenue;
    @JsonIgnore
    private List<GenreEntity> genres;
    @JsonIgnore
    private List<ActorEntity> actors;
}
