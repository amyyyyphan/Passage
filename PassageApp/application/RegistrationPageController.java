package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationPageController {
	@FXML
	private TextField firstName;
	
	@FXML
	private TextField lastName;
	
	@FXML
	private TextField username;
	
	@FXML
	private TextField phone;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private CheckBox agreement;
	
	@FXML
	private Button registerButton;
	
	@FXML
	public void registerButtonPressed(ActionEvent event) throws IOException {
		//checking that information entered is valid
		boolean validAccount = true;
		if (!validateName(firstName.getText())) {
			validAccount = false;
			//label
		}
		if (!validateName(lastName.getText())) {
			validAccount = false;
			//label
		}
		
		if (validAccount) {
			Main.addUser(firstName.getText(), lastName.getText(), username.getText(), password.getText(), phone.getText());
		}
		
		
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent searchPageParent = FXMLLoader.load(getClass().getResource("Registration.fxml"));
		Scene scene = new Scene(searchPageParent,900,600);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}
	
	private boolean validateName(String name) {
		return name.matches("^[a-zA-Z\\\\s]*$");
	}
	
	private boolean validateEmail(String email) {
		return true;
	}
}
