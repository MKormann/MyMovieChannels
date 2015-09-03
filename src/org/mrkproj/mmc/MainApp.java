package org.mrkproj.mmc;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import org.mrkproj.mmc.model.channel.Channel;
import org.mrkproj.mmc.model.movie.Movie;
import org.mrkproj.mmc.view.ChannelOverviewController;
import org.mrkproj.mmc.view.LibraryOverviewController;
import org.mrkproj.mmc.view.RootLayoutController;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main application class
 * 
 * @author Matt Kormann
 *
 */
public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	private ObservableList<Movie> movies;
	private ObservableList<Channel> channels;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("My Movie Channels");
		
		initRoot();
		
		displayLibrary();
	}
	
	public void initRoot() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane)loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			
			RootLayoutController controller = new RootLayoutController();
			controller.setMainApp(this);
			
			primaryStage.show();			
		} catch (IOException e) {
			e.printStackTrace(); //TODO
		}
	}
	
	public void displayChannels() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ChannelOverview.fxml"));
			BorderPane pane = (BorderPane)loader.load();
			
			rootLayout.setCenter(pane);
			
			ChannelOverviewController controller = new ChannelOverviewController();
			controller.setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace(); //TODO
		}
	}
	
	public void displayLibrary() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/LibraryOverview.fxml"));
			BorderPane pane = (BorderPane)loader.load();
			
			rootLayout.setCenter(pane);
			
			LibraryOverviewController controller = new LibraryOverviewController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace(); //TODO
		}
	}
	
	public File getPersonFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}
	
	public void setPersonFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());
		} else {
			prefs.remove("filePath");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
