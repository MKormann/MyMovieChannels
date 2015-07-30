import java.util.ArrayList;

/**
 * Class used to select and create a Movie object based on a certain set of criteria and filters
 * including genre and/or actor.
 * 
 * @author Matt Kormann
 *
 */
public class MovieSelector {
	
	public MovieSelector() {
		
	}
	
	private static Movie createMovie() {
		//TODO
		return new Movie("", "", 0, 2015, null);
	}

	/**
	 * 
	 * @param N number of movies to be generated
	 * @param genres list of genres for movies to be chosen from
	 * @param actors list of actors in generated movies
	 * @return iterable list of N movies in ANY of given genres with AT LEAST one or more of given actors
	 */
	public static Iterable<Movie> createMovieList(int N, Genre[] genres, int[] actors) {
		ArrayList<Movie> movies = new ArrayList<>();
		//TODO add database access to generate movies
		if (genres == null) ;
		if (actors == null) ;
		for (int i = 0; i < N; i++) {
			
		}
		return movies;
	}
	
	/**
	 * 
	 * @param N number of movies to be generated
	 * @return calls method to return iterable list of N movies with no genre or actor filters
	 */
	public static Iterable<Movie> createMovieList(int N) {
		return createMovieList(N, null, null);
	}
}
