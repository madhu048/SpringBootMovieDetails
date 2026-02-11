package com.demo.demo.Repogitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.demo.Entity.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer> {

}
