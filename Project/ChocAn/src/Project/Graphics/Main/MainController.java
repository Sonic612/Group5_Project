package Graphics.Main;

import java.io.IOException;

import Graphics.graphicsStart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This the the controller for MainMenu.fxml.
 * @author sfyock
 */
public class MainController {
	 
	/**The Welcome Label for the main menu.*/
    @FXML
    private Label welcomeLabel;
    
    /**
     * The action when you click the member options button.
     */
    @FXML
    void onMemClick(ActionEvent event) {
    	Stage s = new Stage();
    	
    	Pane mainpane = null;
		try {
			mainpane = (Pane) FXMLLoader.load(graphicsStart.class.getResource("gui/Members/MemberOptionsMenu.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setScene(new Scene(mainpane));
    	s.setTitle("Member Options");
    	s.show();
    	
    	Node  source = (Node)  event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    /**
     * The action when you click the provider options button.
     */
    @FXML
    void onProvClick(ActionEvent event) {
    	Stage s = new Stage();
    	
    	Pane mainpane = null;
		try {
			mainpane = (Pane) FXMLLoader.load(graphicsStart.class.getResource("gui/Providers/ProviderOptionsMenu.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setScene(new Scene(mainpane));
    	s.setTitle("Provider Options");
    	s.show();
    	
    	Node  source = (Node)  event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    /**
     * The action when you click the Report options button.
     */
    @FXML
    void onRepClick(ActionEvent event) {
    	Stage s = new Stage();
    	
    	Pane mainpane = null;
		try {
			mainpane = (Pane) FXMLLoader.load(graphicsStart.class.getResource("gui/Reports/ReportOptionsMenu.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setScene(new Scene(mainpane));
    	s.setTitle("Report Options");
    	s.show();
    	
    	Node  source = (Node)  event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    /**
     * The action when you click the Quit button.
     */
    @FXML
    void onQuitClick(ActionEvent event) {
    	System.exit(0);
    }
}
