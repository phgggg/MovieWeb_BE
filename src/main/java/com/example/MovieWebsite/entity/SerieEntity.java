package com.example.MovieWebsite.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "series", schema = "moviewebsite")
public class SerieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serieID;
    private String serieName;
    private String originalSerieName;
    private String status;
    private String tagline;
    @Lob
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

    @ManyToMany
    @JoinTable(
            name = "genres_series",
            schema = "moviewebsite",
            joinColumns = @JoinColumn(name = "serieID"),
            inverseJoinColumns = @JoinColumn(name = "genreID")
    )
    private List<GenreEntity> genres;

    @ManyToMany
    @JoinTable(
            name = "actors_series",
            schema = "moviewebsite",
            joinColumns = @JoinColumn(name = "serieID"),
            inverseJoinColumns = @JoinColumn(name = "actorID")
    )
    private List<ActorEntity> actors;
}
