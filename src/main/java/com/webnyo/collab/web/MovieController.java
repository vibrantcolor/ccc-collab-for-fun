package com.webnyo.collab.web;

import com.webnyo.collab.Movie;
import com.webnyo.collab.data.MovieRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MovieController {

    MovieRepository movieRepo;

    public MovieController(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    @GetMapping
    public String movieCrud(Model model) {
        List<Movie> movies = movieRepo.findAll(Sort.by("id").ascending());
        model.addAttribute("movies", movies);
        return "movieCrud";
    }

    @GetMapping("/add-movie")
    public String inputMovie() {
        return "create";
    }

    // we need to add a movie to the model for data binding (even though we don't want to display it)
    @ModelAttribute
    public Movie movie() {
        return new Movie();
    }

    @PostMapping
    public String addMovie(@ModelAttribute Movie movie) {
        movieRepo.save(movie);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String editMovie(Model model, @PathVariable Long id) {
        Movie movie;
        if (movieRepo.findById(id).isPresent()) {
            movie = movieRepo.findById(id).get();
            model.addAttribute("movie", movie);
        }
        return "update";
    }

    @PutMapping("/{id}")
    public String updateMovie(@PathVariable Long id, @ModelAttribute Movie movie) {
        Optional<Movie> existingMovie = movieRepo.findById(id);
        if (existingMovie.isPresent()) {
            Movie updatedMovie = existingMovie.get();
            updatedMovie.setName(movie.getName());
            updatedMovie.setYear(movie.getYear());
            movieRepo.save(updatedMovie);
        }
        return "redirect:/";
    }

    @DeleteMapping
    public String deleteMovie(@ModelAttribute Movie movie) {
        movieRepo.deleteById(movie.getId());
        return "redirect:/";
    }
}
