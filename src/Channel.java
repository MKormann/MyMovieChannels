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

	private Queue<Movie> queue;
	private final String name;
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
		this.name = name;
		queue = new LinkedList<>();
	}
	
	/**
	 * 
	 * @return the first item in the movie queue (currently playing movie)
	 */
	public Movie getCurrentMovie() {
		return queue.peek();
	}
	
	/**
	 * 
	 * @param movie to be added to end of queue
	 */
	public void addMovie(Movie movie) {
		if (movie == null) return;
		queue.add(movie);
	}
	
	/**
	 * Adds a number of movies equal to 50 - queue.size to the end of the queue
	 */
	public void populateQueue() {
		if (queue.size() > 30) return;
		else {
			for (int i = (50 - queue.size()); i > 0; i--) {
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
