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
@Table(name = "actors", schema = "moviewebsite")
public class ActorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actorID;
    private String actorName;
    private LocalDateTime birthday;
    private String gender;
    private String placeOfBirth;
    @Lob
    private String biography;
    private String knownForDepartment;
    private BigDecimal popularity;
    private String profilePath;
    private String imdbID;
    private String originalName;

    @ManyToMany(mappedBy = "actors")
    private List<MovieEntity> movies;

    @ManyToMany(mappedBy = "actors")
    private List<SerieEntity> series;
}