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
@Table(name = "reviews", schema = "moviewebsite")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewID;
    @Lob
    private String content;
    private Float rating;
    private String timestamp;

    @ManyToOne
    @JoinColumn(name = "movieID")
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name = "serieID")
    private SerieEntity serie;

    @ManyToOne
    @JoinColumn(name = "userID")
    private UserEntity user;
}