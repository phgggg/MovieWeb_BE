package com.example.MovieWebsite.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "genres", schema = "moviewebsite")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer genreID;
    private String genreName;
    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private List<MovieEntity> movies;
    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private List<SerieEntity> series;
}