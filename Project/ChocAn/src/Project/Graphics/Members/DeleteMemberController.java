package Graphics.Members;

import java.io.IOException;

import Graphics.graphicsStart;
import Main.ChocAn;
import Main.DCOperator;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DeleteMemberController{

    @FXML
    private TextField memUsernameField;
    
    private Button buttonYes;
    
    @FXML
    void setLabel(Label l){
    	l.setText(l.getText() + "\nUsername:\t" + memUsernameField.getText());
    }
    
    @FXML
    void error(){
    	//Error Menu setup
    	GridPane paneArea = new GridPane();
    	Label label;
    	
    	try {
			paneArea = (GridPane) FXMLLoader.load(graphicsStart.class.getResource("gui/Main/ErrorMessageMenu.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	label = (Label) paneArea.getChildren().get(1);
    	setErrorLabel(label);
    	Stage s = new Stage();
    	s.setScene(new Scene(paneArea));
    	s.setTitle("Error...");
    	s.show();
    }
    
    @FXML
    void setErrorLabel(Label l){
    	l.setText(l.getText() 
    			+ "You are missing the username of the member you want to delete.");
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	void onYesClick(Button button){
    	button.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				DCOperator op = ChocAn.getDCOperator();
		    	System.out.println("This worked!_2");
		    	//*ACTIVATE*op.delMember(memUsernameField.getText());
			}
		   
		});
    }
    
	@FXML
    void onDeleteClick(ActionEvent event) {
    	if(!memUsernameField.getText().isEmpty()){
    		
    		Pane paneArea = new Pane();
    		Label label;
    		
    		try {
				paneArea = (Pane) FXMLLoader.load(graphicsStart.class.getResource("gui/Main/AreYouSureMenu.fxml"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    		label = (Label) paneArea.getChildren().get(2);
    		setLabel(label);
    		buttonYes = (Button) paneArea.getChildren().get(0);
    		onYesClick(buttonYes);
    		Stage s = new Stage();
    		s.setScene(new Scene(paneArea));
    		s.show();
    		
    	}
    	else
    		error();
    }
    
	@FXML
    void onCancelClick(ActionEvent event) {
    	Pane paneArea = new Pane();
    	
    	try {
			paneArea = (Pane) FXMLLoader.load(graphicsStart.class.getResource("gui/Members/MemberOptionsMenu.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	Stage s = new Stage();
    	s.setScene(new Scene(paneArea));
    	s.setTitle("Member Options");
    	s.show();
    	
    	Node  source = (Node)  event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
