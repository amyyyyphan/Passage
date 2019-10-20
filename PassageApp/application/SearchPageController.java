package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SearchPageController {
	@FXML
	private Button backButton;
	
	@FXML
	private Button nextButton;
	
	public void buttonChosen(ActionEvent event) throws IOException {
		//gets stage information
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		if (event.getSource() == backButton) {
			Parent searchPageParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
			Scene scene = new Scene(searchPageParent,600,400);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			window.setScene(scene);
			window.show();
		}
	}
}
