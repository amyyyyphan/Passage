package application;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import  javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class MainController {
	@FXML
	private Label brand;
	
	@FXML
	private Label greeting;
	
	@FXML
	private Button search;
	
	@FXML
	private Button offer;
	
	@FXML
	public void choice(ActionEvent event) throws IOException {
		//gets stage information
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		if (event.getSource() == search) {
			Parent searchPageParent = FXMLLoader.load(getClass().getResource("Search.fxml"));
			Scene scene = new Scene(searchPageParent, 600, 400);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			window.setScene(scene);
			window.show();
		}
		else {
			Parent offerPageParent = FXMLLoader.load(getClass().getResource("OfferPage.fxml"));
			Scene scene = new Scene(offerPageParent,600,400);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			window.setScene(scene);
			window.show();
		}
	}
}
