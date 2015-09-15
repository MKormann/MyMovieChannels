package org.mrkproj.mmc.util;

import java.util.List;

/**
 * Class to wrap actor array in for the purpose of overriding toString method
 * @author Matt Kormann
 */
public class ActorWrapper {

	private List<String> actors;
	
	public ActorWrapper(List<String> actors) {
		this.actors = actors;
	}
	
	public List<String> get() {
		return actors;
	}
	
	public void set(List<String> actors) {
		this.actors = actors;
	}
	
	@Override
	public String toString() {
		String s = actors.toString();
		return s.substring(1, s.length() - 1);
	}
}
