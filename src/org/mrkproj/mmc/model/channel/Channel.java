package org.mrkproj.mmc.model.channel;
import java.util.LinkedList;
import java.util.Queue;

import org.mrkproj.mmc.model.Genre;
import org.mrkproj.mmc.model.movie.MovieInstance;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

	private final StringProperty channelName;
	private final StringProperty currentMovie;
	private final StringProperty nextMovie;
	private final ObjectProperty<Genre[]> genres;
	private final ObjectProperty<String[]> actors;

	private Queue<MovieInstance> queue;
	
	/**
	 * Constructs an object of type Channel with the given name.
	 * Adds no movie genre or actor filters.
	 * @param channelName the displayed name of the Channel
	 * @throws IllegalArgumentException if no name provided
	 */
	public Channel(String channelName) {
		this.channelName = new SimpleStringProperty(channelName);
		this.currentMovie = new SimpleStringProperty();
		this.nextMovie = new SimpleStringProperty();
		this.genres = new SimpleObjectProperty<>();
		this.actors = new SimpleObjectProperty<>();
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
		currentMovie.set(queue.peek().toString());
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
	
	
	public String getChannelName() {
		return channelName.get();
	}
	
	public StringProperty getChannelNameProperty() {
		return channelName;
	}
	
	public String getCurrentMovieName() {
		return currentMovie.get();
	}

	public StringProperty getCurrentMovieProperty() {
		return currentMovie;
	}
	
	public String getNextMovieName() {
		return nextMovie.get();
	}

	public StringProperty getNextMovieProperty() {
		return nextMovie;
	}
	
	public Genre[] getGenres() {
		return genres.get();
	}
	
	public ObjectProperty<Genre[]> getGenreProperty() {
		return genres;
	}
	
	public void setGenres(Genre[] g) {
		if (g == null) genres.set(null);
		else {
			Genre[] temp = new Genre[g.length];
			for (int i = 0; i < g.length; i++) {
				temp[i] = g[i];
			}
			genres.set(temp);
		}
	}
	
	public String[] getActors() {
		return actors.get();
	}
	
	public ObjectProperty<String[]> getActorProperty() {
		return actors;
	}
	
	public void setActors(String[] a) {
		if (a == null) actors.set(null);
		else {
			String[] temp = new String[a.length];
			for (int i = 0; i < a.length; i++) {
				temp[i] = a[i];
			}
			actors.set(temp);
		}
	}
}
