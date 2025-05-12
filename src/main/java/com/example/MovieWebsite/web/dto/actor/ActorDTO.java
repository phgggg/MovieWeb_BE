package com.example.MovieWebsite.web.dto.actor;

import com.example.MovieWebsite.entity.MovieEntity;
import com.example.MovieWebsite.entity.SerieEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ActorDTO {
    private Integer actorID;
    private String actorName;
    private LocalDateTime birthday;
    private String gender;
    private String placeOfBirth;
    private String biography;
    private String knownForDepartment;
    private BigDecimal popularity;
    private String profilePath;
    private String imdbID;
    private String originalName;
    @JsonIgnore
    private List<MovieEntity> movies;
    @JsonIgnore
    private List<SerieEntity> series;
}
