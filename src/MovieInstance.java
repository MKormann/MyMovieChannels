/**
 * Class to represent a single play of a Movie.
 * Contains a private final reference to a Movie object, as well as a start time for the movie and
 * methods to return current time and ending time.
 * 
 * @author Matt Kormann
 */
public class MovieInstance {

	private final Movie movie;
	private Long startTime;
	
	public MovieInstance(Movie movie) {
		this.movie = movie;
	}
	
	public void setStartTime() {
		if (startTime != null) return;
		else startTime = System.nanoTime() / 1000000000;
	}
}
