package com.example.MovieWebsite.controller;

import com.example.MovieWebsite.services.UserService;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.user.LoginRequest;
import com.example.MovieWebsite.web.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {
    @Autowired
    UserService usersService;

    @RequestMapping(value = "/findAllUser", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> allUsers() {
        BaseResultDTO result = usersService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> login(@RequestBody LoginRequest requestDTO) {
        BaseResultDTO result = usersService.login(requestDTO.getUserName(), requestDTO.getPassword());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> addUser(@RequestBody UserDTO requestDTO) {
        BaseResultDTO result = usersService.addUser(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public ResponseEntity<BaseResultDTO> updateUser(@RequestBody UserDTO requestDTO) {
        BaseResultDTO result = usersService.updateUser(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByUserName", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findUserEntityByUserName(@RequestParam("userName") String requestDTO) {
        BaseResultDTO result = usersService.findUserEntityByUserName(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity<BaseResultDTO> deleteUser(@RequestParam("id") Integer requestDTO) {
        BaseResultDTO result = usersService.deleteUser(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByUserID", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findUserbyId(@RequestParam("userID") Integer requestDTO) {
        BaseResultDTO result = usersService.findUserbyId(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
