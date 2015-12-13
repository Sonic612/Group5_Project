package Graphics.Members;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParsePosition;

import Graphics.graphicsStart;
import Main.ChocAn;
import Main.DCOperator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UpdateMemberController {
	
	@FXML
	ObservableList<String> activeList = FXCollections.observableArrayList("Acitve", "Non-Active", "Suspended");
	
	@FXML
	DecimalFormat format = new DecimalFormat( "#" );
	
	@FXML
	private Button buttonYes;
	
	@FXML
    private ComboBox<String> activeBox;
	
	@FXML
    private TextField memIDField;
	
	@FXML
    private String fullName;
	
    @FXML
    private TextField lNameField;

    @FXML
    private TextField fNameField;
    
    @FXML
    private TextField cityField;
    
    @FXML
    private TextField stAddrField;
    
    @FXML
    private TextField stateField;

    @FXML
    private TextField zipCodeField;
    
    @FXML
    void initialize(){
    	activeBox.setValue(activeList.get(0));
    	activeBox.setItems(activeList);
    	
    	memIDField.setTextFormatter( new TextFormatter<>(c ->
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
    	    else if(c.getControlNewText().length() > 4)
    	    {
    	        return null;
    	    }
    	    else{
    	    	return c;
    		}
    	}));
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
    			+ "\nMember ID:\t" + memIDField.getText()
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
    			+ "You are missing " + str + " in your updated member.");
    }
    
    boolean checkEmptyErrors(){
    	if(memIDField.getText().isEmpty()
    			|| lNameField.getText().isEmpty() && fNameField.getText().isEmpty()
    			|| stAddrField.getText().isEmpty()
    			|| stateField.getText().isEmpty()
    			|| cityField.getText().isEmpty()
    			|| zipCodeField.getText().isEmpty())
    		return false;
    	return true;
    }
    
    boolean isSuspended(){
    	if(activeBox.getValue() == "Suspended")
    		return true;
		return false;
    }
    
    boolean isActive(){
    	if(activeBox.getValue() == "Active")
    		return true;
    	//Not needed...
    	//else if(activeBox.getValue() == "Non-Active"){
    		//return false;
    	//}
		return false;
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	void onYesClick(Button button, int zipCode, int memID, boolean active, boolean suspend){
    	button.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				DCOperator op = ChocAn.getDCOperator();
		    	System.out.println("This worked!_3");
		    	//*ACTIVATE*op.updateMember(new Member(memID, fNameField.getText(), lNameField.getText(), stAddrField.getText(), cityField.getText(), stateField.getText(), zipCode, active, suspend));
			}
		   
		});
    }
    
	@FXML
    void onUpdateClick(ActionEvent event) {
    	if(checkEmptyErrors()){
    		
    		int zipCode = Integer.parseInt(zipCodeField.getText());
    		int memID = Integer.parseInt(memIDField.getText());
    		
    		boolean suspend = isSuspended();
    		boolean active = isActive();
    		
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
    		onYesClick(buttonYes, zipCode, memID, active, suspend);
    		
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
