package com.example.MovieWebsite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "episode", schema = "moviewebsite")
public class EpisodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "episodeID")
    private Integer episodeID;

    @Column(name = "episodeName", length = 45)
    private String episodeName;

    @Column(name = "episodeNumber")
    private Integer episodeNumber;

    @Column(name = "overview", columnDefinition = "TEXT")
    private String overview;

    @Column(name = "posterPath", length = 45)
    private String posterPath;

    @Column(name = "trailer", length = 45)
    private String trailer;

    @Column(name = "voteAverage")
    private Float voteAverage;

    @Column(name = "voteCount")
    private Integer voteCount;

    @Column(name = "runtime")
    private Integer runtime;

    @ManyToOne
    @JoinColumn(name = "seasonID", referencedColumnName = "seasonID")
    private SeasonEntity season;

    @ManyToOne
    @JoinColumn(name = "serieID", referencedColumnName = "serieID")
    private SerieEntity series;
}
