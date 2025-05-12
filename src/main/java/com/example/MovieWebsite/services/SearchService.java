package com.example.MovieWebsite.services;

import com.example.MovieWebsite.web.dto.response.BaseResultDTO;

public interface SearchService {
    BaseResultDTO search(String keyword);
}
