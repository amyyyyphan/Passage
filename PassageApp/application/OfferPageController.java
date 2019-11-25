package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OfferPageController {
	@FXML
	private Button backButton;
	@FXML
	private Button nextButton;
	@FXML
	private TextField startInfo;
	@FXML
	private TextField destinationInfo;
	@FXML
	private DatePicker dateInfo;
	@FXML
	private TextField timeInfo;
	@FXML
	private Label error;
	
	@FXML
	public void buttonChosen(ActionEvent event) throws IOException {
		if (event.getSource() == backButton) {
			//if back button was clicked, go back to home page
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			Parent searchPageParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
			Scene scene = new Scene(searchPageParent, 900, 600);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			window.setScene(scene);
			window.show();
		} else {
			//next button clicked
			//if there's missing information, stay on offer page and show user an error
			if (startInfo.getText().isEmpty() || destinationInfo.getText().isEmpty() || dateInfo.getValue() == null || timeInfo.getText().isEmpty()) {
				error.setText("Please fill out all fields.");
			} else {	
				String startingPoint = startInfo.getText();
				String destination = destinationInfo.getText();
				String time = timeInfo.getText();
					
				//convert date to string
				LocalDate date = dateInfo.getValue();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				String formattedDate = date.format(formatter);
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("DriveDetails.fxml"));
				Parent driveDetailPageParent = loader.load();
				Scene driveDetailsPageScene = new Scene(driveDetailPageParent);
				
				//access controller
				DriveDetailsController controller = loader.getController();
				//pass travel details to Drive Details page
				controller.travelDetails(startingPoint, destination, formattedDate, time);
				
				Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
				driveDetailsPageScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				window.setScene(driveDetailsPageScene);
				window.show();
			}
		}
		
	}
}
