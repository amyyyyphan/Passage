package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPageController {
	@FXML
	private TextField username;
	
	@FXML
	private PasswordField password;
	
	@FXML 
	private Button loginButton;
	
	@FXML 
	private Button registerButton;
	
	@FXML
	public void loginButtonPressed(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent searchPageParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
		Scene scene = new Scene(searchPageParent,900,600);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}
	
	@FXML
	public void registerButtonPressed(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent searchPageParent = FXMLLoader.load(getClass().getResource("Registration.fxml"));
		Scene scene = new Scene(searchPageParent,900,600);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}

}