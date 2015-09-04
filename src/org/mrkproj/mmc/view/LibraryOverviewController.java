package org.mrkproj.mmc.view;

import org.mrkproj.mmc.MainApp;
import org.mrkproj.mmc.model.movie.Movie;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LibraryOverviewController {

	@FXML
	private Label libraryLabel;
	@FXML
	private TableView<Movie> movieTable;
	@FXML
	private TableColumn<Movie, String> titleColumn;
	@FXML
	private TableColumn<Movie, Number> yearColumn;
	@FXML
	private TableColumn<Movie, Number> lengthColumn;
	@FXML
	private TableColumn<Movie, String> starringColumn;
	@FXML
	private TableColumn<Movie, String> genreColumn;
	
	private MainApp mainApp;
	
	public LibraryOverviewController() {
		
	}
	
	@FXML
	private void initialize() {
		titleColumn.setCellValueFactory(data -> data.getValue().getTitleProperty());
		yearColumn.setCellValueFactory(data -> data.getValue().getYearProperty());
		lengthColumn.setCellValueFactory(data -> data.getValue().getLengthProperty());
		starringColumn.setCellValueFactory(data -> data.getValue().getActorProperty().asString());
		genreColumn.setCellValueFactory(data -> data.getValue().getGenreProperty().asString());
		
	}
	
	/**
	 * Set reference to main application.
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		movieTable.setItems(mainApp.getMovies());
	}
	
	/**
	 * Create a channel using the currently selected movie as a starting point.
	 */
	@FXML
	public void createChannel() {
		int index = movieTable.getSelectionModel().getSelectedIndex();
		if (index >= 0) {
		
			//TODO remove movie from all channels except if currently playing.
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No selection");
			alert.setHeaderText("No movie selected.");
			alert.setContentText("Select a movie from table to being channel creation.");
			
			alert.showAndWait();
		}
	}
	
	/**
	 * Choose folder to add to library
	 */
	@FXML
	public void addFolder() {
		
	}
	
	/**
	 * Choose single file to add to library
	 */
	@FXML
	public void addFile() {
		
	}
	
	/**
	 * Remove movie file from library
	 */
	@FXML
	public void removeMovie() {
		int index = movieTable.getSelectionModel().getSelectedIndex();
		if (index >= 0) {
			movieTable.getItems().remove(index);
			//TODO remove movie from all channels except if currently playing.
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No selection");
			alert.setHeaderText("No movie selected.");
			alert.setContentText("Select a movie from table to remove.");
			
			alert.showAndWait();
		}
	}
}
