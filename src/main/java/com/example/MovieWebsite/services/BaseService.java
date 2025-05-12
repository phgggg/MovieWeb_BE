package com.example.MovieWebsite.services;

import com.example.MovieWebsite.repository.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected ModelMapper modelMapper;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected ActorRepository actorRepository;
    @Autowired
    protected GenreRepository genreRepository;
    @Autowired
    protected MovieRepository movieRepository;
    @Autowired
    protected SerieRepository serieRepository;
    @Autowired
    protected EpisodeRepository episodeRepository;
    @Autowired
    protected SeasonRepository seasonRepository;
    @Autowired
    protected PlaylistRepository playlistRepository;
    @Autowired
    protected Playlist_ItemRepository playlistItemRepository;
    @Autowired
    protected ReviewRepository reviewRepository;
    @Autowired
    protected UserWatchHistoryRepository userWatchHistoryRepository;
}
