package org.mrkproj.mmc.view;

import org.mrkproj.mmc.MainApp;
import org.mrkproj.mmc.model.channel.Channel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ChannelOverviewController {

	@FXML
	private Label channelLabel;
	@FXML
	private TableView<Channel> channelTable;
	@FXML
	private TableColumn<Channel, String> channelColumn;
	@FXML
	private TableColumn<Channel, String> currentMovieColumn;
	@FXML
	private TableColumn<Channel, String> timeColumn;
	@FXML
	private TableColumn<Channel, String> nextMovieColumn;
	
	private MainApp mainApp;
	
	@FXML
	private void initialize() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		channelTable.setItems(mainApp.getChannels());
	}
	
	/**
	 * Switch view to the library overview
	 */
	@FXML
	public void goToLibrary() {
		mainApp.displayLibrary();
	}
	
	/**
	 * Launch the selected channel
	 */
	@FXML
	public void playChannel() {
		int index = channelTable.getSelectionModel().getSelectedIndex();
		
	}
	
	/**
	 * Create new channel
	 */
	@FXML
	public void createChannel() {
		
	}
	
	/**
	 * Edit the channel criteria
	 */
	@FXML
	public void editChannel() {
		
	}
	
	/**
	 * Delete the selected channel
	 */
	@FXML
	public void deleteChannel() {
		
	}
}
