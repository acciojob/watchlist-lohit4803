package com.driver;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    com.driver.MovieRepository movieRepository;

    public String findDirectorName(String moviename){
        return movieRepository.findDirectorName(moviename);
    }

    public void addMovie(com.driver.Movie movie){
        movieRepository.saveMovie(movie);
    }

    public void addDirector(com.driver.Director director){
        movieRepository.saveDirector(director);
    }

    public void createMovieDirectorPair(String movie, String director){
        movieRepository.saveMovieDirectorPair(movie, director);
    }

    public com.driver.Movie findMovie(String movieName){
        return movieRepository.findMovie(movieName);
    }

    public com.driver.Director findDirector(String directorName){
        return movieRepository.findDirector(directorName);
    }

    public List<String> findMoviesFromDirector(String director){
        return movieRepository.findMoviesFromDirector(director);
    }

    public List<String> findAllMovies(){
        return movieRepository.findAllMovies();
    }

    public void deleteDirector(String director){
        movieRepository.deleteDirector(director);
    }

    public void deleteAllDirectors(){
        movieRepository.deleteAllDirector();
    }
}
