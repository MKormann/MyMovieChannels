package org.mrkproj.mmc.model.channel;
import java.util.ArrayList;

/**
 * Handler for all the user's channels.
 * 
 * @author Matt Kormann
 */

public class ChannelManager {

	private ArrayList<Channel> channels;
	
	public ChannelManager() {
		
	}
	
	public void addChannel() {
		
	}
	
	/**
	 * Remove channel from the list of channels.
	 * @param channel to be deleted
	 */
	public void deleteChannel(Channel channel) {
		if (channel == null) return;
		if (!channels.contains(channel)) return;
		channels.remove(channel);
	}
}
