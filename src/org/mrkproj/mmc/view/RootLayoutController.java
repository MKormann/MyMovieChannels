package org.mrkproj.mmc.view;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.mrkproj.mmc.MainApp;
import org.mrkproj.mmc.model.LibraryHandler;
import org.mrkproj.mmc.model.LibraryWrapper;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

public class RootLayoutController {

	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
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
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        Path path = Paths.get(fileChooser.showSaveDialog(mainApp.getPrimaryStage()).toURI());

        if (path != null) {
            // Make sure it has the correct extension
            if (!path.endsWith(".xml")) {
                path = Paths.get(path.toUri() + ".xml");
            }
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
