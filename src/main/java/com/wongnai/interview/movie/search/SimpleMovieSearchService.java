package com.wongnai.interview.movie.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.wongnai.interview.movie.external.MovieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieSearchService;
import com.wongnai.interview.movie.external.MovieDataService;

@Component("simpleMovieSearchService")
public class SimpleMovieSearchService implements MovieSearchService {
	@Autowired
	private MovieDataService movieDataService;

	@Override
	public List<Movie> search(String queryText) {
		//TODO: Step 2 => Implement this method by using data from MovieDataService
		// All test in SimpleMovieSearchServiceIntegrationTest must pass.
		// Please do not change @Component annotation on this class

		String splitRegex = "\\s+";

		List<Movie> out = new ArrayList<Movie>();

		if (queryText.split(splitRegex).length > 1) return new ArrayList<>();

		for (MovieData movieData : movieDataService.fetchAll()) {

			String movieName = movieData.getTitle();
			String[] words = movieName.split(splitRegex);

			for (String word : words) {

				if (word.toLowerCase().equals(queryText.toLowerCase())) {
					out.add(new Movie(movieName, movieData.getCast()));
					break;
				}
			}
		}

		return out;
	}
}
