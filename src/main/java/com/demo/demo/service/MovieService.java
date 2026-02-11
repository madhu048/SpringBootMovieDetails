package com.demo.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.demo.DTOclasses.MovieRequest;
import com.demo.demo.DTOclasses.MovieResponse;
import com.demo.demo.Entity.Movie;
import com.demo.demo.ExceptionHandler.NotFoundEx;
import com.demo.demo.Repogitory.MovieRepo;

@Service
public class MovieService implements ImovieService {
    private final MovieRepo movieRepo;

    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public MovieResponse createMovie(MovieRequest movie) {
        Movie sMovie = new Movie();
        sMovie.setName(movie.getName());
        sMovie.setHero(movie.getHero());
        sMovie.setDirector(movie.getDirector());
        sMovie.setBudget(movie.getBudget());
        sMovie.setReleaseyear(movie.getReleaseyear());

        Movie dbMovie = movieRepo.save(sMovie);

        MovieResponse movieRes = new MovieResponse();
        movieRes.setName(dbMovie.getName());
        movieRes.setHero(dbMovie.getHero());
        movieRes.setDirector(dbMovie.getDirector());
        movieRes.setBudget(dbMovie.getBudget());
        movieRes.setReleaseyear(dbMovie.getReleaseyear());
        movieRes.setId(dbMovie.getId());

        return movieRes;
    }

    public MovieResponse getMovie(int id) {
        Movie dbMovie = movieRepo.findById(id)
                .orElseThrow(() -> new NotFoundEx("Movie not found with id: " + id));

        MovieResponse movieRes = new MovieResponse();
        movieRes.setName(dbMovie.getName());
        movieRes.setHero(dbMovie.getHero());
        movieRes.setDirector(dbMovie.getDirector());
        movieRes.setBudget(dbMovie.getBudget());
        movieRes.setReleaseyear(dbMovie.getReleaseyear());
        movieRes.setId(dbMovie.getId());

        return movieRes;
    }

    public MovieResponse updateMovie(int id, MovieRequest movie) {
        Movie dbMovie = movieRepo.findById(id)
                .orElseThrow(() -> new NotFoundEx("Movie not found with id: " + id));

        dbMovie.setName(movie.getName());
        dbMovie.setHero(movie.getHero());
        dbMovie.setDirector(movie.getDirector());
        dbMovie.setBudget(movie.getBudget());
        dbMovie.setReleaseyear(movie.getReleaseyear());

        Movie savedMovie = movieRepo.save(dbMovie);

        MovieResponse movieRes = new MovieResponse();

        movieRes.setName(savedMovie.getName());
        movieRes.setHero(savedMovie.getHero());
        movieRes.setDirector(savedMovie.getDirector());
        movieRes.setBudget(savedMovie.getBudget());
        movieRes.setReleaseyear(savedMovie.getReleaseyear());
        movieRes.setId(savedMovie.getId());

        return movieRes;
    }

    public List<MovieResponse> getAllMovies() {
        List<Movie> dbMovieList = movieRepo.findAll();

        List<MovieResponse> movieList = new ArrayList<>();

        for (Movie movie : dbMovieList) {
            MovieResponse movieRes = new MovieResponse();
            movieRes.setName(movie.getName());
            movieRes.setHero(movie.getHero());
            movieRes.setDirector(movie.getDirector());
            movieRes.setBudget(movie.getBudget());
            movieRes.setReleaseyear(movie.getReleaseyear());
            movieRes.setId(movie.getId());

            movieList.add(movieRes);
        }
        return movieList;
    }

    public String deleteMovie(int id) {
        Movie dbMovie = movieRepo.findById(id)
                .orElseThrow(() -> new NotFoundEx("Movie not found with id: " + id));
        movieRepo.delete(dbMovie);
        return "Movie Deleted Successfully";
    }

}
