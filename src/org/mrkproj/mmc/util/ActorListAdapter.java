package org.mrkproj.mmc.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ActorListAdapter extends XmlAdapter<String, List<String>> {

	public List<String> unmarshal(String s) throws Exception {
		String[] strings = s.split(",");
		List<String> list = new ArrayList<String>();
		for (String str : strings) {
			list.add(str);
		}
		return list;
	}
	
	public String marshal(List<String> list) throws Exception {
		String string = "";
		for (int i = 0; i < list.size(); i++) {
			string += list.get(i);
			if (i != list.size() - 1) string += ",";
		}
		return string;
	}
}
