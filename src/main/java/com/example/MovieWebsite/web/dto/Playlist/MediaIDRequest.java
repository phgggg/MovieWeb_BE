package com.example.MovieWebsite.web.dto.Playlist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MediaIDRequest {
    private Integer movieID;
    private Integer serieID;
    private Integer userID;
}
