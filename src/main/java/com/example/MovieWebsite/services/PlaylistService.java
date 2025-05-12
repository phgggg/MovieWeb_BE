package com.example.MovieWebsite.services;

import com.example.MovieWebsite.web.dto.Playlist.MediaIDRequest;
import com.example.MovieWebsite.web.dto.Playlist.Playlist_ItemDTO;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.Playlist.PlaylistDTO;


public interface PlaylistService {
    BaseResultDTO findAllPlaylists(Integer pageSize, Integer page);
    BaseResultDTO addPlaylist(Integer userID);
    BaseResultDTO updatePlaylist(PlaylistDTO PlaylistDTO);
    BaseResultDTO addToPlaylist(Integer userID, Playlist_ItemDTO PlaylistDTO);
    BaseResultDTO findPlaylistEntityByPlaylistName(String PlaylistName);
    BaseResultDTO deletePlaylist(Integer id);
    BaseResultDTO findAll();
    BaseResultDTO getPlaylistItem(Integer userID);
    BaseResultDTO deleteItemFromPlaylist(MediaIDRequest mediaID);
}
