package Graphics.Reports;

import java.io.IOException;

import Graphics.graphicsStart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This is the PrintProviderReportController. It helps to find and print a provider report.
 * @author sfyock
 */
public class PrintProviderReportController {
	
	/**
     * 
     */
	@FXML
    private TextField provIDField;
	
    /**
     * 
     */
    @FXML
    private DatePicker datePickerField;
    
    @FXML
    private Button buttonDateReset;
    
    @FXML
    void onDateSelect(ActionEvent event) {
    	buttonDateReset.setVisible(true);
    }

    @FXML
    void onDateResetClick(ActionEvent event) {
    	datePickerField.setValue(null);
    	buttonDateReset.setVisible(false);
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
    void setErrorLabel(Label l){
    	l.setText("Successfully Printed.");
    }
    
    @FXML
    void setErrorLabel(Label l, String str){
    	l.setText(l.getText() 
    			+ "To print your provider report you need:"
    			+ "\n\t" + str);
    }
    
    @FXML
    boolean checkEmptyErrors(){
    	if(provIDField.getText().isEmpty() && datePickerField.getValue() == null){
    		error("Both a username and date.");
    		return false;
    	}
    	else if(provIDField.getText().isEmpty()){
    		error("A provider ID");
    		return false;
    	}
    	else if(datePickerField.getValue() == null){
    		error("A date");
    		return false;
    	}
    	return true;
    }
    
    /**
     * Finds and Prints the provider report if possible.
     * @param event
     */
    @FXML
    void onReportPrintClick(ActionEvent event) {
    	if(checkEmptyErrors()){
    		
    		Pane paneArea = new Pane();
    		Label label;
    		
    		try {
				paneArea = (Pane) FXMLLoader.load(graphicsStart.class.getResource("gui/Main/ErrorMessageMenu.fxml"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    		
    		label = (Label) paneArea.getChildren().get(1);
    		setErrorLabel(label);
    		
    		Stage s = new Stage();
    		s.setScene(new Scene(paneArea));
    		s.setTitle("Success!");
    		s.show();
    	}
    }
    
    /**
     * Cancels the PrintProviderReportMenu and generates the Report Option's Menu.
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