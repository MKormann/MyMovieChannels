package org.mrkproj.mmc.view;

import org.mrkproj.mmc.MainApp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ChannelOverviewController {

	@FXML
	private Label userChannels;
	
	//Reference to main application
	private MainApp mainApp;
	
	@FXML
	private void initialize() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
