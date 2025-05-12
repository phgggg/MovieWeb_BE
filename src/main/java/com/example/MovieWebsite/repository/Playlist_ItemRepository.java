package com.example.MovieWebsite.repository;

import com.example.MovieWebsite.entity.Playlist_Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Playlist_ItemRepository extends JpaRepository<Playlist_Item, Integer> {
    @Query(value = "SELECT i.* FROM moviewebsite.playlist_item i join playlist p on p.playlistid = i.playlistid where i.movieid = :id and p.userid = :userid limit 1;",nativeQuery = true)
    Playlist_Item findPlaylist_ItemByMovieIDAndUserID(@Param("id") Integer movieID, @Param("userid") Integer userid);
    @Query(value = "SELECT i.* FROM moviewebsite.playlist_item i join playlist p on p.playlistid = i.playlistid where i.serieid = :id and p.userid = :userid limit 1;",nativeQuery = true)
    Playlist_Item findPlaylist_ItemBySerieIDAndUserID(@Param("id") Integer serieID, @Param("userid") Integer userid);
    @Query(value = "SELECT * FROM moviewebsite.playlist_item where playlistid = :playlistID;", nativeQuery = true)
    List<Playlist_Item> findPlaylist_ItemByPlaylist(@Param("playlistID") Integer playlistID);

}
