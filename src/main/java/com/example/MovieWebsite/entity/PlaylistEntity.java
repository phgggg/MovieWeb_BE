package com.example.MovieWebsite.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "playlist", schema = "moviewebsite")
public class PlaylistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playlistID;
    private String playlistName;

    @ManyToOne
    @JoinColumn(name = "userID")
    private UserEntity user;
}
