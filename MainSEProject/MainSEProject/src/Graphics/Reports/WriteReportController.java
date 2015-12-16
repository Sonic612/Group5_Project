package Graphics.Reports;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;

import Graphics.graphicsStart;
import Main.ChocAn;
import Main.ReportViewer;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This is the Write Report Controller for the WriteReportMenu.fxml.
 * 
 * @author sfyock
 */
/**
 * @author seanp_000
 *
 */
public class WriteReportController {
	
	/**
	 * This is the Decimal Formatter for the initialization process.
	 */
	@FXML
	DecimalFormat format = new DecimalFormat("#");
	
	/**
	 * This is the list for the Member/Provider Combo Box.
	 */
	@FXML
	ObservableList<String> reportTypeList = FXCollections.observableArrayList("Member", "Provider");

	/**
	 * This is the Combo Box for the member/provider options.
	 */
	@FXML
	private ComboBox<String> typeChoiceBox;

	/**
	 * This is the yes button in the AreYouSureMenu.
	 */
	@FXML
	private Button buttonYes;
	
	/**
	 * This is the reset date button.
	 */
	@FXML
	private Button buttonResetDate;

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
	private DatePicker dateSelectField;

	/**
	 * to initialize the typeChoiceBox
	 */
	@FXML
	void initialize() {
		typeChoiceBox.setValue("Select Type");
		typeChoiceBox.setItems(reportTypeList);
		provMemIDField.setTextFormatter(new TextFormatter<>(c -> {
			if (c.getControlNewText().isEmpty()) {
				return c;
			}

			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(c.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
				return null;
			} else if (c.getControlNewText().length() > 4) {
				return null;
			} else {
				return c;
			}
		}));
	}

	/**
	 * The action when the user clicks the submit button.
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
	 * The action when the user clicks the cancel button.
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
			memProvLabel.setText("Member ID: ");
			provMemIDField.setVisible(true);
			provMemIDField.setPromptText("Enter ID");
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
	 * @param event
	 */
	@FXML
	void onDateClick(ActionEvent event) {
		buttonResetDate.setVisible(true);
	}

	/**
	 * @param event
	 */
	@FXML
	void onResetDateClick(ActionEvent event) {
		dateSelectField.setValue(null);
		buttonResetDate.setVisible(false);
	}

	/**
	 * Checks for empty errors in the code.
	 * 
	 * @return
	 */
	@FXML
	boolean checkEmptyErrors() {
		String fullStringError = "\nSelect type of Report" + "\nA Date";
		String errorString = "";

		if (typeChoiceBox.getValue().equals("Select Type")) {
			errorString = errorString + "\nSelect type of Report";
		} else if (provMemIDField.getText() == null) {
			if (typeChoiceBox.getValue().equals("Provider"))
				errorString = errorString + "\nA Provider ID";
			else if (typeChoiceBox.getValue().equals("Member"))
				errorString = errorString + "\nA Member ID";
		}
		if (dateSelectField.getValue() == null) {
			errorString = errorString + "\nA Date";
		}
		
		if((dateSelectField.getValue() != null) && dateSelectField.getValue().isAfter(LocalDate.now())){
			error();
			return false;
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
	 * The action when the user clicks the Write New Report button.
	 * 
	 * @param button
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	void onYesClick(Button button) {
		button.setOnAction(new EventHandler() {

			@Override
			public void handle(Event event) {
				String doesNotExist;
				ReportViewer view = ChocAn.getReports();
				
				int provMemID = Integer.parseInt(provMemIDField.getText());
				
				int type = (typeChoiceBox.getValue().equals("Member")) ? 1 : 0;
				
				view.GenReport(type, provMemID, dateSelectField.getValue().toString());
				doesNotExist = view.viewReport();
				
				if (!doesNotExist.equals("")) {
					Pane messagePane = null;
					try {
						messagePane = (Pane) FXMLLoader
								.load(graphicsStart.class.getResource("gui/Messages/ReportMessageMenu.fxml"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					ScrollPane scrollPane;
					Label label;
					
					scrollPane = (ScrollPane) messagePane.getChildren().get(1);
					label = (Label) scrollPane.getContent();
					setMessageLabel(label, doesNotExist);

					Stage s = new Stage();
					s.setScene(new Scene(messagePane));
					s.setTitle("Report");
					s.show();

					Node source = (Node) event.getSource();
					Stage stage = (Stage) source.getScene().getWindow();
					stage.close();
				}
				else{
					Pane messagePane = null;
					try {
						messagePane = (Pane) FXMLLoader
								.load(graphicsStart.class.getResource("gui/Messages/ErrorMessageMenu.fxml"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					Label label;

					label = (Label) messagePane.getChildren().get(1);
					setMessageLabel(label, doesNotExist);

					Stage s = new Stage();
					s.setScene(new Scene(messagePane));
					s.setTitle("Error!");
					s.show();

					Node source = (Node) event.getSource();
					Stage stage = (Stage) source.getScene().getWindow();
					stage.close();
				}

			}

			/**
			 * This sets up a success/failure message label in an error message
			 * menu.
			 * 
			 * @param label
			 * @param doesNotExist
			 */
			@FXML
			void setMessageLabel(Label label, String doesNotExist) {
				if (doesNotExist.equals("")) {
					label.setText("Error: No Reports Available!");
				} else {
					//System.out.println(doesNotExist);
					label.setText(doesNotExist);
				}
			}
		});
	}

	/**
	 * @param l
	 */
	@FXML
	void setLabel(Label l) {
		l.setText("\nNew report will be saved as:" + "\nType:\t\t" + typeChoiceBox.getValue().toString()
				+ "\nDate:\t\t" + dateSelectField.getValue().toString() + "\nUnder User:\t"
				+ provMemIDField.getText());
	}
	
	@FXML
	void setErrorLabel(Label l) {
		l.setText(l.getText() + "Your selected date must be before or equal to today.");
	}
	
	/**
	 * @param l
	 * @param str
	 */
	@FXML
	void setErrorLabel(Label l, String str) {
		l.setText(l.getText() + "To show the report you must have:" + str);
	}
	
	@FXML
	void error() {
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
		setErrorLabel(label);

		Stage s = new Stage();
		s.setScene(new Scene(paneArea));
		s.setTitle("Error!");
		s.show();
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
