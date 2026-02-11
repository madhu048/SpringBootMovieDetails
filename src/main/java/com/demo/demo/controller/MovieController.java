package com.demo.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demo.DTOclasses.MovieRequest;
import com.demo.demo.DTOclasses.MovieResponse;
import com.demo.demo.service.MovieService;

@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/createMovie")
    public ResponseEntity<MovieResponse> saveMovie(@RequestBody MovieRequest movie) {
        MovieResponse savedMovie = movieService.createMovie(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/getMovie/{id}")
    public ResponseEntity<MovieResponse> getMovieDetails(@PathVariable int id) {
        MovieResponse dbMovie = movieService.getMovie(id);
        return new ResponseEntity<>(dbMovie, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/getAllMovies")
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateMovie/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable int id, @RequestBody MovieRequest movie) {
        MovieResponse updatedMovie = movieService.updateMovie(id, movie);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/removeMovie/{id}")
    public ResponseEntity<String> removeMovie(@PathVariable int id) {
        return new ResponseEntity<>(movieService.deleteMovie(id), HttpStatus.OK);
    }
}
