package org.mrkproj.mmc.model;
import java.io.File;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.mrkproj.mmc.MainApp;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Path;


/**
 * Class to handle saving and loading of a user's library of videos
 * 
 * @author Matt Kormann
 *
 */

public class LibraryHandler {
	
	public LibraryHandler() {
		
	}

	public static File getFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}
	
	public static void setFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());
		} else {
			prefs.remove("filePath");
		}
	}	 
	
	public static LibraryWrapper loadLibraryFromFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(LibraryWrapper.class);
			Unmarshaller um = context.createUnmarshaller();
			LibraryWrapper wrapper = (LibraryWrapper)um.unmarshal(file);
			setFilePath(file);
			return wrapper;
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Save error");
			alert.setHeaderText("Unable to save data");
			alert.setContentText("Unable to save data to file:\n" + file.getPath());
			
			alert.showAndWait(); 
			return null;
		}
	}
	
	public static void saveLibraryToFile(File file, LibraryWrapper wrapper) {
		try {
			JAXBContext context = JAXBContext.newInstance(LibraryWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			m.marshal(wrapper, file);
			
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Save error");
			alert.setHeaderText("Unable to save data");
			alert.setContentText("Unable to save data to file:\n" + file.getPath());
			
			alert.showAndWait(); 
		}
	}	
}
