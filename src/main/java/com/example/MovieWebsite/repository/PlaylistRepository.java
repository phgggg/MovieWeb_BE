package com.example.MovieWebsite.repository;

import com.example.MovieWebsite.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Integer> {
    PlaylistEntity getPlaylistEntitiesByPlaylistID (Integer id);
    List<PlaylistEntity> findAll();
    PlaylistEntity findPlaylistEntityByPlaylistName(String playlistName);
    PlaylistEntity findPlaylistEntityByPlaylistID(Integer id);
    @Query(value = "SELECT * FROM moviewebsite.playlist where userid = :userid;",nativeQuery = true)
    PlaylistEntity findPlaylistEntityByUserID(@Param("userid") Integer id);
}
