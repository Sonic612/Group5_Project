package Graphics.Messages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 * This is the message controller for ReportMessageMenu.fxml.
 * @author sfyock
 *
 */
public class ReportMessageController {

	/**
	 * This is the scroll pane.
	 */
	@FXML
    private ScrollPane scrollPane;
	
	/**
	 * This is the report label.
	 */
	@FXML
	private Label reportLabel;
    
    /**
	 * The action event when the user clicks the ok button.
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