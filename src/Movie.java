/**
 * An object representing a single instance of a movie file in the program
 * 
 * @author Matt Kormann
 */
public class Movie {
	
	private final String title;
	private final String filename;
	private long startTime;
	private int length;
	private final Genre[] genres;
	
	/**
	 * Initializes a Movie object taking in the title, file location, length of movie, and a list of Genres
	 * @param title of the movie
	 * @param filename the file location of the movie
	 * @param length total runtime in seconds of the movie file
	 * @param genres list of Genres applying to this movie
	 */
	public Movie(String title, String filename, int length, Genre[] g) {
		this.title = title;
		this.filename= filename;
		genres = new Genre[g.length];
		for (int i = 0; i < g.length; i++) {
			genres[i] = g[i];
		}
	}
	
	/**
	 * Sets movie start time with current System.nanoTime()
	 */
	public void startMovie() {
		startTime = System.nanoTime();
	}

	/**
	 * 
	 * @return the title of the Movie
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the runtime in seconds that movie is currently playing at
	 */
	public int getTimeAt() {
		return (int)((System.nanoTime() - startTime) / 1000000000);
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
	public String getFilename() {
		return filename;
	}
}
