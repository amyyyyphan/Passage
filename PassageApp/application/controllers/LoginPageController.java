package application.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPageController {
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Label error;
	@FXML 
	private Button loginButton;
	@FXML 
	private Button registerButton;
	
	@FXML
	public void loginButtonPressed(ActionEvent event) throws IOException {
		//check if username and password are correct
		if(Main.checkAccountInfo(username.getText(), password.getText())) {
			Main.changeCurrentUser(username.getText());
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			Parent searchPageParent = FXMLLoader.load(getClass().getResource("/application/fxml/Home.fxml"));
			Scene scene = new Scene(searchPageParent,900,600);
			
			scene.getStylesheets().add(getClass().getResource("/application/css/application.css").toExternalForm());
			window.setScene(scene);
			window.show();
		} else {
			//if username or password is incorrect, do not let user go pass login page and show error
			error.setVisible(true);
		}
	}
	
	@FXML
	public void registerButtonPressed(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent searchPageParent = FXMLLoader.load(getClass().getResource("/application/fxml/Registration.fxml"));
		Scene scene = new Scene(searchPageParent,900,600);
		
		scene.getStylesheets().add(getClass().getResource("/application/css/application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}
}
