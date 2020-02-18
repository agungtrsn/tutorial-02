package com.apap.tu03.service;

import java.util.List;
import com.apap.tu03.model.MovieModel;

public interface MovieService {
	void addMovie(MovieModel movie);
	List<MovieModel> getMovieList();
	MovieModel getMovieDetail(String id);
	String updateMovie(String id, Integer value);
	String deleteMovie(String id);
}
