package com.example.MovieWebsite.services.impl;

import com.example.MovieWebsite.entity.*;
import com.example.MovieWebsite.services.BaseService;
import com.example.MovieWebsite.services.PlaylistService;
import com.example.MovieWebsite.web.dto.Playlist.MediaIDRequest;
import com.example.MovieWebsite.web.dto.Playlist.PlaylistDTO;
import com.example.MovieWebsite.web.dto.Playlist.Playlist_ItemDTO;
import com.example.MovieWebsite.web.dto.movie.MovieDTO;
import com.example.MovieWebsite.web.dto.response.ArrayResultDTO;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.response.MediaDTO;
import com.example.MovieWebsite.web.dto.response.SingleResultDTO;
import com.example.MovieWebsite.web.dto.serie.SerieDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PlaylistServiceImpl extends BaseService implements PlaylistService {
    @Override
    public BaseResultDTO findAllPlaylists(Integer pageSize, Integer page) {
        logger.info("=== START FIND ALL PlaylistS::");
        ArrayResultDTO<PlaylistDTO> responseResultDTO = new ArrayResultDTO<>();
        List<PlaylistDTO> listPlaylist = new ArrayList<>();
        try{
            Page<PlaylistEntity> rawDatas = playlistRepository.findAll(PageRequest.of(page, pageSize));
            if (rawDatas != null) {
                if (!rawDatas.getContent().isEmpty()) {
                    rawDatas.getContent().forEach(Playlist -> {
                        PlaylistDTO PlaylistDTO = modelMapper.map(Playlist, PlaylistDTO.class);
                        listPlaylist.add(PlaylistDTO);
                    });
                }
                responseResultDTO.setSuccess(listPlaylist, rawDatas.getTotalElements(), rawDatas.getTotalPages());
                logger.info("=== FIND ALL USERS RESPONSE::" + responseResultDTO.getErrorCode());
            }
        }catch (Exception ex) {
            responseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO addPlaylist(Integer userID) {
        logger.info("=== START ADD NEW Playlist::");
        BaseResultDTO responseResultDTO = new BaseResultDTO();
        try {
            UserEntity user = userRepository.findUserEntityByUserID(userID);
            PlaylistEntity Playlist = playlistRepository.findPlaylistEntityByUserID(user.getUserID());
            if (Playlist != null) {
                responseResultDTO.setFail("Playlist đã tồn tại: " + Playlist.getUser());
            }
            else {
                PlaylistEntity newPlaylist = new PlaylistEntity();
                newPlaylist.setPlaylistName("playlist for user " + user.getUserID());
                newPlaylist.setUser(user);
                playlistRepository.save(newPlaylist);
                responseResultDTO.setSuccess();
            }
            logger.info("=== ADD NEW Playlist RESPONSE::" + responseResultDTO.getErrorCode());
        } catch (Exception ex) {
            responseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO updatePlaylist(PlaylistDTO PlaylistDTO) {
        logger.info("=== START UPDATE Playlist::");
        SingleResultDTO<PlaylistEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            PlaylistEntity Playlist = playlistRepository.findPlaylistEntityByPlaylistID(PlaylistDTO.getPlaylistID());
            if(Playlist!=null){
                PlaylistEntity newPlaylist = modelMapper.map(PlaylistDTO, PlaylistEntity.class);
                playlistRepository.save(newPlaylist);
                responseResultDTO.setSuccess();
            }
            logger.info("UPDATE Playlist RESPONSE" + responseResultDTO.getErrorCode());
        }catch (Exception ex){
            logger.info("=== UPDATE Playlist ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO addToPlaylist(Integer userID, Playlist_ItemDTO playlistItemDTO) {
        logger.info("=== START ADD TO Playlist::");
        SingleResultDTO<PlaylistEntity> responseResultDTO = new SingleResultDTO<>();
        Integer movieID, serieID;
        try{
            UserEntity user = userRepository.findUserEntityByUserID(userID);
            PlaylistEntity Playlist = playlistRepository.findPlaylistEntityByUserID(user.getUserID());
            Playlist_Item itemMovie = playlistItemRepository.findPlaylist_ItemByMovieIDAndUserID(playlistItemDTO.getMovieID(),userID);
            Playlist_Item itemSerie = playlistItemRepository.findPlaylist_ItemBySerieIDAndUserID(playlistItemDTO.getSerieID(),userID);
            if(Playlist!=null && itemSerie == null && itemMovie == null){
                playlistItemDTO.setPlaylist(Playlist);
                Playlist_Item newItem = modelMapper.map(playlistItemDTO, Playlist_Item.class);
                playlistItemRepository.save(newItem);
                responseResultDTO.setSuccess();
            }else {
                responseResultDTO.setFail("đã thêm vào playlist hoặc playlist không tồn tại!");
            }
            logger.info("ADD TO Playlist RESPONSE" + responseResultDTO.getErrorCode());
        }catch (Exception ex){
            logger.info("=== ADD TO Playlist ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findPlaylistEntityByPlaylistName(String PlaylistName) {
        logger.info("=== START D Playlist BY NAME::");
        SingleResultDTO<PlaylistEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            PlaylistEntity Playlist = playlistRepository.findPlaylistEntityByPlaylistName(PlaylistName);
            if(Playlist!=null){
                responseResultDTO.setSuccess(Playlist);
            }
        }catch (Exception ex){
            logger.info("=== FIND Playlist BY NAME ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO deletePlaylist(Integer id) {
        logger.info("=== START DELETE Playlist BY ID::");
        SingleResultDTO<PlaylistEntity> responseResultDTO = new SingleResultDTO<>();
        try{
            PlaylistEntity Playlist = playlistRepository.findPlaylistEntityByPlaylistID(id);
            if(Playlist!=null){
                playlistRepository.delete(Playlist);
                responseResultDTO.setSuccess();
                logger.info("DELETE Playlist RESPONSE" + responseResultDTO.getErrorCode());
            }
        }catch (Exception ex){
            logger.info("=== DELETE Playlist ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO findAll() {
        logger.info("=== START FIND ALL ENTITY::");
        ArrayResultDTO<PlaylistEntity> responseResultDTO = new ArrayResultDTO<>();
        try{
            ArrayList<PlaylistEntity> PlaylistList = (ArrayList<PlaylistEntity>) playlistRepository.findAll();
            if(!PlaylistList.isEmpty()){
                responseResultDTO.setSuccess(PlaylistList);
            }
        }catch (Exception ex){
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO getPlaylistItem(Integer userID) {
        SingleResultDTO<MediaDTO> responseResultDTO = new SingleResultDTO<>();
        MediaDTO listMedia = new MediaDTO();
        ArrayList<SerieDTO> listSerie = new ArrayList<>();
        ArrayList<MovieDTO> listMovie = new ArrayList<>();
        logger.info("START GET PLAYLIST ITEMS");
        try{
            PlaylistEntity playlist = playlistRepository.findPlaylistEntityByUserID(userID);
            List<Playlist_Item> playlist_items = playlistItemRepository.findPlaylist_ItemByPlaylist(playlist.getPlaylistID());
            for(Playlist_Item item : playlist_items){
                if(item.getMovieID() != null){
                    MovieEntity movie = movieRepository.findMovieEntityByMovieID(item.getMovieID());
                    MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
                    listMovie.add(movieDTO);
                }else{
                    SerieEntity serie = serieRepository.findSerieEntityBySerieID(item.getSerieID());
                    SerieDTO serieDTO = modelMapper.map(serie, SerieDTO.class);
                    listSerie.add(serieDTO);
                }
            }
            listMedia.setSerieList(listSerie);
            listMedia.setMovieList(listMovie);
            responseResultDTO.setSuccess(listMedia);

        }catch (Exception ex){
            logger.info("=== GET PLAYLIST ITEM ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO deleteItemFromPlaylist(MediaIDRequest mediaID) {
        logger.info("=== START DELETE ITEM FROM Playlist BY ID::");
        BaseResultDTO responseResultDTO = new SingleResultDTO<>();
        try{
            Playlist_Item item = null;
            Integer movieID = mediaID.getMovieID(), serieID = mediaID.getSerieID();
            if(movieID!=null) {
                item = playlistItemRepository.findPlaylist_ItemByMovieIDAndUserID(movieID, mediaID.getUserID());
            }
            else {
                item = playlistItemRepository.findPlaylist_ItemBySerieIDAndUserID(serieID, mediaID.getUserID());
            }

            if(item!=null){
                playlistItemRepository.delete(item);
                responseResultDTO.setSuccess();
                logger.info("DELETE ITEM FROM Playlist RESPONSE{}", responseResultDTO.getErrorCode());
            }
        }catch (Exception ex){
            logger.info("=== DELETE ITEM FROM Playlist ERROR::");
            responseResultDTO.setFail(ex.getMessage());
        }
        return responseResultDTO;
    }
}
