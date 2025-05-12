package com.example.MovieWebsite.web.dto.Playlist;

import com.example.MovieWebsite.entity.UserEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistDTO {
    private Integer playlistID;
    private String playlistName;
    private UserEntity user;
}
