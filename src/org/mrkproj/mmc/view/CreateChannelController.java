package org.mrkproj.mmc.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mrkproj.mmc.model.Genre;
import org.mrkproj.mmc.model.channel.Channel;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	private List<TextField> actorNames;
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
	}
	
	public void setStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	@FXML
	/**
	 * Check input, create new channel, and close dialog.
	 * Called on OK click.
	 */
	public void submitNewChannel() {
		if (isValid()) {
			submitted = true;
			channel = new Channel(newChannelName.getText());
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
		if (getSelectedGenres().isEmpty() && getActorList().isEmpty()) {
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
	 * @return list of genres checked by user
	 */
	private List<Genre> getSelectedGenres() {
		ArrayList<Genre> genres = new ArrayList<>();
		if (checkAction.isSelected()) genres.add(Genre.ACTION);
		if (checkAnimated.isSelected()) genres.add(Genre.ANIMATED);
		if (checkClassic.isSelected()) genres.add(Genre.CLASSIC);
		if (checkComedy.isSelected()) genres.add(Genre.COMEDY);
		if (checkDrama.isSelected()) genres.add(Genre.DRAMA);
		if (checkHorror.isSelected()) genres.add(Genre.HORROR);
		if (checkRomance.isSelected()) genres.add(Genre.ROMANCE);
		if (checkSciFi.isSelected()) genres.add(Genre.SCIFI);
		if (checkThriller.isSelected()) genres.add(Genre.THRILLER);
		if (checkWestern.isSelected()) genres.add(Genre.WESTERN);
		return genres;
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
	
	/**
	 * @return was submit clicked
	 */
	public boolean isSubmitted() {
		return submitted;
	}
}