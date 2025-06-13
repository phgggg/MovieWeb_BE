package com.example.MovieWebsite.web.dto.serie;

import com.example.MovieWebsite.entity.SeasonEntity;
import com.example.MovieWebsite.entity.SerieEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EpisodeDTO {
    private Integer episodeID;
    private String episodeName;
    private Integer episodeNumber;
    private String overview;
    private String posterPath;
    private String trailer;
    private Float voteAverage;
    private Integer voteCount;
    private Integer runtime;
    @JsonIgnore
    private SeasonEntity season;
    private SerieEntity series;
}
