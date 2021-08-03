package com.oktenweb.javaadvancedmvc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MovieDto {

    @JsonProperty("id")
    private int movieId;
    private String title;
    private int duration;
    private int directorId;
}