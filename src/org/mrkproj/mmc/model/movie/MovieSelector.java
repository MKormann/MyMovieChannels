package org.mrkproj.mmc.model.movie;
import java.util.ArrayList;

import org.mrkproj.mmc.model.Genre;

/**
 * Class used to select and create a Movie object based on a certain set of criteria and filters
 * including genre and/or actor.
 * 
 * @author Matt Kormann
 *
 */
public class MovieSelector {
	
	public MovieSelector() {
		
	}
	
	/**
	 * Create and return a list of N MovieInstances
	 * @param N number of Instances to be created
	 * @return list of Instances
	 */
	public Iterable<MovieInstance> getMovies(int N) {
		ArrayList<MovieInstance> movies = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			
		}
		return movies;
	}
	
	/**
	 * 
	 * @param genres list of genres for movies to be chosen from
	 * @param actors list of actors in generated movies
	 * @return iterable list of movies in ANY of given genres with AT LEAST one or more of given actors
	 */
	public Iterable<Movie> createMoviePool(Genre[] genres, int[] actors) {
		ArrayList<Movie> movies = new ArrayList<>();
		//TODO add database access to generate movies
		if (genres == null) ;
		if (actors == null) ;
		return movies;
	}
	
	/**
	 * 
	 * @param genres list of genres for movies to be chosen from
	 * @return iterable list of movies in ANY of given genres
	 */
	public Iterable<Movie> createMoviePool(Genre[] genres) {
		return createMoviePool(genres, null);
	}
	
	/**
	 * 
	 * @param actors list of actors in generated movies
	 * @return iterable list of movies including AT LEAST one or more of the given actors
	 */
	public Iterable<Movie> createMoviePool(int[] actors) {
		return createMoviePool(null, actors);
	}
	
	/**
	 * 
	 * @return calls method to return iterable list of movies with no genre or actor filters
	 */
	public Iterable<Movie> createMoviePool() {
		return createMoviePool(null, null);
	}
}
