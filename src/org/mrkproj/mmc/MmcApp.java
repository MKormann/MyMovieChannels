package org.mrkproj.mmc;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main application class
 * 
 * @author Matt Kormann
 *
 */
public class MmcApp extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("My Movie Channels");
		
		initRoot();
	}
	
	public void initRoot() {
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
