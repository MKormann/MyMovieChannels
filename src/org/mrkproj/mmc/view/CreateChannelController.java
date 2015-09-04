package org.mrkproj.mmc.view;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

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
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	public void submitNewChannel() {
		
	}
	
	@FXML
	public void cancel() {
		
	}
	
	/**
	 * @return whether or not user input is valid
	 */
	private boolean isValid() {
		return true;
	}
}