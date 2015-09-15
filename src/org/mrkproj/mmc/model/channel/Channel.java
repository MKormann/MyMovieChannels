package org.mrkproj.mmc.model.channel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.mrkproj.mmc.model.Genre;
import org.mrkproj.mmc.model.movie.MovieInstance;
import org.mrkproj.mmc.util.ActorListAdapter;
import org.mrkproj.mmc.util.GenreAdapter;

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
	
	public final static int MAX = 25;

	private final StringProperty channelName;
	private final StringProperty currentMovie;
	private final StringProperty nextMovie;
	private final ObjectProperty<boolean[]> genres;
	private final ObjectProperty<List<String>> actors;

	private MovieInstance currentInstance;
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
	
	public Channel() {
		this("");
	}
	
	/**
	 * Retrieve the currently playing movie
	 * @return the first item in the movie queue
	 */
	public MovieInstance getCurrentMovie() {
		return currentInstance;
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
	public void startNextMovie() {
		if (queue.isEmpty()) {
			currentInstance = null;
			currentMovie.set("");
			return; //TODO add error message
		} else {
			currentInstance = queue.poll();
			currentMovie.set(currentInstance.toString());
			if (!queue.isEmpty()) nextMovie.set(queue.peek().toString());
			else nextMovie.set("");
		} 
	}
	
	/**
	 * 
	 * @return size of the current playlist
	 */
	public int currentQueueSize() {
		return queue.size();
	}
	
	/**
	 * Adds a given list of movie instances to the queue
	 */
	public void addToQueue(List<MovieInstance> movies) {
		queue.addAll(movies);
		if (currentInstance == null && !queue.isEmpty()) {
			currentInstance = queue.poll();
			currentMovie.set(currentInstance.toString());
			if (!queue.isEmpty()) nextMovie.set(queue.peek().toString());
			else nextMovie.set("");
		}
	}
	
	
	/**
	 * Getters and setters below
	 */
	
	public String getChannelName() {
		return channelName.get();
	}
	
	public void setChannelName(String name) {
		channelName.set(name);
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
	
	@XmlJavaTypeAdapter(GenreAdapter.class)
	public boolean[] getGenres() {
		return genres.get();
	}
	
	public ObjectProperty<boolean[]> getGenreProperty() {
		return genres;
	}
	
	public void setGenres(boolean[] genres) {
		if (genres == null) this.genres.set(new boolean[Genre.GENRES]);
		else {
			if (genres.length == Genre.GENRES) {
				boolean[] temp = new boolean[Genre.GENRES];
				for (int i = 0; i < Genre.GENRES; i++) {
					temp[i] = genres[i];
				}
				this.genres.set(temp);
			}
		}
	}
	
	public void addGenre(int i) {
		if (i >= 0 && i < Genre.GENRES) genres.get()[i] = true;
	}
	
	public void removeGenre(int i) {
		if (i >= 0 && i < Genre.GENRES) genres.get()[i] = false;
	}
	
	@XmlJavaTypeAdapter(ActorListAdapter.class)
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
