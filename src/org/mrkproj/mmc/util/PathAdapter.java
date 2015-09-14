package org.mrkproj.mmc.util;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class PathAdapter extends XmlAdapter<String, Path> {

	@Override
	public Path unmarshal(String s) throws Exception {
		return Paths.get(s);
	}
	
	@Override
	public String marshal(Path p) throws Exception {
		return p.toString();
	}
	
}
