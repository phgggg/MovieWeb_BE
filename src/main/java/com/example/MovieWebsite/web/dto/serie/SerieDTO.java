package com.example.MovieWebsite.web.dto.serie;

import com.example.MovieWebsite.entity.ActorEntity;
import com.example.MovieWebsite.entity.GenreEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class SerieDTO {
    private Integer serieID;
    private String serieName;
    private String originalSerieName;
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
    private String runtime;
    private String originalLanguage;
    private String tags;
    private String adult;
    private String genreID;
    private String firstAirDate;
    private String lastAirDate;
    private String type;
    private Integer numberOfSeasons;
    private Integer numberOfEpisodes;
    @JsonIgnore
    private List<GenreEntity> genres;
    @JsonIgnore
    private List<ActorEntity> actors;
}
