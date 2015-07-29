import java.util.LinkedList;
import java.util.Queue;

/**
 * An object representing a "channel" of movies.
 * Holds a queue of Movie objects in an order representative of a playlist, and can
 * be assigned X number of genres or other sorting categories to determine what
 * movie files are used to populate the playlist.
 * 
 * @author Matt Kormann
 */
public class Channel {
	
	private final int MAX = 25;

	private Queue<Movie> queue;
	private final String NAME;
	private Genre[] genres;
	private int[] actorIDs;
	
	
	/**
	 * Constructs an object of type Channel with the given name.
	 * Adds no movie genre or actor filters.
	 * @param name the displayed name of the Channel
	 * @throws IllegalArgumentException if no name provided
	 */
	public Channel(String name) {
		if (name == null) throw new IllegalArgumentException();
		this.NAME = name;
		queue = new LinkedList<>();
	}
	
	/**
	 * Retrieve the currently playing movie
	 * @return the first item in the movie queue
	 */
	public Movie getCurrentMovie() {
		return queue.peek();
	}
	
	/**
	 * Add a movie
	 * @param movie to be added to end of queue
	 */
	public void addMovie(Movie movie) {
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
		return NAME;
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
	public void setGenres(Genre[] genres) {
		this.genres = genres;
	}
	
	/**
	 * 
	 * @return actorIDs list of actor ids for this channel
	 */
	public int[] getActors() {
		return actorIDs;
	}
	
	/**
	 * 
	 * @param actorIDs list of ints that correspond to actors in database
	 */
	public void setActors(int[] actorIDs) {
		this.actorIDs = actorIDs;
	}
}
