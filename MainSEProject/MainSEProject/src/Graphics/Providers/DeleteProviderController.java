package Graphics.Providers;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParsePosition;

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
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This is the delete provider controller for DeleteProviderMenu.fxml.
 * 
 * @author sfyock
 */
public class DeleteProviderController {

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

	/**
	 * This is the text field for the provider ID.
	 */
	@FXML
	private TextField provIDField;

	/**
	 * The initialization of this controller. Sets up formats for text fields.
	 */
	@FXML
	void initialize() {
		provIDField.setTextFormatter(new TextFormatter<>(c -> {
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
	 * The action event when the user clicks the delete button.
	 * 
	 * @param event
	 */
	@FXML
	void onDeleteClick(ActionEvent event) {
		if (!provIDField.getText().isEmpty()) {
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

	/**
	 * The action event when the user clicks the cancel button.
	 * 
	 * @param event
	 */
	@FXML
	void onCancelClick(ActionEvent event) {
		Pane paneArea = new Pane();

		try {
			paneArea = (Pane) FXMLLoader
					.load(graphicsStart.class.getResource("gui/Providers/ProviderOptionsMenu.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Stage s = new Stage();
		s.setScene(new Scene(paneArea));
		s.setTitle("Provider Options Menu");
		s.show();

		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	/**
	 * The action when the user clicks the yes button in the AreYouSureMenu.
	 * 
	 * @param button
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	void onYesClick(Button button) {
		button.setOnAction(new EventHandler() {

			@Override
			public void handle(Event event) {
				DCOperator op = ChocAn.getOperator();
				String doesNotExist;
				System.out.println("Clicked Yes to delete Provider.");
				doesNotExist = op.delProvider(provIDField.getText());

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
				label.setText(doesNotExist);
			}

		});
	}

	/**
	 * Sets up label of the AreYouSureMenu.
	 * 
	 * @param l
	 */
	@FXML
	void setLabel(Label l) {
		l.setText(l.getText() + "\nProvider ID:\t" + provIDField.getText());
	}

	/**
	 * This sets up the error message label in the error message menu.
	 * 
	 * @param l
	 */
	@FXML
	void setErrorLabel(Label l) {
		l.setText(l.getText() + "You are missing the ID of the provider you want to delete.");
	}

	/**
	 * Sets up an error message using the ErrorMessageMenu.
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
