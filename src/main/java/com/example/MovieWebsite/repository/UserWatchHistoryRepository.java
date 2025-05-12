package com.example.MovieWebsite.repository;


import com.example.MovieWebsite.entity.UserWatchHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserWatchHistoryRepository extends JpaRepository<UserWatchHistoryEntity, Integer> {

    // Ví dụ: Tìm tất cả lịch sử xem của một user cụ thể
    List<UserWatchHistoryEntity> findByUserId(Integer userId);

    // Ví dụ: Tìm lịch sử xem theo movieId
    List<UserWatchHistoryEntity> findByMovieId(Integer movieId);

    // Ví dụ: Tìm lịch sử xem theo serieId
    List<UserWatchHistoryEntity> findBySerieId(Integer serieId);

    // Ví dụ: Tìm lịch sử xem theo episodeId
    List<UserWatchHistoryEntity> findByEpisodeId(Integer episodeId);

    // Ví dụ: Tìm tất cả bản ghi đã hoàn thành
    List<UserWatchHistoryEntity> findByIsCompleted(Boolean isCompleted);
}
