package com.example.MovieWebsite.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_watch_history")
@Data
public class UserWatchHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Integer historyId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "serie_id")
    private Integer serieId;

    @Column(name = "episode_id")
    private Integer episodeId;

    @Column(name = "watched_at")
    private LocalDateTime watchedAt;

    @Column(name = "watch_duration")
    private Integer watchDuration; // thời lượng xem (giây)

    @Column(name = "is_completed")
    private Boolean isCompleted;

}
