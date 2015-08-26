import java.nio.file.Path;

/**
 * An object representing a single instance of a movie file in the program
 * 
 * @author Matt Kormann
 */
public class Movie {
	
	private final Path filename;
	private int length; //change to final once available TODO
	private String title;
	private int year;
	private Genre[] genres;
	
	/**
	 * Initializes a Movie object taking in the title, file location, length of movie, and a list of Genres
	 * @param title of the movie
	 * @param filename the file location of the movie
	 * @param length total runtime in seconds of the movie file
	 * @param genres list of Genres applying to this movie
	 */
	public Movie(String title, Path filename, int length, int year, Genre[] g) {
		this.title = title;
		this.filename= filename;
		this.year = year;
		genres = new Genre[g.length];
		for (int i = 0; i < g.length; i++) {
			genres[i] = g[i];
		}
	}

	/**
	 * 
	 * @return the title of the Movie
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @return an array of Genres that this Movie falls under
	 */
	public Genre[] getGenres() {
		return genres;
	}
	
	/**
	 * 
	 * @return the file location of the Movie
	 */
	public Path getFilename() {
		return filename;
	}
	
	/**
	 * 
	 * @return length of movie in seconds
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * 
	 * @param New title of movie
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @param Year of movie
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * 
	 * @param Array of genres to be applied to movie
	 */
	public void setGenres(Genre[] genres) {
		this.genres = genres;
	}
}
