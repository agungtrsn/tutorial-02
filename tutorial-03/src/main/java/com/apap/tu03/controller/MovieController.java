package com.apap.tu03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tu03.model.MovieModel;
import com.apap.tu03.service.MovieService;

@Controller
public class MovieController {
	@Autowired
	private MovieService movieService;
	
	@RequestMapping("/movie/add")
	public String add(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "genre", required = true) String genre,
			@RequestParam(value = "budget", required = true) Long budget,
			@RequestParam(value = "duration", required = true) Integer duration) {
		MovieModel movie = new MovieModel(id, title, genre, budget, duration);
		movieService.addMovie(movie);
		return "add";
	}
	
	@RequestMapping("/movie/view")
	public String view(@RequestParam("id") String id, Model model) {
		MovieModel archive = movieService.getMovieDetail(id);
		model.addAttribute("movie", archive);
		return "view-movie";
	}
	
	@RequestMapping("/movie/viewall")
	public String viewAll(Model model) {
		List<MovieModel> archive = movieService.getMovieList();
		model.addAttribute("movies", archive);
		return "viewall-movie";
	}
	
	@RequestMapping("/movie/view/{id}")
	public String viewMovie(@PathVariable String id, Model model) {
		
		if(!id.isEmpty()) {
			MovieModel archive = movieService.getMovieDetail(id);
			
			if(archive!=null) {
				model.addAttribute("movie", archive);
				return "view-movie";
			}
		}
		return "not-found";
	}
	
	@RequestMapping("/movie/update/{id}/duration/{value}")
    public String updateMovie(@PathVariable String id, @PathVariable Integer value, Model model) {
		
		if(!id.isEmpty()) {
			String result = movieService.updateMovie(id, value);
        
			if(result!=null) {
				model.addAttribute("result", result);
				return "update";
			}
		}
		return "not-found";
	}

    @RequestMapping("/movie/delete/{id}")
    public String updateMovie(@PathVariable String id, Model model) {
    	
    	if(!id.isEmpty()) {
    		String result = movieService.deleteMovie(id);
    		
    		if(result!=null) {
    			model.addAttribute("result", result);
    			return "delete";
    		}
    	}
    	return "not-found";
    }
}
