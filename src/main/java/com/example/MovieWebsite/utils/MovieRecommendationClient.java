package com.example.MovieWebsite.utils;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.MovieWebsite.entity.MovieEntity;
import com.example.MovieWebsite.entity.SerieEntity;
import com.example.MovieWebsite.repository.MovieRepository;
import com.example.MovieWebsite.repository.SerieRepository;
import com.example.MovieWebsite.web.dto.movie.MovieDTO;
import com.example.MovieWebsite.web.dto.response.MediaDTO;
import com.example.MovieWebsite.web.dto.serie.SerieDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieRecommendationClient {
    protected final ModelMapper modelMapper;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String API_BASE_URL = "http://localhost:5000";
    private final HttpClient httpClient;
    private final MovieRepository movieRepository;
    private final SerieRepository serieRepository;
    private final ObjectMapper objectMapper;
@Autowired
    public MovieRecommendationClient(MovieRepository movieRepository, SerieRepository serieRepository) {

        this.modelMapper = new ModelMapper();
        this.serieRepository = serieRepository;
        this.httpClient = HttpClient.newHttpClient();
        this.movieRepository = movieRepository;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Get recommended movies for a specific user
     * @param userId the ID of the user
     * @return ArrayList of Movie entities
     */
    public MediaDTO getRecommendedList(int userId) throws Exception {
        MediaDTO result = new MediaDTO();
        // Create the request to get recommended movie IDs
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_BASE_URL + "/recommendations/movie-ids/" + userId))
                .GET()
                .build();

        // Send the request and get the response
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Failed to get recommendations: " + response.body());
        }

        // Parse the response JSON using Jackson
        JsonNode jsonResponse = objectMapper.readTree(response.body());

        if (!jsonResponse.get("status").asText().equals("success")) {
            throw new Exception("API error: " +
                    (jsonResponse.has("message") ? jsonResponse.get("message").asText() : "Unknown error"));
        }

        // Extract movie IDs
        JsonNode movieIdsArray = jsonResponse.get("movieID");
        List<Integer> movieIds = new ArrayList<>();

        for (JsonNode idNode : movieIdsArray) {
            movieIds.add(idNode.asInt());
            logger.info(String.valueOf(idNode.asInt()));
        }

        ArrayList<MovieEntity> recommendedMovies = new ArrayList<>();
        for (Integer id : movieIds) {
            MovieEntity movieEntityByMovieID = movieRepository.findMovieEntityByMovieID(id);
            recommendedMovies.add(movieEntityByMovieID);
        }

        // Extract serie IDs
        JsonNode serieIdsArray = jsonResponse.get("serieID");
        List<Integer> serieIds = new ArrayList<>();

        for (JsonNode idNode : serieIdsArray) {
            serieIds.add(idNode.asInt());
            logger.info(String.valueOf(idNode.asInt()));
        }

        ArrayList<SerieEntity> recommendedSeries = new ArrayList<>();
        for (Integer id : serieIds) {
            SerieEntity serieEntityBySerieID = serieRepository.findSerieEntityBySerieID(id);
            recommendedSeries.add(serieEntityBySerieID);
        }

        ArrayList<MovieDTO> listMovieRec = new ArrayList<>();
        if(!recommendedMovies.isEmpty()){
            logger.info("=== FOUND::");
            for (MovieEntity movie : recommendedMovies) {
                MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
                listMovieRec.add(movieDTO);
            }
        }

        ArrayList<SerieDTO> listSerieRec = new ArrayList<>();
        if(!recommendedSeries.isEmpty()){
            logger.info("=== FOUND::");
            for (SerieEntity serie : recommendedSeries) {
                SerieDTO serieDTO = modelMapper.map(serie, SerieDTO.class);
                listSerieRec.add(serieDTO);
            }
        }

        result.setMovieList(listMovieRec);
        result.setSerieList(listSerieRec);
        return result;
    }

    /**
     * Asynchronous version of getRecommendedList
     */
    public CompletableFuture<MediaDTO> getRecommendedMoviesAsync(int userId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return getRecommendedList(userId);
            } catch (Exception e) {
                throw new RuntimeException("Failed to get movie recommendations", e);
            }
        });
    }

}