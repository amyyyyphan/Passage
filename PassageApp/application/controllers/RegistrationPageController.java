package application.controllers;

import java.io.IOException;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
	private Label firstNameError;
	@FXML
	private Label lastNameError;
	@FXML
	private Label emailError;
	@FXML
	private Label phoneError;
	@FXML
	private Label passwordError;
	@FXML
	private Label emailAlreadyExistError;
	
	@FXML
	private AnchorPane registrationConfirmation;
	@FXML
	private Button okButton;
	
	@FXML
	public void registerButtonPressed(ActionEvent event) throws IOException {
		removeErrors();
		
		//checking that information entered is valid
		boolean validAccount = true;
		
		if (firstName.getText().isEmpty() || !validateName(firstName.getText())) {
			validAccount = false;
			firstNameError.setVisible(true);
			System.out.println("Invalid first name");
		}
		if (lastName.getText().isEmpty() || !validateName(lastName.getText())) {
			validAccount = false;
			System.out.println("Invalid last name");
			lastNameError.setVisible(true);
		}
		
		if (!validateEmail(username.getText())) {
			validAccount = false;
			System.out.println("Invalid email");
			emailError.setVisible(true);
		} else if (!Main.emailAvailable(username.getText())) {
			validAccount = false;
			emailAlreadyExistError.setVisible(true);
		}
		
		if (!validatePhone(phone.getText())) {
			validAccount = false;
			System.out.println("Invalid phone");
			phoneError.setVisible(true);
		}
		
		if(!validatePassword(password.getText())) {
			validAccount = false;
			System.out.println("Invalid password: " + password.getText());
			passwordError.setVisible(true);
		}
		
		if(!agreement.isSelected()) {
			validAccount = false;
			System.out.println("Checkbox not selected");
		}
		
		//if there's nothing wrong with information given and agreement is checked, add user data to database and show confirmation window
		if (validAccount) {
			Main.addUser(firstName.getText(), lastName.getText(), username.getText(), password.getText(), phone.getText());
			Main.changeCurrentUser(username.getText());
			
			showConfirmationWindow();
		}
	}
	
	@FXML
	public void okButtonPressed(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent searchPageParent = FXMLLoader.load(getClass().getResource("/application/fxml/Home.fxml"));
		Scene scene = new Scene(searchPageParent,900,600);
		
		scene.getStylesheets().add(getClass().getResource("/application/css/application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}
	
	public void showConfirmationWindow() {
		registrationConfirmation.setVisible(true);
	}
	
	//remove errors
	//function is called to remove errors when registration page is loaded or to remove previous errors
	private void removeErrors() {
		firstNameError.setVisible(false);
		lastNameError.setVisible(false);
		emailError.setVisible(false);
		phoneError.setVisible(false);
		passwordError.setVisible(false);
		emailAlreadyExistError.setVisible(false);
		
	}
	
	//checks if first/last name is a valid name
	private boolean validateName(String name) {
		return name.matches("^[a-zA-Z\\\\s]*$");
	}
	
	//checks if email address is a valid email address
	private boolean validateEmail(String email) {
		//pattern for email
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		return pattern.matcher(email).matches();
	}
	
	//checks that phone number is a valid phone number
	private boolean validatePhone(String phone) {
		//ex: 1234567890
		if (phone.matches("\\d{10}")) {
			return true;
		//ex:(408)123-4567
		} else if (phone.matches("\\(\\d{3}\\)\\d{3}-\\d{4}")){
			return true;
		} else {
			return false;
		}
	}
	
	//checks that the password meets all password requirements
	//Password must be:
	//	Made out of characters from the ASCII code
	//	At least 8 characters long
	//	At least one capital letter
	//	At least one special character
	//	At least one number
	private boolean validatePassword(String password) {
		return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=])(?=\\S+$).{8,}$");
	}
}
