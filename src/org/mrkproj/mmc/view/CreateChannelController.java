package org.mrkproj.mmc.view;

import org.mrkproj.mmc.model.channel.Channel;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the Create Channel popup dialog
 * 
 * @author Matt Kormann
 */
public class CreateChannelController {

	@FXML
	private TextField newChannelName;
	@FXML
	private TextField actorOne;
	@FXML
	private TextField actorTwo;
	@FXML
	private TextField actorThree;
	@FXML
	private TextField actorFour;
	@FXML
	private TextField actorFive;
	@FXML
	private CheckBox checkAction;
	@FXML
	private CheckBox checkAnimated;
	@FXML
	private CheckBox checkClassic;
	@FXML
	private CheckBox checkComedy;
	@FXML
	private CheckBox checkDrama;
	@FXML
	private CheckBox checkHorror;
	@FXML
	private CheckBox checkRomance;
	@FXML
	private CheckBox checkSciFi;
	@FXML
	private CheckBox checkThriller;
	@FXML
	private CheckBox checkWestern;
	
	private Stage dialogStage;
	private Channel channel;
	private boolean submitted = false;
	
	@FXML
	private void initialize() {
		
	}
	
	public void setStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	@FXML
	public void submitNewChannel() {
		if (isValid()) {
			submitted = true;
			dialogStage.close();
		}
	}
	
	@FXML
	public void cancel() {
		dialogStage.close();
	}
	
	/**
	 * @return whether or not user input is valid
	 */
	private boolean isValid() {
		if (newChannelName.getText() == null || newChannelName.getText().length() == 0) {
			
		}
		return true;
	}
	
	/**
	 * @return the created channel
	 */
	public Channel getChannel() {
		return channel;
	}
	
	/**
	 * @return was submit clicked
	 */
	public boolean isSubmitted() {
		return submitted;
	}
}