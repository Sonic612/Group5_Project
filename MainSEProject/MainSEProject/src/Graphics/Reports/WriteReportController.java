package Graphics.Reports;

import java.io.IOException;
import Graphics.graphicsStart;
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
 * This is the WriteReportController. It helps to create a Report and save it to
 * the database.
 * 
 * @author sfyock
 */
public class WriteReportController {

	/**
	 * This is the list for the Member/Provider Combo Box.
	 */
	@FXML
	ObservableList<String> reportTypeList = FXCollections.observableArrayList("Member", "Provider");

	/**
	 * This is the Combo Box for the active/suspended options.
	 */
	@FXML
	private ComboBox<String> typeChoiceBox;

	/**
	 * This is the yes button in the AreYouSureMenu.
	 */
	@FXML
	private Button buttonYes;

	/**
	 * This is the title of the new report.
	 */
	@FXML
	private TextField reportTitleField;

	/**
	 * This is the report itself.
	 */
	@FXML
	private TextArea reportArea;

	/**
	 * This is the label for member/provider ID.
	 */
	@FXML
	private Label memProvLabel;

	/**
	 * This is the provider/member ID field
	 */
	@FXML
	private TextField provMemIDField;

	/**
	 * This is the start date for the report
	 */
	@FXML
	private DatePicker startDateSelectField;

	/**
	 * This is the end date for the report
	 */
	@FXML
	private DatePicker endDateSelectField;

	/**
	 * to initialize the typeChoiceBox
	 */
	@FXML
	void initialize() {
		typeChoiceBox.setValue("Select Type");
		typeChoiceBox.setItems(reportTypeList);
	}

	/**
	 * Submits the currently written report to the database, if possible.
	 * 
	 * @param event
	 */
	@FXML
	void onSubmitReportClick(ActionEvent event) {

		if (checkEmptyErrors()) {

			Pane paneArea = new Pane();
			Label label;

			try {
				paneArea = (Pane) FXMLLoader.load(graphicsStart.class.getResource("gui/Messages/AreYouSureMenu.fxml"));
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

	}

	/**
	 * Cancels the WriteReportMenu and generates the Report Option's Menu.
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

	/**
	 * Action for when the report type is changed.
	 * 
	 * @param event
	 */
	@FXML
	void onReportTypeChanged(ActionEvent event) {
		if (typeChoiceBox.getValue().equals("Member")) {
			memProvLabel.setVisible(true);
			memProvLabel.setText("Member Username: ");
			provMemIDField.setVisible(true);
			provMemIDField.setPromptText("Enter Username");
			provMemIDField.setText(null);
		} else if (typeChoiceBox.getValue().equals("Provider")) {
			memProvLabel.setVisible(true);
			memProvLabel.setText("Provider ID: ");
			provMemIDField.setVisible(true);
			provMemIDField.setPromptText("Enter ID");
			provMemIDField.setText(null);
		}
	}

	/**
	 * Checks for empty errors in the code.
	 * 
	 * @return
	 */
	@FXML
	boolean checkEmptyErrors() {
		String fullStringError = "\nA title" + "\nThe actual report" + "\nSelect type of Report" + "\nA start date"
				+ "\nA end date";
		String errorString = "";

		if (reportTitleField.getText().isEmpty()) {
			errorString = errorString + "\nA title";
		}
		if (reportArea.getText().isEmpty()) {
			errorString = errorString + "\nThe actual report";
		}
		if (typeChoiceBox.getValue().equals("Select Type")) {
			errorString = errorString + "\nSelect type of Report";
		}else if (provMemIDField.getText() == null) {
			if (typeChoiceBox.getValue().equals("Provider")) {
				errorString = errorString + "\nA Provider ID";
			} else
				errorString = errorString + "\nA Member ID";
		}
		if (startDateSelectField.getValue() == null) {
			errorString = errorString + "\nA start date";
		}
		if (endDateSelectField.getValue() == null) {
			errorString = errorString + "\nAn end date";
		}

		if (errorString.equals(fullStringError)) {
			error("\nEverything!");
			return false;
		}
		if (!errorString.isEmpty()) {
			error(errorString);
			return false;
		}
		return true;

	}

	/**
	 * @param button
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	void onYesClick(Button button) {
		button.setOnAction(new EventHandler() {

			@Override
			public void handle(Event arg0) {
				// DCOperator op = ChocAn.getOperator();
				System.out.println("This worked!_1");
				// *ACTIVATE*op.addReport(new Report(reportTitleField.getText(),
				// userIDField.getText(), dateSelectField.getValue().toString(),
				// reportArea.getText());
			}

		});
	}

	/**
	 * @param l
	 */
	@FXML
	void setLabel(Label l) {
		l.setText(l.getText() + "\nNew report will be saved as:" + "\nName:\t\t" + reportTitleField.getText()
				+ "\nDate:\t\t" + startDateSelectField.getValue().toString() + "\nUnder User:\t"
				+ provMemIDField.getText());
	}

	/**
	 * @param l
	 * @param str
	 */
	@FXML
	void setErrorLabel(Label l, String str) {
		l.setText(l.getText() + "You are missing " + str + " in your new report.");
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
}
