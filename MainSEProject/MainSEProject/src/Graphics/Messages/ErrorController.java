package Graphics.Messages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This is the are you sure controller for AreYouSureMenu.fxml.
 * 
 * @author sfyock
 */
public class ErrorController {

	/**
	 * This is the error label where.
	 */
	@FXML
	private Label errorLabel;

	/**
	 * The action event when the user clicks the Ok button.
	 * 
	 * @param event
	 */
	@FXML
	void onOkClick(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

}
