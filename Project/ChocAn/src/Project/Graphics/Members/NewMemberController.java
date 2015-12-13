package Graphics.Members;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParsePosition;

import Graphics.graphicsStart;
import Graphics.Message.AreYouSureController;
import Main.ChocAn;
import Main.DCOperator;
import Main.Member;
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
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NewMemberController extends AreYouSureController{
	
	@FXML
	DecimalFormat format = new DecimalFormat( "#" );
	
	@FXML
	private Button buttonYes;
	
    @FXML
    private String fullName;
    
    @FXML
    private TextField usernameField;
	
	@FXML
    private TextField lNameField;

    @FXML
    private TextField fNameField;
    
    @FXML
    private TextField stAddrField;
    
    @FXML
    private TextField cityField;
    
    @FXML
    private TextField stateField;

    @FXML
    private TextField zipCodeField;
    
    @FXML
    void initialize(){
    		zipCodeField.setTextFormatter( new TextFormatter<>(c ->
        	{
        	    if ( c.getControlNewText().isEmpty() )
        	    {
        	        return c;
        	    }

        	    ParsePosition parsePosition = new ParsePosition( 0 );
        	    Object object = format.parse( c.getControlNewText(), parsePosition );
        	    
        	    if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
        	    {
        	        return null;
        	    }
        	    else if(c.getControlNewText().length() > 5)
        	    {
        	        return null;
        	    }
        	    else{
        	    	return c;
        		}
        	}));
    		stateField.setTextFormatter( new TextFormatter<>(c ->
        	{
        	    if ( c.getControlNewText().isEmpty() )
        	    {
        	        return c;
        	    }
        	    
        	    if(c.getControlNewText().length() > 2)
        	    {
        	        return null;
        	    }
        	    else if(!isAllUpper(c.getControlNewText())){
        	    	c.setText(c.getText().toUpperCase());
        	    	return c;
        	    }
        	    else{
        	    	return c;
        		}
        	}));
    }
    
    @FXML
    boolean isAllUpper(String s) {
        for(char c : s.toCharArray()) {
           if(Character.isLetter(c) && Character.isLowerCase(c)) {
               return false;
            }
        }
        return true;
    }
    
    @FXML
    void setLabel(Label l){
    	l.setText(l.getText() 
    			+ "\nMember ID:\t" + usernameField.getText()
    			+ "\nName:\t\t" + fullName
    			+ "\nAddress:\t\t" + stAddrField.getText()
    			+ "\nState:\t\t" + stateField.getText()
    			+ "\nZip Code:\t\t" + zipCodeField.getText());
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
    	setErrorLabel(label, "something");
    	Stage s = new Stage();
    	s.setScene(new Scene(paneArea));
    	s.setTitle("Error...");
    	s.show();
    }
    
    @FXML
    void error(String str){
    	//Error Menu setup
    	GridPane paneArea = new GridPane();
    	Label label;
    	
    	try {
			paneArea = (GridPane) FXMLLoader.load(graphicsStart.class.getResource("gui/Main/ErrorMessageMenu.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	label = (Label) paneArea.getChildren().get(1);
    	setErrorLabel(label, str);
    	Stage s = new Stage();
    	s.setScene(new Scene(paneArea));
    	s.setTitle("Error...");
    	s.show();
    }
    
    @FXML
    void setErrorLabel(Label l, String str){
    	l.setText(l.getText() 
    			+ "You are missing " + str + " in your new member.");
    }
    
    boolean checkEmptyErrors(){
    	if(usernameField.getText().isEmpty()
    			|| lNameField.getText().isEmpty() && fNameField.getText().isEmpty()
    			|| stAddrField.getText().isEmpty()
    			|| stateField.getText().isEmpty()
    			|| cityField.getText().isEmpty()
    			|| zipCodeField.getText().isEmpty())
    		return false;
    	return true;
    			
    	
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	void onYesClick(Button button, int i){
    	button.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				DCOperator op = ChocAn.getDCOperator();
		    	System.out.println("This worked!_1");
		    	//*ACTIVATE*op.addMember(new Member(usernameField.getText(), fNameField.getText(), lNameField.getText(), stAddrField.getText(), cityField.getText(), stateField.getText(), i, true, false, "No Description."));
			}
		   
		});
    }
    
    @FXML
    void onSubmitClick(ActionEvent event) {
    	
    	if(checkEmptyErrors()){
    		
    		int i = Integer.parseInt(zipCodeField.getText());
    		fullName = lNameField.getText() + ", " + fNameField.getText();
    		
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
    		onYesClick(buttonYes, i);
    		
    		Stage s = new Stage();
    		s.setScene(new Scene(paneArea));
    		s.show();
    	}
    	else error();
    	
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
