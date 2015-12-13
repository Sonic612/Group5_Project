package Graphics.Reports;

import java.io.IOException;
import java.util.List;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This is the WriteReportController. It helps to create a Report and save it to the database.
 * @author sfyock
 */
public class WriteReportController {
	
	/**
     * 
     */
	@FXML
	ObservableList<String> reportTypeList = FXCollections.observableArrayList("Member", "Provider");
	
	/**
     * 
     */
	@FXML
	private Button buttonYes;
	
	/**
     * 
     */
    @FXML
    private TextField reportTitleField;
    
	/**
     * 
     */
    @FXML
    private TextArea reportArea;
	
    /**
     * 
     */
    @FXML
    private ComboBox<String> typeChoiceBox;
    
    /**
     * 
     */
    @FXML
    private DatePicker dateSelectField;
    
    /**
     * 
     */
    @FXML
    private Label memProvLabel;
    
    /**
     * 
     */
    @FXML
    private TextField userIDField;
    
    /**
     * to initialize the typeChoiceBox
     */
    @FXML
    void initialize(){
    	typeChoiceBox.setValue("Select Type");
    	typeChoiceBox.setItems(reportTypeList);
    }
    
    /**
     * Action for when the report type is changed.
     * @param event
     */
    @FXML
    void onReportTypeChanged(ActionEvent event) {
    	if(typeChoiceBox.getValue().equals("Member")){
    		memProvLabel.setVisible(true);
    		memProvLabel.setText("Member Username: ");
    		userIDField.setVisible(true);
    		userIDField.setPromptText("Enter Username");
    		userIDField.setText(null);
    	}
    	else if(typeChoiceBox.getValue().equals("Provider")){
    		memProvLabel.setVisible(true);
    		memProvLabel.setText("Provider ID: ");
    		userIDField.setVisible(true);
    		userIDField.setPromptText("Enter ID");
    		userIDField.setText(null);
    	}
    }
    
    @FXML
    void setLabel(Label l){
    	l.setText(l.getText() 
    			+ "\nNew report will be saved as:"
    			+ "\nName:\t\t" + reportTitleField.getText()
    			+ "\nDate:\t\t" + dateSelectField.getValue().toString()
    			+ "\nUnder User:\t" + userIDField.getText());
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
    			+ "You are missing " + str + " in your new report.");
    }
    
    @FXML
    boolean checkEmptyErrors(){
    	if(reportTitleField.getText().isEmpty()
    			|| reportArea.getText().isEmpty()
    			|| typeChoiceBox.getValue().equals("Select Type")
    			|| dateSelectField.getValue().toString().isEmpty()
    			|| userIDField.getText().isEmpty())
    		return false;
    	return true;
    			
    	
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	void onYesClick(Button button){
    	button.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				DCOperator op = ChocAn.getDCOperator();
		    	System.out.println("This worked!_1");
		    	//*ACTIVATE*op.addReport(new Report(reportTitleField.getText(), userIDField.getText(), dateSelectField.getValue().toString(), reportArea.getText());
			}
		   
		});
    }

    
    /**
     * Submits the currently written report to the database, if possible.
     * @param event
     */
    @FXML
    void onSubmitReportClick(ActionEvent event) {

    	if(checkEmptyErrors()){
    		
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
    	else error();
    	
    }
    
    
    /**
     * Cancels the WriteReportMenu and generates the Report Option's Menu.
     * @param event
     */
    @FXML
    void onCancelClick(ActionEvent event) {
    	Pane paneArea = new Pane();
    	
    	try {
			paneArea = (Pane) FXMLLoader.load(graphicsStart.class.getResource("gui/Reports/ReportOptionsMenu.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	Stage s = new Stage();
    	s.setScene(new Scene(paneArea));
    	s.setTitle("Report Options");
    	s.show();
    	
    	Node  source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
