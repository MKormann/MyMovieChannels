package org.mrkproj.mmc.model.movie;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.mrkproj.mmc.model.Genre;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * An object representing a single instance of a movie file in the program
 * 
 * @author Matt Kormann
 */
public class Movie {
	
	private final ObjectProperty<Path> filename;
	private final StringProperty title;
	private final IntegerProperty year;
	private final IntegerProperty length; //change to final once available TODO
	private final ObjectProperty<List<Genre>> genres;
	private final ObjectProperty<List<String>> actors;
	
	/**
	 * Initializes a Movie object taking in the title, file location, length of movie, and a list of Genres
	 * @param title of the movie
	 * @param filename the file location of the movie
	 * @param length total runtime in seconds of the movie file
	 * @param genres list of Genres applying to this movie
	 */
	public Movie(Path filename, String title, int year, int length, List<Genre> g, List<String> a) {
		this.filename = new SimpleObjectProperty<>(filename);
		this.title = new SimpleStringProperty(title);
		this.year = new SimpleIntegerProperty(year);
		this.length = new SimpleIntegerProperty(length);
		ArrayList<Genre> temp = new ArrayList<>();
		temp.addAll(g);
		ArrayList<String> temp2 = new ArrayList<>();
		temp2.addAll(a);
		this.genres = new SimpleObjectProperty<>(temp);
		this.actors = new SimpleObjectProperty<>(temp2);
	}
	
	/**
	 * Initializes a Movie object without any extra data, setting title to the default file name, length to 0, and genres empty
	 * @param file location of the movie
	 */
	public Movie(Path filename) {
		this(filename, filename.getFileName().toString(), LocalDateTime.now().getYear(), 0, 
				new ArrayList<>(), new ArrayList<>());
	}

	public String getTitle() {
		return title.get();
	}
	
	public StringProperty getTitleProperty() {
		return title;
	}

	public List<Genre> getGenres() {
		return genres.get();
	}
	
	public ObjectProperty<List<Genre>> getGenreProperty() {
		return genres;
	}
	
	public List<String> getActors() {
		return actors.get();
	}
	
	public ObjectProperty<List<String>> getActorProperty() {
		return actors;
	}

	public Path getFilename() {
		return filename.get();
	}
	
	public ObjectProperty<Path> getPathProperty() {
		return filename;
	}
	
	public int getLength() {
		return length.get();
	}
	
	public IntegerProperty getLengthProperty() {
		return length;
	}
	
	public int getYear() {
		return year.get();
	}
	
	public IntegerProperty getYearProperty() {
		return year;
	}
	
	public void setTitle(String title) {
		this.title.set(title);
	}

	public void setYear(int year) {
		this.year.set(year);
	}

	public void setGenres(List<Genre> genres) {
		this.genres.set(genres);
	}
	
	public void setActors(List<String> actors) {
		this.actors.set(actors);
	}
}
