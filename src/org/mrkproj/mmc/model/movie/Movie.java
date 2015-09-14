package org.mrkproj.mmc.model.movie;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.mrkproj.mmc.model.Genre;
import org.mrkproj.mmc.util.ActorListAdapter;
import org.mrkproj.mmc.util.GenreAdapter;
import org.mrkproj.mmc.util.PathAdapter;

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
	private final IntegerProperty length;
	private final ObjectProperty<boolean[]> genres;
	private final ObjectProperty<List<String>> actors;
	
	/**
	 * Initializes a Movie object taking in the title, file location, length of movie, and a list of Genres
	 * @param title of the movie
	 * @param filename the file location of the movie
	 * @param length total runtime in seconds of the movie file
	 * @param g list of booleans representing the Genres applying to this movie
	 * @param a list of actor names
	 */
	public Movie(Path filename, String title, int year, int length, boolean[] g, List<String> a) {
		this.filename = new SimpleObjectProperty<>(filename);
		this.title = new SimpleStringProperty(title);
		this.year = new SimpleIntegerProperty(year);
		this.length = new SimpleIntegerProperty(length);
		boolean[] temp = new boolean[Genre.GENRES];
		for (int i = 0; i < g.length; i++) {
			temp[i] = g[i];
		}
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
		this(filename, filename.getFileName().toString(), LocalDateTime.now().getYear(), 3, 
				new boolean[Genre.GENRES], new ArrayList<>());
	}
	
	public Movie() {
		this(Paths.get(""));
	}

	public String getTitle() {
		return title.get();
	}
	
	public StringProperty getTitleProperty() {
		return title;
	}

	@XmlJavaTypeAdapter(GenreAdapter.class)
	public boolean[] getGenres() {
		return genres.get();
	}
	
	public ObjectProperty<boolean[]> getGenreProperty() {
		return genres;
	}
	
	@XmlJavaTypeAdapter(ActorListAdapter.class)
	public List<String> getActors() {
		return actors.get();
	}
	
	public ObjectProperty<List<String>> getActorProperty() {
		return actors;
	}

	@XmlJavaTypeAdapter(PathAdapter.class)
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

	public void setGenres(boolean[] genres) {
		if (genres == null) this.genres.set(new boolean[Genre.GENRES]);
		else if (genres.length == Genre.GENRES) {
			boolean[] temp = new boolean[Genre.GENRES];
			for (int i = 0; i < Genre.GENRES; i++) {
				temp[i] = genres[i];
			}
			this.genres.set(temp);
		}
	}
	
	public void addGenre(int i) {
		if (i >= 0 && i < Genre.GENRES) genres.get()[i] = true;
	}
	
	public void removeGenre(int i) {
		if (i >= 0 && i < Genre.GENRES) genres.get()[i] = false;
	}
	
	public void setActors(List<String> actors) {
		this.actors.set(actors);
	}
}
