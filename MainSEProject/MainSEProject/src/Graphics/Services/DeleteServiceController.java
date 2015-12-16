package Graphics.Services;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParsePosition;

import Graphics.graphicsStart;
import Main.ChocAn;
import Main.ServiceRecordProcess;
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

public class DeleteServiceController {

	/**
	 * This is the Decimal Formatter for the initialization process.
	 */
	@FXML
	DecimalFormat format = new DecimalFormat("#");

	/**
	 * This is the yes button in the AreYouSureMenu.
	 */
	@FXML
	private Button buttonYes;

	@FXML
	private TextField servCodeField;

	/**
	 * The initialization of this controller. Sets up formats for text fields.
	 */
	@FXML
	void initialize() {
		servCodeField.setTextFormatter(new TextFormatter<>(c -> {
			if (c.getControlNewText().isEmpty()) {
				return c;
			}

			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(c.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
				return null;
			} else if (c.getControlNewText().length() > 3) {
				return null;
			} else {
				return c;
			}
		}));
	}

	@FXML
	void onDeleteClick(ActionEvent event) {
		if (!servCodeField.getText().isEmpty()) {

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
		} else
			error();
	}

	@FXML
	void onCancelClick(ActionEvent event) {
		Stage s = new Stage();

		Pane mainpane = null;
		try {
			mainpane = (Pane) FXMLLoader.load(graphicsStart.class.getResource("gui/Services/ServiceOptionsMenu.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		s.setScene(new Scene(mainpane));
		s.setTitle("Service Menu");
		s.show();

		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	/**
	 * The action when the user clicks the yes button in the AreYouSureMenu.
	 * 
	 * @param button
	 * @param zipCode
	 * @param memID
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	void onYesClick(Button button) {
		button.setOnAction(new EventHandler() {

			@Override
			public void handle(Event event) {
				ServiceRecordProcess servRecord = ChocAn.getServices();
				System.out.println("Clicked Yes for New Member.");
				String doesNotExist;

				int servCode = Integer.parseInt(servCodeField.getText());

				doesNotExist = servRecord.deleteRecord(servCode);

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
				s.show();

				Node source = (Node) event.getSource();
				Stage stage = (Stage) source.getScene().getWindow();
				stage.setTitle("Message");
				stage.close();

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
				System.out.println(doesNotExist);
				label.setText(doesNotExist);
			}
		});

	}

	/**
	 * This sets up the label in the AreYouSureMenu.
	 * 
	 * @param l
	 */
	@FXML
	void setLabel(Label l) {
		l.setText(l.getText() + "\nService Code:\t" + servCodeField.getText());
	}

	/**
	 * This sets up the error label in the error message.
	 * 
	 * @param l
	 */
	@FXML
	void setErrorLabel(Label l) {
		l.setText(l.getText() + "You are missing the service code of the service you want to delete.");
	}

	/**
	 * This sets up the error message menu.
	 */
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
		s.setTitle("Error...");
		s.show();
	}

}
