package com.demo.demo.service;

import java.util.List;

import com.demo.demo.DTOclasses.MovieRequest;
import com.demo.demo.DTOclasses.MovieResponse;

public interface ImovieService {

    MovieResponse createMovie(MovieRequest movie);

    MovieResponse getMovie(int id);

    MovieResponse updateMovie(int id, MovieRequest movie);

    List<MovieResponse> getAllMovies();

    String deleteMovie(int id);
}
