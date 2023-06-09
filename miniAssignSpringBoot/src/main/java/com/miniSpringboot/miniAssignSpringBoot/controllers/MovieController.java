package com.miniSpringboot.miniAssignSpringBoot.controllers;

import com.miniSpringboot.miniAssignSpringBoot.models.Movie;
import com.miniSpringboot.miniAssignSpringBoot.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")


public class MovieController {
    private static Logger logger = LoggerFactory.getLogger(MovieService.class);
    @Autowired
    private MovieService movieService;
    @GetMapping
    public List<Movie> findAll(){
        logger.info("findAll books " + this.getClass().getName());
        return movieService.findAll();
    }


    @PostMapping("/importCsv")
    public List<Movie> importCsv(){

        return movieService.readCsv();
    }
    @PostMapping
    public Movie save(@RequestBody Movie movie){
        logger.info("save book " + this.getClass().getName());
        return movieService.save(movie);
    }

    //get director movies by year
    @GetMapping("/director/{director}/yearRange/{startYear}/{endYear}")
    public List<String> getMoviesByDirectorAndYearRange(@PathVariable String director,
                                                        @PathVariable int startYear,
                                                        @PathVariable int endYear) {
        return movieService.getdirector(director, startYear, endYear);
    }

    //get English titles from user reviews
    @GetMapping("/englishTitles")
    public List<String> getEnglishTitlesWithUserReviewsGreaterThan(
            @RequestParam int userReviewFilter) {
        return movieService.getEnglishTitlesWithUserReviewsGreaterThan(userReviewFilter);
    }


    //get high budget titles for particular year and country
    @GetMapping("/highestBudget")
    public List<String> getHighestBudgetMoviesForYearAndCountry(
            @RequestParam String year, @RequestParam String country) {
        return movieService. getHighestBudgetTitlesForYearAndCountry(year,country);
    }


}