package org.mrkproj.mmc.util;

/**
 * Class to wrap genre array in for the purpose of overriding toString method
 * @author Matt Kormann
 */
public class GenreWrapper {

	private static final String[] names = {"Action", "Animated", "Classic", "Comedy", "Drama", "Horror",
			"Romance", "SciFi", "Thriller", "Western"};
	
	private boolean[] genres;
	
	public GenreWrapper(boolean[] genres) {
		this.genres = genres;
	}
	
	public boolean[] get() {
		return genres;
	}
	
	public void set(boolean[] genres) {
		this.genres = genres;
	}
	
	@Override
	public String toString() {
		String s = ""; 
		for (int i = 0; i < genres.length; i++) {
			if (genres[i]) {
				s += GenreWrapper.names[i] + ", "; 
			}
		}
		if (s.length() > 0) s = s.substring(0, s.length() - 2);
		return s;
	}
}
