package com.wongnai.interview.movie.sync;

import javax.transaction.Transactional;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieInvertedIndexMemory;
import com.wongnai.interview.movie.external.MovieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.external.MovieDataService;

@Component
public class MovieDataSynchronizer {
	@Autowired
	private MovieDataService movieDataService;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private MovieInvertedIndexMemory movieInvertedIndexMemory;

	@Transactional
	public void forceSync() {
		for(MovieData data : movieDataService.fetchAll()) {
			Movie movie = new Movie(data.getTitle(), data.getCast());
			movie = movieRepository.save(movie);
			for(String word : movie.getName().toLowerCase().split("\\s+")) {
				movieInvertedIndexMemory.insert(word, movie.getId());
			}
		}
	}
}
