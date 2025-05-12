package com.example.MovieWebsite.controller;

import com.example.MovieWebsite.services.PlaylistService;
import com.example.MovieWebsite.web.dto.Playlist.MediaIDRequest;
import com.example.MovieWebsite.web.dto.Playlist.PlaylistRequestDTO;
import com.example.MovieWebsite.web.dto.Playlist.Playlist_ItemDTO;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.user.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {
    @Autowired
    PlaylistService playlistService;
    @RequestMapping(value = "/addNewPlaylist/{id}", method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> addNew(@PathVariable Integer id) {
        BaseResultDTO result = playlistService.addPlaylist(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/addToPlaylist", method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> addToPlaylist(@RequestBody PlaylistRequestDTO playlistRequestDTO) {
        Integer userID = playlistRequestDTO.getUserID();
        Playlist_ItemDTO playlistItemDTO = playlistRequestDTO.getPlaylistItemDTO();
        BaseResultDTO result = playlistService.addToPlaylist(userID, playlistItemDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/getPlayListItemFromUser/{id}", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> getPlayListItemFromUser(@PathVariable Integer id) {
        BaseResultDTO result = playlistService.getPlaylistItem(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteItemFromPlaylist", method = RequestMethod.DELETE)
    public ResponseEntity<BaseResultDTO> deleteItemFromPlaylist(@RequestBody MediaIDRequest id) {
        BaseResultDTO result = playlistService.deleteItemFromPlaylist(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
