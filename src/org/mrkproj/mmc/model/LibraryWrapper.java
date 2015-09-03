package org.mrkproj.mmc.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.mrkproj.mmc.model.channel.Channel;
import org.mrkproj.mmc.model.movie.Movie;

@XmlRootElement (name = "library")
public class LibraryWrapper {

	private List<Movie> movies;
	private List<Channel> channels;
	
	@XmlElement(name = "movie")
	public List<Movie> getMovies() {
		return movies;
	}
	
	@XmlElement(name = "channel")
	public List<Channel> getChannels() {
		return channels;
	}
	
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}
}
