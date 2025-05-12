package com.example.MovieWebsite.services.impl;

import com.example.MovieWebsite.entity.MovieEntity;
import com.example.MovieWebsite.entity.UserWatchHistoryEntity;
import com.example.MovieWebsite.services.BaseService;
import com.example.MovieWebsite.services.UserWatchHistoryService;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.user.UserWatchHistoryDTO;
import org.springframework.stereotype.Service;

@Service
public class UserWatchHistoryServiceImpl extends BaseService implements UserWatchHistoryService {

    @Override
    public BaseResultDTO addHistory(UserWatchHistoryDTO historyDTO) {
        logger.info("=== START ADD NEW HISTORY::");
        BaseResultDTO responseResultDTO = new BaseResultDTO();
        try {

            UserWatchHistoryEntity history = modelMapper.map(historyDTO, UserWatchHistoryEntity.class);
            history = userWatchHistoryRepository.save(history);
            if (history != null) {
                logger.info("new HISTORY id = " + history.getHistoryId());
                responseResultDTO.setSuccess();
            }
            logger.info("=== ADD NEW HISTORY RESPONSE::" + responseResultDTO.getErrorCode());
        } catch (Exception ex) {
            responseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return responseResultDTO;
    }
}
