package com.example.MovieWebsite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "season", schema = "moviewebsite")
public class SeasonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seasonID")
    private Integer seasonID;

    @Column(name = "seasonName", length = 100)
    private String seasonName;

    @Column(name = "seasonNumber")
    private Integer seasonNumber;

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

    @ManyToOne
    @JoinColumn(name = "serieID", referencedColumnName = "serieID")
    private SerieEntity series;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EpisodeEntity> episodes;
}
