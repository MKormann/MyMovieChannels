package org.mrkproj.mmc.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.mrkproj.mmc.model.Genre;
import org.mrkproj.mmc.model.channel.Channel;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the Create Channel popup dialog
 * 
 * @author Matt Kormann
 */
public class CreateChannelController {

	@FXML
	private Label currentAction;
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
	private List<TextField> actorNames;
	private Map<Integer, CheckBox> checkBoxes;
	private Channel channel;
	private boolean submitted = false;
	
	@FXML
	private void initialize() {
		//Add text fields to a list for easy iteration
		actorNames = new ArrayList<>();
		actorNames.add(actorOne);
		actorNames.add(actorTwo);
		actorNames.add(actorThree);
		actorNames.add(actorFour);
		actorNames.add(actorFive);
		
		//Add checkboxes to map for easy access
		checkBoxes = new HashMap<>();
		checkBoxes.put(Genre.ACTION, checkAction);
		checkBoxes.put(Genre.ANIMATED, checkAnimated);
		checkBoxes.put(Genre.CLASSIC, checkClassic);
		checkBoxes.put(Genre.COMEDY, checkComedy);
		checkBoxes.put(Genre.DRAMA, checkDrama);
		checkBoxes.put(Genre.HORROR, checkHorror);
		checkBoxes.put(Genre.ROMANCE, checkRomance);
		checkBoxes.put(Genre.SCIFI, checkSciFi);
		checkBoxes.put(Genre.THRILLER, checkThriller);
		checkBoxes.put(Genre.WESTERN, checkWestern);
	}
	
	@FXML
	/**
	 * Check input, create new channel, and close dialog.
	 * Called on OK click.
	 */
	public void submitChannel() {
		if (isValid()) {
			submitted = true;
			if (channel == null) channel = new Channel(newChannelName.getText());
			else channel.setChannelName(newChannelName.getText());
			channel.setGenres(getSelectedGenres());
			channel.setActors(getActorList());
			dialogStage.close();
		}
	}
	
	/**
	 * Exit dialog with no action.
	 */
	@FXML
	public void cancel() {
		dialogStage.close();
	}
	
	/**
	 * @return whether or not user input is valid
	 */
	private boolean isValid() {
		if (newChannelName.getText() == null || newChannelName.getText().length() == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
            alert.setTitle("Form error.");
            alert.setHeaderText("Error with form input.");
            alert.setContentText("Channel name field cannot be empty.");

            alert.showAndWait();
            
            return false;
		}
		if (!isAnySelected() && getActorList().isEmpty()) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(dialogStage);
            alert.setTitle("No criteria selected");
            alert.setHeaderText("No genres or actors have been selected.  Channel will include all movies.");
            alert.setContentText("Is this ok?");

            Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
			    return true;
			}
            
            return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param set channel to be edited
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
		
		//Set channel name text field
		newChannelName.setText(channel.getChannelName());
		
		//Set check boxes to show channel's genres
		boolean[] genres = channel.getGenres();
		if (genres != null) {
			for (int i = 0; i < genres.length; i++) {
				checkBoxes.get(i).setSelected(genres[i]);
			}
		}
		//Set actor text fields to show channel's actors
		List<String> listActor = channel.getActors();
		int len = listActor.size();
		
		//Check if channel was assigned more than 5 actors
		if (len > 5) len = 5;
		
		for (int i = 0; i < len; i++) {
			if (listActor.get(i) != null) actorNames.get(i).setText(listActor.get(i));
			else actorNames.get(i).setText("");
		}
	}
	
	/**
	 * @return was submit clicked
	 */
	public boolean isSubmitted() {
		return submitted;
	}
	
	
	/**
	 * 
	 * @return list of genres checked by user
	 */
	private boolean[] getSelectedGenres() {
		boolean[] genres = new boolean[Genre.GENRES];
		for (Integer i : checkBoxes.keySet()) {
			if (checkBoxes.get(i).isSelected()) genres[i] = true;
		}
		return genres;
	}
	
	/**
	 * @return checks if there is at least one selection
	 */
	private boolean isAnySelected() {
		for (Integer i : checkBoxes.keySet()) {
			if (checkBoxes.get(i).isSelected()) return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return list of given actor names
	 */
	private List<String> getActorList() {
		ArrayList<String> actors = new ArrayList<>();
		for (TextField t : actorNames) {
			if (t.getText() != null && t.getText().length() != 0)
				actors.add(t.getText());
		}
		return actors;
	}
	
	/**
	 * @return the created channel
	 */
	public Channel getChannel() {
		return channel;
	}
	
	public void setStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setLabel(String currentAction) {
		this.currentAction.setText(currentAction);
	}
}