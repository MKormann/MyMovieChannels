package org.mrkproj.mmc.util;

import org.apache.commons.lang3.time.DurationFormatUtils;

public class DurationWrapper {

	private int duration;
	
	public DurationWrapper(int duration) {
		this.duration = duration;
	}
	
	public int get() {
		return duration;
	}
	
	public void set(int duration) {
		this.duration = duration;
	}
	
	public String toString() {
		return DurationFormatUtils.formatDuration(duration * 1000, "H:mm:ss");
	}
}
