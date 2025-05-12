package com.example.MovieWebsite.web.dto.Playlist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistRequestDTO {
    private Integer userID;
    private Playlist_ItemDTO playlistItemDTO;
}
