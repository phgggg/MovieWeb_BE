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
@Table(name = "movies", schema = "moviewebsite")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieID;
    private String movieName;
    private String originalMovieName;
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
    private Integer runtime;
    private String originalLanguage;
    private String tags;
    private String adult;
    private Integer genreID;
    private String releaseDate;
    private Integer budget;
    private Integer revenue;

    @ManyToMany
    @JoinTable(
            name = "genres_movies",
            schema = "moviewebsite",
            joinColumns = @JoinColumn(name = "movieID"),
            inverseJoinColumns = @JoinColumn(name = "genreID")
    )
    private List<GenreEntity> genres;

    @ManyToMany
    @JoinTable(
            name = "actors_movies",
            schema = "moviewebsite",
            joinColumns = @JoinColumn(name = "movieID"),
            inverseJoinColumns = @JoinColumn(name = "actorID")
    )
    private List<ActorEntity> actors;
}