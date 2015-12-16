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
 * This is the PrintMemberReportController. It helps to find and print a member
 * report.
 * 
 * @author sfyock
 */
public class PrintMemberReportController {

	@FXML
	private TextField memIDField;

	@FXML
	private DatePicker startDatePickerField;

	@FXML
	private DatePicker endDatePickerField;

	@FXML
	private Button buttonDateReset;

	@FXML
	void onDateSelect(ActionEvent event) {
		buttonDateReset.setVisible(true);
	}

	@FXML
	void onDateResetClick(ActionEvent event) {
		startDatePickerField.setValue(null);
		endDatePickerField.setValue(null);
		buttonDateReset.setVisible(false);
	}

	@FXML
	void error(String str) {
		// Error Menu setup
		GridPane paneArea = new GridPane();
		Label label;

		try {
			paneArea = (GridPane) FXMLLoader
					.load(graphicsStart.class.getResource("gui/Messages/ErrorMessageMenu.fxml"));
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
	void setErrorLabel(Label l) {
		l.setText("Successfully Printed.");
	}

	@FXML
	void setErrorLabel(Label l, String str) {
		l.setText(l.getText() + "To print your member report you need:" + "\n\t" + str);
	}

	@FXML
	boolean checkEmptyErrors() {
		String memError = null;
		String startError = null;
		String endError = null;

		if (memIDField.getText().isEmpty() && startDatePickerField.getValue() == null
				&& endDatePickerField.getValue() == null) {
			error("A member ID, a start date, and an end date.");
			return false;
		} else if (memIDField.getText().isEmpty()) {
			memError = "a member ID";
		} else if (startDatePickerField.getValue() == null) {
			startError = "a start date";
		} else if (endDatePickerField.getValue() == null) {
			endError = "an end date";
		}

		if (memError != null) {
			String error = startError + ", and " + endError;
			error(error);
			return false;
		} else if (startError != null) {
			String error = memError + ", and " + endError;
			error(error);
			return false;
		} else if (endError != null) {
			String error = memError + ", and " + startError;
			error(error);
			return false;
		}
		return true;
	}

	/**
	 * Finds and Prints the provider report if possible.
	 * 
	 * @param event
	 */
	@FXML
	void onReportPrintClick(ActionEvent event) {
		if (checkEmptyErrors()) {

			buttonDateReset.setVisible(true);
			Pane paneArea = new Pane();
			Label label;

			try {
				paneArea = (Pane) FXMLLoader
						.load(graphicsStart.class.getResource("gui/Messages/ErrorMessageMenu.fxml"));
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
	 * Cancels the PrintMemberReportMenu and generates the Report Option's Menu.
	 * 
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
		s.setTitle("Report Options Menu");
		s.show();

		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	void onEndDateClick(ActionEvent event) {
		buttonDateReset.setVisible(true);
	}

	@FXML
	void onStartDateClick(ActionEvent event) {
		buttonDateReset.setVisible(true);
	}

}
