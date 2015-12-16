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
public class AreYouSureController {

	/**
	 * This is the question label where you input your question.
	 */
	@FXML
	private Label questionLabel;

	/**
	 * The action event when the user clicks the No button.
	 * 
	 * @param event
	 */
	@FXML
	void onNoClick(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

}
