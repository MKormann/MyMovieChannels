package org.mrkproj.mmc.view;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.mrkproj.mmc.MainApp;
import org.mrkproj.mmc.model.channel.Channel;
import org.mrkproj.mmc.model.movie.Movie;

import com.xuggle.xuggler.IContainer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	private TableColumn<Movie, String> lengthColumn;
	@FXML
	private TableColumn<Movie, String> starringColumn;
	@FXML
	private TableColumn<Movie, String> genreColumn;
	
	private final String extensions = "Video Files (*.3gp *.avi *.divx *.f4v *.flv *.m4v *.mkv *.mov *.mp4 *.mpeg *.mpg "
										+ "*.mov *.vob *.webm *.wmv)";
	
	private final String[] extList = {"*.3gp", "*.avi", "*.divx", "*.f4v", "*.flv", "*.m4v", "*.mkv", 
											"*.mov", "*.mp4", "*.mpeg", "*.mpg", "*.mov", "*.vob", "*.webm", "*.wmv"};
	
	private final String[] extList2 = {"3gp", "avi", "divx", "f4v", "flv", "m4v", "mkv", 
											"mov", "mp4", "mpeg", "mpg", "mov", "vob", "webm", "wmv"};
	
	private MainApp mainApp;
	
	public LibraryOverviewController() {
		
	}
	
	@FXML
	private void initialize() {
		titleColumn.setCellValueFactory(data -> data.getValue().getTitleProperty());
		yearColumn.setCellValueFactory(data -> data.getValue().getYearProperty());
		lengthColumn.setCellValueFactory(data -> data.getValue().getLengthProperty().asString());
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
	 * Switch view to Channels
	 */
	public void goToChannels() {
		mainApp.displayChannels();
	}
	
	/**
	 * Create a channel using the currently selected movie as a starting point.
	 */
	@FXML
	public void createChannel() {
		int index = movieTable.getSelectionModel().getSelectedIndex();
		if (index >= 0) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class.getResource("view/CreateChannel.fxml"));
				AnchorPane pane = (AnchorPane)loader.load();
				
				Stage dialogStage = new Stage();
				dialogStage.setTitle("Create Channel");
				dialogStage.initModality(Modality.WINDOW_MODAL);
				dialogStage.initOwner(mainApp.getPrimaryStage());
				Scene scene = new Scene(pane);
				dialogStage.setScene(scene);
				
				CreateChannelController controller = loader.getController();
				controller.setStage(dialogStage);
				controller.setLabel("Create New Channel");
				
				Movie movie = movieTable.getItems().get(index);
				Channel channel = new Channel(movie.getTitle() + " Channel");
				channel.setGenres(movie.getGenres());
				channel.setActors(movie.getActors());
				controller.setChannel(channel);
				
				dialogStage.showAndWait();
				
				if (controller.isSubmitted()) {
					mainApp.getChannels().add(channel);
				}
				
				//TODO Populate channel's queue and start 
			} catch (Exception e) {
				e.printStackTrace();
				//TODO
			}
		
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No selection");
			alert.setHeaderText("No movie selected.");
			alert.setContentText("Select a movie from table to begin channel creation.");
			
			alert.showAndWait();
		}
	}
	
	/**
	 * Choose folder to add to library
	 */
	@FXML
	public void addFolder() {
		DirectoryChooser chooser = new DirectoryChooser();

		File directory = chooser.showDialog(mainApp.getPrimaryStage());
		if (directory != null && directory.isDirectory()) {
			for (File file : FileUtils.listFiles(directory, extList2, true)) {
				Path path = Paths.get(file.toURI());
				if (path != null && !mainApp.getMoviePaths().contains(path)) {
					try {
						Movie movie = new Movie(path);
						IContainer container = IContainer.make();
						if (container.open(path.toString(), IContainer.Type.READ, null) < 0)
							throw new RuntimeException();
						movie.setLength((int)(container.getDuration() / 1000000));
						mainApp.getMovies().add(movie);
						mainApp.getMoviePaths().add(path);
					} catch (Exception e) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.initOwner(mainApp.getPrimaryStage());
						alert.setTitle("File read error");
						alert.setHeaderText("Cannot read video information.");
						alert.setContentText("Video file will not be added.");
						
						alert.showAndWait();	
					}
				}
			}
		}
	}
	
	/**
	 * Choose single file to add to library
	 */
	@FXML
	public void addFile() {
		FileChooser chooser = new FileChooser();
		ExtensionFilter filter = new ExtensionFilter(extensions, extList);
		chooser.getExtensionFilters().add(filter);
		
		File file = chooser.showOpenDialog(mainApp.getPrimaryStage());
		Path path = Paths.get(file.toURI());
		
		if (path != null && !mainApp.getMoviePaths().contains(path)) {
			try {
				Movie movie = new Movie(path);
				IContainer container = IContainer.make();
				if (container.open(path.toString(), IContainer.Type.READ, null) < 0)
					throw new RuntimeException();
				movie.setLength((int)(container.getDuration() / 1000000));
				mainApp.getMovies().add(movie);
				mainApp.getMoviePaths().add(path);
				editMovie(movie);
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(mainApp.getPrimaryStage());
				alert.setTitle("File read error");
				alert.setHeaderText("Cannot read video information.");
				alert.setContentText("Video file will not be added.");
				
				alert.showAndWait();	
			}
		}
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
	
	@FXML
	public void editMovieFromTable() {
		int index = movieTable.getSelectionModel().getSelectedIndex();
		if (index >= 0) {
			editMovie(movieTable.getItems().get(index));
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No selection");
			alert.setHeaderText("No movie selected.");
			alert.setContentText("Select a movie from table to remove.");
			
			alert.showAndWait();
		}
	}
	
	/**
	 * Edit movie
	 */
	private void editMovie(Movie movie) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/EditMovie.fxml"));
			AnchorPane pane = (AnchorPane)loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Movie");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(mainApp.getPrimaryStage());
			Scene scene = new Scene(pane);
			dialogStage.setScene(scene);
			
			EditMovieController controller = loader.getController();
			controller.setStage(dialogStage);
			controller.setLabel("Edit Movie");
			controller.setMovie(movie);
			
			dialogStage.showAndWait(); 
		} catch (Exception e) {
			e.printStackTrace();
			//TODO
		}
	}
}
