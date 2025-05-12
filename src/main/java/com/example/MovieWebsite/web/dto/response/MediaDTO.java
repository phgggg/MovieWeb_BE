package com.example.MovieWebsite.web.dto.response;

import com.example.MovieWebsite.web.dto.movie.MovieDTO;
import com.example.MovieWebsite.web.dto.serie.SerieDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class MediaDTO {
    ArrayList<MovieDTO> movieList;
    ArrayList<SerieDTO> serieList;
}
