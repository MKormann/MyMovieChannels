/**
 * Class used to create channels given a certain number of criteria.
 * 
 * @author Matt Kormann
 */

public class ChannelFactory {
	
	private ChannelFactory() {
		
	}
	
	/**
	 * 
	 * @param name Channel name
	 * @return call to factory method with name and null arrays for genre and actor
	 */
	public static Channel createChannel(String name) {
		return ChannelFactory.createChannel(name, null, null);
	}
	
	/**
	 * 
	 * @param name Channel name
	 * @param genres array of Genre enums
	 * @return call to factory method with name, genres, and null array for actors
	 */
	public static Channel createChannel(String name, Genre[] genres) {
		return ChannelFactory.createChannel(name, genres, null);	
	}
	
	/**
	 * 
	 * @param name Channel name
	 * @param actorIDs array of actor IDs
	 * @return call to factory method with name, actors, and null array for genres
	 */
	public static Channel createChannel(String name, int[] actorIDs) {
		return ChannelFactory.createChannel(name, null, actorIDs);		
	}
	
	/**
	 * 
	 * @param name Channel name
	 * @param genres array of Genre enums
	 * @param actorIDs array of actor IDs
	 * @return constructs a new Channel with three given parameters
	 */
	public static Channel createChannel(String name, Genre[] genres, int[] actorIDs) {
		Channel channel = new Channel(name);
		channel.setGenres(genres);
		channel.setActors(actorIDs);
		return channel;
	}
}