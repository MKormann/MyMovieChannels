package org.mrkproj.mmc.view;

import java.util.Optional;

import org.mrkproj.mmc.MainApp;
import org.mrkproj.mmc.model.channel.Channel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
		channelColumn.setCellValueFactory(data -> data.getValue().getChannelNameProperty());
		currentMovieColumn.setCellValueFactory(data -> data.getValue().getCurrentMovieProperty());
		//timeColumn.setCellValueFactory(data -> data.getValue().get);
		nextMovieColumn.setCellValueFactory(data -> data.getValue().getNextMovieProperty());
		
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
			dialogStage.showAndWait();
			
			if (controller.isSubmitted()) {
				channelTable.getItems().add(controller.getChannel());
			}
			
			//TODO Populate channel's queue and start 
		} catch (Exception e) {
			e.printStackTrace();
			//TODO
		}
	}
	
	/**
	 * Edit the channel criteria
	 */
	@FXML
	public void editChannel() {
		int index = channelTable.getSelectionModel().getSelectedIndex();
		if (index != -1) {
			Channel channel = channelTable.getItems().get(index);
			
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class.getResource("view/CreateChannel.fxml"));
				AnchorPane pane = (AnchorPane)loader.load();
				
				Stage dialogStage = new Stage();
				dialogStage.setTitle("Edit Channel");
				dialogStage.initModality(Modality.WINDOW_MODAL);
				dialogStage.initOwner(mainApp.getPrimaryStage());
				Scene scene = new Scene(pane);
				dialogStage.setScene(scene);
				
				CreateChannelController controller = loader.getController();
				controller.setStage(dialogStage);
				controller.setLabel("Edit Channel");
				controller.setChannel(channel);
				dialogStage.showAndWait();
				
				//TODO repopulate channel queue, either from end of current or replace current
			} catch (Exception e) {
				e.printStackTrace();
				//TODO
			}
		}
	}
	
	/**
	 * Delete the selected channel
	 */
	@FXML
	public void deleteChannel() {
		int index = channelTable.getSelectionModel().getSelectedIndex();
		if (index != -1) {
			Channel channel = channelTable.getItems().get(index);
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete channel");
			alert.setHeaderText("This will delete the channel \"" + channel.getChannelName() +"\"");
			alert.setContentText("Are you sure?");
	
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
			    channelTable.getItems().remove(index);
			}
		}
	}
}
