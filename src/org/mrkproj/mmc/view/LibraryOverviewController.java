package org.mrkproj.mmc.view;

import org.mrkproj.mmc.MainApp;
import org.mrkproj.mmc.model.movie.Movie;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LibraryOverviewController {

	@FXML
	private TableView<Movie> movieTable;
	@FXML
	private TableColumn<Movie, String> titleColumn;
	@FXML
	private TableColumn<Movie, String> yearColumn;
	@FXML
	private TableColumn<Movie, String> lengthColumn;
	@FXML
	private TableColumn<Movie, String> starringColumn;
	@FXML
	private TableColumn<Movie, String> genreColumn;
	
	private MainApp mainApp;
	
	@FXML
	private void initialize() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
