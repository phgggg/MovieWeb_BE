package com.example.MovieWebsite.services;

import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.user.UserWatchHistoryDTO;


public interface UserWatchHistoryService {
    BaseResultDTO addHistory(UserWatchHistoryDTO historyDTO);

}
