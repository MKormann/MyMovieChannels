package org.mrkproj.mmc.model.channel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
	private final ObjectProperty<List<Genre>> genres;
	private final ObjectProperty<List<String>> actors;

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
	
	public List<Genre> getGenres() {
		return genres.get();
	}
	
	public ObjectProperty<List<Genre>> getGenreProperty() {
		return genres;
	}
	
	public void setGenres(List<Genre> g) {
		if (g == null) genres.set(new ArrayList<Genre>());
		else {
			ArrayList<Genre> temp = new ArrayList<>();
			temp.addAll(g);
			genres.set(temp);
		}
	}
	
	public List<String> getActors() {
		return actors.get();
	}
	
	public ObjectProperty<List<String>> getActorProperty() {
		return actors;
	}
	
	public void setActors(List<String> a) {
		if (a == null) actors.set(new ArrayList<String>());
		else {
			ArrayList<String> temp = new ArrayList<>();
			temp.addAll(a);
			actors.set(temp);
		}
	}
}
