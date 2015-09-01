package org.mrkproj.mmc.model.movie;
/**
 * Class to represent a single play of a Movie.
 * Contains a private final reference to a Movie object, as well as a start time for the movie and
 * methods to return current time and ending time.
 * 
 * @author Matt Kormann
 */
public class MovieInstance {

	private final Movie movie;
	private int startTime = 0;
	private boolean started;
	private boolean finished;
	
	public MovieInstance(Movie movie) {
		this.movie = movie;
	}
	
	/**
	 * Sets movie start time with current System.nanoTime()
	 */
	public void startMovie() {
		if (!started) {
			startTime = (int)System.nanoTime() / 1000000000;
			started = true;
		}
	}
	
	/**
	 * Returns current time of movie in seconds, returning 0 if not started, and -1 if finished
	 * @return int representing the current play time in seconds
	 */
	public int getCurrentTime() {
		if (!started) return 0;
		if (!finished) return (int)(System.nanoTime() / 1000000000 - startTime);
		else return -1;
	}
	
	/**
	 * Checks if movie is done playing
	 * @return true if done, false otherwise
	 */
	public boolean isOver() {
		if (finished) return true;
		if (System.nanoTime() / 1000000000 - startTime > movie.getLength()) {
			finished = true;
			return true;
		}
		return false;
	}
}
