package com.example.MovieWebsite.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWatchHistoryDTO {
    private Integer historyId;
    private Integer userId;
    private Integer movieId;
    private Integer serieId;
    private Integer episodeId;
    private LocalDateTime watchedAt;
    private Integer watchDuration;
    private Boolean isCompleted;
}
