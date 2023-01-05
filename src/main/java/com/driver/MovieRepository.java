package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    private HashMap<String, com.driver.Movie> movieMap;
    private HashMap<String, com.driver.Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;
    private HashMap<String, String> movieDirector;


    public MovieRepository(){
        this.movieMap = new HashMap<String, com.driver.Movie>();
        this.directorMap = new HashMap<String, com.driver.Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
        this.movieDirector = new HashMap<String, String>();
    }

    public String findDirectorName(String Movie)
    {
        if(movieDirector.containsKey(Movie))
        {
            return movieDirector.get(Movie);
        }
        return "Director not found";
    }

    public void saveMovie(com.driver.Movie movie)
    {
        movieMap.put(movie.getName(), movie);
    }

    public void saveDirector(com.driver.Director director)
    {
        directorMap.put(director.getName(), director);
    }

    public void saveMovieDirectorPair(String movie, String director)
    {



        if(movieMap.containsKey(movie)&&directorMap.containsKey(director)){

            List<String> currentMoviesByDirector = new ArrayList<>();

            if(directorMovieMapping.containsKey(director))
                currentMoviesByDirector = directorMovieMapping.get(director);

            currentMoviesByDirector.add(movie);

            directorMovieMapping.put(director,currentMoviesByDirector);

        }

    }

    public com.driver.Movie findMovie(String movie)
    {
        return movieMap.get(movie);
    }

    public com.driver.Director findDirector(String director)
    {
        return directorMap.get(director);
    }

    public List<String> findMoviesFromDirector(String director){
        List<String> moviesList = new ArrayList<String>();
        if(directorMovieMapping.containsKey(director)) moviesList = directorMovieMapping.get(director);
        return moviesList;
    }

    public List<String> findAllMovies()
    {
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director){

        List<String> movies = new ArrayList<String>();
        if(directorMovieMapping.containsKey(director)){
            //1. Find the movie names by director from the pair
            movies = directorMovieMapping.get(director);

            //2. Deleting all the movies from movieDb by using movieName
            for(String movie: movies){
                if(movieMap.containsKey(movie)){
                    movieMap.remove(movie);
                }
            }

            //3. Deleteing the pair
            directorMovieMapping.remove(director);
        }

        //4. Delete the director from directorDb.
        if(directorMap.containsKey(director)){
            directorMap.remove(director);
        }
    }

    public void deleteAllDirector(){

        HashSet<String> moviesSet = new HashSet<String>();

        //Deleting the director's map
        directorMap = new HashMap<>();

        //Finding out all the movies by all the directors combined
        for(String director: directorMovieMapping.keySet()){

            //Iterating in the list of movies by a director.
            for(String movie: directorMovieMapping.get(director)){
                moviesSet.add(movie);
            }
        }

        //Deleting the movie from the movieDb.
        for(String movie: moviesSet){
            if(movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }
        //clearing the pair.
        directorMovieMapping = new HashMap<>();
    }
}