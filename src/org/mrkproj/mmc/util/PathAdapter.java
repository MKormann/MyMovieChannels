package org.mrkproj.mmc.util;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class PathAdapter extends XmlAdapter<String, Path> {

	public Path unmarshal(String s) throws Exception {
		Path path = Paths.get(s);
		System.out.println(path);
		return Paths.get(s);
	}
	
	public String marshal(Path p) throws Exception {
		return p.toString();
	}
	
}
