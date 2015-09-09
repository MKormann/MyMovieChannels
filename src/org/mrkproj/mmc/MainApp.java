package org.mrkproj.mmc;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.mrkproj.mmc.model.LibraryHandler;
import org.mrkproj.mmc.model.LibraryWrapper;
import org.mrkproj.mmc.model.channel.Channel;
import org.mrkproj.mmc.model.movie.Movie;
import org.mrkproj.mmc.view.ChannelOverviewController;
import org.mrkproj.mmc.view.LibraryOverviewController;
import org.mrkproj.mmc.view.RootLayoutController;

import javafx.application.Application;
import javafx.collections.FXCollections;
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
	
	private ObservableList<Movie> movies = FXCollections.observableArrayList();
	private ObservableList<Channel> channels = FXCollections.observableArrayList();
	
	private Set<Path> moviePaths = new HashSet<>();

	public MainApp() {
		movies.add(new Movie(Paths.get(""), "The Matrix", 1999, 7921, new ArrayList<>(), new ArrayList<>()));
		movies.add(new Movie(Paths.get(""), "Braveheart", 1995, 9033, new ArrayList<>(), new ArrayList<>()));
		movies.add(new Movie(Paths.get(""), "Toy Story 3", 2011, 6974, new ArrayList<>(), new ArrayList<>()));
		movies.add(new Movie(Paths.get(""), "Max Max: Fury Road", 2015, 7802, new ArrayList<>(), new ArrayList<>()));
		channels.add(new Channel("Action Movies"));
		channels.add(new Channel("Tom Cruise Collection"));
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("My Movie Channels");
		
		Path path = LibraryHandler.getFilePath();
		if (path != null) {
			setLibrary(LibraryHandler.loadLibraryFromFile(path));
		}
		
		initRoot();
		
		displayChannels();
	}
	
	public void initRoot() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane)loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			
			RootLayoutController controller = loader.getController();
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
			
			ChannelOverviewController controller = loader.getController();
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
			
			LibraryOverviewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace(); //TODO
		}
	}
	
	public void setLibrary(LibraryWrapper wrapper) {
		channels.clear();
		channels.addAll(wrapper.getChannels());
		movies.clear();
		movies.addAll(wrapper.getMovies());
		moviePaths.clear();
		//Add path for each movie to set
		for (Movie m : wrapper.getMovies()) {
			moviePaths.add(m.getFilename());
		}	
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public ObservableList<Movie> getMovies() {
		return movies;
	}
	
	public ObservableList<Channel> getChannels() {
		return channels;
	}
	
	public Set<Path> getMoviePaths() {
		return moviePaths;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
