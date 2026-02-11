package com.demo.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie_table")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "mName")
    private String name;
    @Column(name = "mHero")
    private String hero;
    @Column(name = "mDirector")
    private String director;
    @Column(name = "mBudget")
    private long budget;
    @Column(name = "mReleaseYear")
    private String releaseyear;
}
