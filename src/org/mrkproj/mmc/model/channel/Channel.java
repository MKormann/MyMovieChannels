package org.mrkproj.mmc.model.channel;
import java.util.LinkedList;
import java.util.Queue;

import org.mrkproj.mmc.model.Genre;
import org.mrkproj.mmc.model.movie.MovieInstance;

/**
 * An object representing a "channel" of movies.
 * Holds a queue of Movie objects in an order representative of a playlist, and can
 * be assigned X number of genres or other sorting categories to determine what
 * movie files are used to populate the playlist.
 * 
 * @author Matt Kormann
 */
public class Channel {
	
	private final static int MAX = 25;

	private Queue<MovieInstance> queue;
	private final String name;
	private Genre[] genres;
	private String[] actors;
	
	
	/**
	 * Constructs an object of type Channel with the given name.
	 * Adds no movie genre or actor filters.
	 * @param name the displayed name of the Channel
	 * @throws IllegalArgumentException if no name provided
	 */
	public Channel(String name) {
		if (name == null) throw new IllegalArgumentException();
		this.name = name;
		queue = new LinkedList<>();
	}
	
	/**
	 * Retrieve the currently playing movie
	 * @return the first item in the movie queue
	 */
	public MovieInstance getCurrentMovie() {
		return queue.peek();
	}
	
	/**
	 * Add a movie
	 * @param movie to be added to end of queue
	 */
	public void addMovie(MovieInstance movie) {
		if (movie == null) return;
		queue.add(movie);
	}
	
	/**
	 * Remove current movie, start next movie's counter
	 */
	private void startNextMovie() {
		queue.remove();
		queue.peek().startMovie();
	}
	
	/**
	 * Adds a number of movies equal to max length - queue.size to the end of the queue
	 */
	public void populateQueue() {
		if (queue.size() > 10) return;
		else {
			for (int i = (MAX - queue.size()); i > 0; i--) {
				//TODO add function call to random movie generator
			}
		}
	}
	
	//Getters and setters below
	
	/**
	 * 
	 * @return name of Channel
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return list of Genres for this channel
	 */
	public Genre[] getGenres() {
		return genres;
	}
	
	/**
	 * 
	 * @param genres list of enum type Genres
	 */
	public void setGenres(Genre[] g) {
		if (g == null) return;
		genres = new Genre[g.length];
		for (int i = 0; i < g.length; i++) {
			genres[i] = g[i];
		}
	}
	
	/**
	 * 
	 * @return String list of actors for this channel
	 */
	public String[] getActors() {
		return actors;
	}
	
	/**
	 * 
	 * @param actors list that corresponds to actors in database
	 */
	public void setActors(String[] ids) {
		if (ids == null) return;
		actors = new String[ids.length];
		for (int i = 0; i < ids.length; i++) {
			actors[i] = ids[i];
		}
	}
}
