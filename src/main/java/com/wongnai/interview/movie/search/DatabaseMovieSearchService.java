package com.wongnai.interview.movie.search;

import java.util.ArrayList;
import java.util.List;

import com.wongnai.interview.movie.external.MovieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.MovieSearchService;

@Component("databaseMovieSearchService")
public class DatabaseMovieSearchService implements MovieSearchService {
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public List<Movie> search(String queryText) {
		//TODO: Step 3 => Please make all test in DatabaseMovieSearchServiceIntegrationTest run pass.
		// This database search method must use only MovieRepository.findByNameContains(String), you also have to implement
		// MovieDataSynchronizer.forceSync() to load all movie data, using MovieDataService, into MovieRepository.
		// Do not change @Component annotation on this class

		String splitRegex = "\\s+";

		if(queryText.split(splitRegex).length > 1) return new ArrayList<>();

		ArrayList<Movie> out = new ArrayList<>();

		for(Movie movie : movieRepository.findByNameContains(queryText)){

			String[] words = movie.getName().split(splitRegex);

			for(String word : words) {
				if(word.toLowerCase().equals(queryText.toLowerCase())) {
					out.add(movie);
				}
			}
		}

		return out;
	}
}
