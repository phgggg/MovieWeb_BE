package com.example.MovieWebsite.controller;

import com.example.MovieWebsite.services.UserWatchHistoryService;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.user.UserWatchHistoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/history")
public class UserWatchHistoryController extends BaseController {
    @Autowired
    UserWatchHistoryService userWatchHistoryService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> addHistory(@RequestBody UserWatchHistoryDTO requestDTO) {
        BaseResultDTO result = userWatchHistoryService.addHistory(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
