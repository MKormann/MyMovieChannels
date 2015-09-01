package org.mrkproj.mmc.view;

import org.mrkproj.mmc.MmcApp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ChannelOverviewController {

	@FXML
	private Label userChannels;
	
	//Reference to main application
	private MmcApp mmcApp;
	
	@FXML
	private void initialize() {
		
	}
	
	public void setMainApp(MmcApp mmcApp) {
		this.mmcApp = mmcApp;
	}
}
