package org.mrkproj.mmc.model;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.mrkproj.mmc.MainApp;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 * Class to handle saving and loading of a user's library of videos
 * 
 * @author Matt Kormann
 *
 */

public class LibraryHandler {
	
	public LibraryHandler() {
		
	}

	public static Path getFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return Paths.get(filePath);
		} else {
			return null;
		}
	}
	
	public static void setFilePath(Path path) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		if (path != null) {
			prefs.put("filePath", path.toString());
		} else {
			prefs.remove("filePath");
		}
	}	 
	
	public static LibraryWrapper loadLibraryFromFile(Path path) {
		try {
			JAXBContext context = JAXBContext.newInstance(LibraryWrapper.class);
			Unmarshaller um = context.createUnmarshaller();
			LibraryWrapper wrapper = (LibraryWrapper)um.unmarshal(path.toFile());
			setFilePath(path);
			return wrapper;
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Save error");
			alert.setHeaderText("Unable to save data");
			alert.setContentText("Unable to save data to file:\n" + path.toString());
			
			alert.showAndWait(); 
			return null;
		}
	}
	
	public static void saveLibraryToFile(Path path, LibraryWrapper wrapper) {
		try {
			JAXBContext context = JAXBContext.newInstance(LibraryWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			m.marshal(wrapper, path.toFile());
			
			setFilePath(path);
			
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Save error");
			alert.setHeaderText("Unable to save data");
			alert.setContentText("Unable to save data to file:\n" + path.toString());
			
			alert.showAndWait(); 
		}
	}	
}
