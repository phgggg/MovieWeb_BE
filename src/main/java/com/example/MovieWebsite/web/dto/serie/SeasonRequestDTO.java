package com.example.MovieWebsite.web.dto.serie;

import com.example.MovieWebsite.entity.EpisodeEntity;
import com.example.MovieWebsite.entity.SerieEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SeasonRequestDTO {
    private Integer seasonID;
    private String seasonName;
    private Integer seasonNumber;
    private String overview;
    private String posterPath;
    private String trailer;
    private Float voteAverage;
    private Integer voteCount;
    private Integer serieID;
}
