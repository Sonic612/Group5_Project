package Graphics.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * This is the login controller for MainLogin.fxml.
 * @author sfyock
 */
public class LoginController {

	/**
	 * The text field for the user name.
	 */
	@FXML
	private TextField userField;
	
	/**
	 * The password field for the user's password.
	 */
	@FXML
	private PasswordField passField;

	/**
	 * The action event for when the user clicks the login button.
	 * @param event
	 */
	@FXML
	void onLoginClick(ActionEvent event) {

	}

	/**
	 * The action event for when the user clicks the quit button.
	 * @param event
	 */
	@FXML
	void onQuitClick(ActionEvent event) {

	}

}
