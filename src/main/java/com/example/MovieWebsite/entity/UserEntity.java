package com.example.MovieWebsite.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", schema = "moviewebsite")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;
    @Getter
    private String userName;
    @Getter
    private String password;
    private String fullName;
    private LocalDateTime birthday;
    private Integer gender;
    private String profileImage;
    private String email;

}
