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
@Table(name = "playlist_item", schema = "moviewebsite")
public class Playlist_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer itemID;
    @ManyToOne
    @JoinColumn(name = "playlistID")
    PlaylistEntity playlist;
    Integer movieID;
    Integer serieID;
}
