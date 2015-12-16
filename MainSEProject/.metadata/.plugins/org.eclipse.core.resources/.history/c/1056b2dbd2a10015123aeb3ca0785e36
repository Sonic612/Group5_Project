package Graphics.Message;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AreYouSureController {
	
    @FXML
    private Label questionLabel;
    
    @FXML
	public void onYesClick(ActionEvent event) {
    	System.out.println("This didn't work so well...");
    }

    @FXML
    void onNoClick(ActionEvent event) {
    	Node  source = (Node)  event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
