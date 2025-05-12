package com.example.MovieWebsite.web.dto.genre;
import com.example.MovieWebsite.entity.MovieEntity;
import com.example.MovieWebsite.entity.SerieEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenreDTO {
    private Integer genreID;
    private String genreName;
    @JsonIgnore
    private List<MovieEntity> movies;
    @JsonIgnore
    private List<SerieEntity> series;
}
