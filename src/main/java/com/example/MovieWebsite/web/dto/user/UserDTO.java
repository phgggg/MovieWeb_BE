package com.example.MovieWebsite.web.dto.user;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class UserDTO {
    private Integer userID;
    private String userName;
    private String password;
    private String fullName;
    private LocalDateTime birthday;
    private Integer gender;
    private String profileImage;
    private String email;
}
