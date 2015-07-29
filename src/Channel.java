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
	
	/**
	 * Constructs an object of type Channel with the given name.
	 * Adds no movie filters.
	 * @param name the displayed name of the Channel
	 */
	public Channel(String name) {
		this.name = name;
		queue = new LinkedList<>();
	}
	
	/**
	 * Constructs an object of type Channel, with the given name, and adds a list of Genres for the Channel to play.
	 * @param name the displayed name of the Channel
	 * @param genres list of Genres that this Channel will play
	 */
	public Channel(String name, Genre[] genres) {
		this(name);
		this.genres = genres;
	}
	
	/**
	 * 
	 * @return name of Channel
	 */
	public String getName() {
		return name;
	}
}
