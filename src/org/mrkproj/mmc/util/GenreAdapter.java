package org.mrkproj.mmc.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.mrkproj.mmc.model.Genre;

public class GenreAdapter extends XmlAdapter<String, boolean[]>{

	public boolean[] unmarshal(String s) throws Exception {
		boolean[] genres = new boolean[Genre.GENRES];
		for (int i = 0; i < genres.length; i++) {
			if (s.charAt(i) == '1') genres[i] = true;
		}
		return genres;
	}
	
	public String marshal(boolean[] genres) throws Exception {
		String s = "";
		for (boolean b : genres) {
			if (b) s += 1;
			else s+= 0;
		}
		return s;
	}
}
