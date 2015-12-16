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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PrintManagerReportController {

    @FXML
    private DatePicker endDatePickerField;

    @FXML
    private DatePicker startDatePickerField;

    @FXML
    private Button buttonDateReset;

    @FXML
    void onReportPrintClick(ActionEvent event) {

    }

    @FXML
    void onEndDateClick(ActionEvent event) {

    }

    @FXML
    void onStartDateClick(ActionEvent event) {
    	buttonDateReset.setVisible(true);
    }
    
    @FXML
    void onDateResetClick(ActionEvent event) {
    	buttonDateReset.setVisible(true);
    }
    
    @FXML
    void onCancelClick(ActionEvent event) {
    	Stage s = new Stage();

		Pane mainpane = null;
		try {
			mainpane = (Pane) FXMLLoader.load(graphicsStart.class.getResource("gui/Reports/ReportOptionsMenu.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		s.setScene(new Scene(mainpane));
		s.setTitle("Report Options Menu");
		s.show();

		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
    }

}
