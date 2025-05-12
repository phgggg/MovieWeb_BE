package com.example.MovieWebsite.services;

import com.example.MovieWebsite.web.dto.request.SearchUsersRequestDTO;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.user.UserDTO;

public interface UserService {
    BaseResultDTO findAllUsers(Integer pageSize, Integer page);
    BaseResultDTO addUser(UserDTO userDTO);
    BaseResultDTO updateUser(UserDTO userDTO);
    BaseResultDTO findUserEntityByUserName(String userName);
    BaseResultDTO deleteUser(Integer id);
    BaseResultDTO findUserbyId(Integer id);
    BaseResultDTO findAll();
    BaseResultDTO login(String userName, String password);
}
