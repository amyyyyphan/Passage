package application;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class HomeController {
	@FXML
	private Button search;
	@FXML
	private Button offer;
	@FXML
	private MenuButton userButton;
	
	@FXML
	public void choice(ActionEvent event) throws IOException {	
		if (event.getSource() == search) {
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			Parent searchPageParent = FXMLLoader.load(getClass().getResource("Search.fxml"));
			Scene scene = new Scene(searchPageParent, 900, 600);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			window.setScene(scene);
			window.show();
		}
		else {
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			Parent offerPageParent = FXMLLoader.load(getClass().getResource("OfferPage.fxml"));
			Scene scene = new Scene(offerPageParent, 900, 600);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			window.setScene(scene);
			window.show();
		}
	}
	
	@FXML
	public void viewMyRides(ActionEvent event) throws IOException {
		
		//pass rides to My Rides page
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("MyRides.fxml"));
		Parent myRidesPageParent = loader.load();
		Scene myRidesPageScene = new Scene(myRidesPageParent);
		
		//access controller
		MyRidesController controller = loader.getController();
		//pass travel details to Drive Details page
		controller.initData(Main.viewMyRides());
		
		Stage window = (Stage) userButton.getScene().getWindow();
		myRidesPageScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(myRidesPageScene);
		window.show();
	}
	
	@FXML
	public void logout(ActionEvent event) throws IOException {
		Stage window = (Stage) userButton.getScene().getWindow();
		Parent loginPageParent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
		Scene scene = new Scene(loginPageParent, 900, 600);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}
}
