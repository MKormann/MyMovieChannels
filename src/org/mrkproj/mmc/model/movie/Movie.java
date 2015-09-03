package org.mrkproj.mmc.model.movie;
import java.nio.file.Path;
import java.time.LocalDateTime;

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
	private final ObjectProperty<Genre[]> genres;
	
	/**
	 * Initializes a Movie object taking in the title, file location, length of movie, and a list of Genres
	 * @param title of the movie
	 * @param filename the file location of the movie
	 * @param length total runtime in seconds of the movie file
	 * @param genres list of Genres applying to this movie
	 */
	public Movie(Path filename, String title, int length, int year, Genre[] g) {
		this.filename = new SimpleObjectProperty<>(filename);
		this.title = new SimpleStringProperty(title);
		this.length = new SimpleIntegerProperty(length);
		this.year = new SimpleIntegerProperty(year);
		Genre[] temp = new Genre[g.length];
		for (int i = 0; i < g.length; i++) {
			temp[i] = g[i];
		}
		this.genres = new SimpleObjectProperty<>(temp);
	}
	
	/**
	 * Initializes a Movie object without any extra data, setting title to the default file name, length to 0, and genres empty
	 * @param file location of the movie
	 */
	public Movie(Path filename) {
		this(filename, filename.getFileName().toString(), 0, LocalDateTime.now().getYear(), new Genre[0]);
	}

	public String getTitle() {
		return title.get();
	}
	
	public StringProperty getTitleProperty() {
		return title;
	}

	public Genre[] getGenres() {
		return genres.get();
	}
	
	public ObjectProperty<Genre[]> getGenreProperty() {
		return genres;
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

	public void setGenres(Genre[] genres) {
		this.genres.set(genres);
	}
}
