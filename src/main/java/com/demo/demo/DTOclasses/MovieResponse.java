package com.demo.demo.DTOclasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private Integer id;
    private String name;
    private String hero;
    private String director;
    private long budget;
    private String releaseyear;
}
