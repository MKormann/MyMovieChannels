package org.mrkproj.mmc.model.movie;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mrkproj.mmc.model.Genre;

/**
 * Class used to select and create a Movie object based on a certain set of criteria and filters
 * including genre and/or actor.
 * 
 * @author Matt Kormann
 *
 */
public class MovieSelector {
	
	private Set<Movie> moviePool;
	private List<Movie> movieLibrary;
	
	public MovieSelector() {
		moviePool = new HashSet<>();
	}
	
	/**
	 * Set a reference to the main library of movies
	 * @param movies total movie list
	 */
	public void setLibrary(List<Movie> movies) {
		movieLibrary = movies;
	}
	
	/**
	 * Create and return a list of N MovieInstances
	 * @param N number of Instances to be created
	 * @return list of Instances
	 */
	public List<MovieInstance> getMovies(int N) {
		ArrayList<MovieInstance> movies = new ArrayList<>();
		if (moviePool.size() == 0) return movies;
		if (moviePool.size() == 1) {
			for (int i = 0; i < N; i++) {
				movies.add(new MovieInstance((Movie)moviePool.toArray()[0]));
			}
			return movies;
		}
		Object[] moviePoolArray = moviePool.toArray();
		int prev = -1;
		for (int i = 0; i < N; i++) {
			int curr = prev;
			while (curr == prev) {
				curr = (int)(Math.random() * moviePoolArray.length);
			}
			MovieInstance instance = new MovieInstance((Movie)moviePoolArray[curr]);
			movies.add(instance);
			prev = curr;
		}
		return movies;
	}
	
	/**
	 * 
	 * @param genres list of genres for movies to be chosen from
	 * @param actors list of actors in generated movies
	 * @return set of movies in ANY of given genres with AT LEAST one or more of given actors
	 */
	public void createMoviePool(boolean[] genres, List<String> actors) {
		if (genres.length != Genre.GENRES) genres = null;
		if (genres == null && actors == null) {
			moviePool.addAll(movieLibrary);
		}
		else {
			for (Movie m : movieLibrary) {
				for (int i = 0; i < genres.length; i++) {
					if (m.getGenres()[i] && genres[i]) {
						moviePool.add(m);
						break;
					}
				}
				for (String s : m.getActors()) {
					if (actors.contains(s)) {
						moviePool.add(m);
						break;
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param genres list of genres for movies to be chosen from
	 * @return set of movies in ANY of given genres
	 */
	public void createMoviePool(boolean[] genres) {
		createMoviePool(genres, null);
	}
	
	/**
	 * 
	 * @param actors list of actors in generated movies
	 * @return set of movies including AT LEAST one or more of the given actors
	 */
	public void createMoviePool(List<String> actors) {
		createMoviePool(null, actors);
	}
	
	/**
	 * 
	 * @return calls method to return set of movies with no genre or actor filters
	 */
	public void createMoviePool() {
		createMoviePool(null, null);
	}
}
