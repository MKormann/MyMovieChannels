package org.mrkproj.mmc.view;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.mrkproj.mmc.MainApp;
import org.mrkproj.mmc.model.LibraryHandler;
import org.mrkproj.mmc.model.LibraryWrapper;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class RootLayoutController {

	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void newLibrary() {
		mainApp = new MainApp();
		mainApp.getChannels().clear();
		mainApp.getMovies().clear();
		mainApp.getMoviePaths().clear();
		LibraryHandler.setFilePath(null);
	}
	
	@FXML
	private void openLibrary() {
		FileChooser chooser = new FileChooser();
		
		//Set extension filter
		ExtensionFilter filter = new ExtensionFilter(
				"XML files (*.xml)", "*.xml"); 
		chooser.getExtensionFilters().add(filter);
		
		//Show save file dialog
		File file = chooser.showOpenDialog(mainApp.getPrimaryStage());
		if (file != null) {
			Path path = Paths.get(file.toURI());
			mainApp.setLibrary(LibraryHandler.loadLibraryFromFile(path));
		}
			
	}
	
	@FXML
	private void saveLibrary() {
		Path path = LibraryHandler.getFilePath();
		if (path != null) {
			LibraryWrapper wrapper = new LibraryWrapper();
			wrapper.setChannels(mainApp.getChannels());
			wrapper.setMovies(mainApp.getMovies());
			LibraryHandler.saveLibraryToFile(path, wrapper);
		} else {
			saveLibraryAs();
		}
	}
	
	@FXML
	private void saveLibraryAs() {
        FileChooser chooser = new FileChooser();

        // Set extension filter
        ExtensionFilter filter = new ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        chooser.getExtensionFilters().add(filter);

        // Show save file dialog
        File file = chooser.showSaveDialog(mainApp.getPrimaryStage());
        if (file != null) {
            Path path = Paths.get(file.toURI());
            LibraryWrapper wrapper = new LibraryWrapper();
			wrapper.setChannels(mainApp.getChannels());
			wrapper.setMovies(mainApp.getMovies());
            LibraryHandler.saveLibraryToFile(path, wrapper);
        }
	}
	
	/**
     * Close the app.
     */
    @FXML
    private void exitApp() {
        System.exit(0);
    }
}
