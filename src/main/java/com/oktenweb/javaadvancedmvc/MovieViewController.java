package com.oktenweb.javaadvancedmvc;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Base64;

@Controller
@RequiredArgsConstructor
public class MovieViewController {

    private final RestTemplate restTemplate;
    @Value("${movies-api.username}")
//    @Value("admin")
    private String username;
    @Value("${movies-api.password}")
//    @Value("admin")
    private String password;

    @GetMapping("/movies")
    public String moviePage(Model model) {
//    public ModelAndViewV
        model.addAttribute("cinemaName", "My Cinema");
        HttpHeaders headers = new HttpHeaders();
        final String credentials = username + ":" + password;
        final String auth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
        headers.add(HttpHeaders.AUTHORIZATION, auth);

        HttpEntity httpEntity = new HttpEntity(headers);
        final ResponseEntity<MoviePage> responseEntity = restTemplate.exchange("http://localhost:8081/movie?size=20", HttpMethod.GET,
                httpEntity, MoviePage.class);

        if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.hasBody()) {
            final MoviePage body = responseEntity.getBody();
            model.addAttribute("movies", body.getMovies());
        } else {
            model.addAttribute("movies", new ArrayList<>());
        }
        model.addAttribute("newMovie", new MovieDto());
        return "movies";
    }

    @PostMapping("/movie")
    public String createMovie(MovieDto movieDto) {

        HttpHeaders headers = new HttpHeaders();
        final String credentials = username + ":" + password;
        final String auth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
        headers.add(HttpHeaders.AUTHORIZATION, auth);

        HttpEntity httpEntity = new HttpEntity(movieDto, headers);
        restTemplate.exchange("http://localhost:8081/movie", HttpMethod.POST,
                httpEntity, MovieDto.class);

        return "redirect:/movies";
    }
}