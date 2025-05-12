package com.example.MovieWebsite.web.dto.user;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginRequest {
    private String userName;
    private String password;
}
