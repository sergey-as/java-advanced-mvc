package com.oktenweb.javaadvancedmvc;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@AllArgsConstructor
@NoArgsConstructor
@Data
//@Getter
//@Setter
public class MoviePage {

    private List<MovieDto> movies;
    private long totalElements;

}