package com.example.MovieWebsite.web.dto.Playlist;

import com.example.MovieWebsite.entity.PlaylistEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Playlist_ItemDTO {
    Integer itemID;
    @JsonIgnore
    PlaylistEntity playlist;
    Integer movieID;
    Integer serieID;
}
