package Graphics.Reports;

import java.io.IOException;

import Graphics.graphicsStart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This is the reports menu controller for ReportOptionsMenu.fxml.
 * 
 * @author sfyock
 */
public class ReportMenuController {

	/**
	 * The action when the user clicks the Write New Report button.
	 * 
	 * @param event
	 */
	@FXML
	void onWriteRepClick(ActionEvent event) {
		Pane paneArea = new Pane();

		try {
			paneArea = (Pane) FXMLLoader.load(graphicsStart.class.getResource("gui/Reports/WriteReportMenu.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Stage s = new Stage();
		s.setScene(new Scene(paneArea));
		s.setTitle("Write Report");
		s.show();

		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	/**
	 * The action when the user clicks the Print Member Report button.
	 * 
	 * @param event
	 */
	@FXML
	void onMemRepClick(ActionEvent event) {
		Pane paneArea = new Pane();

		try {
			paneArea = (Pane) FXMLLoader
					.load(graphicsStart.class.getResource("gui/Reports/PrintMemberReportMenu.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Stage s = new Stage();
		s.setScene(new Scene(paneArea));
		s.setTitle("Print Member Report");
		s.show();

		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	/**
	 * The action when the user clicks the Print Provider Report button.
	 * 
	 * @param event
	 */
	@FXML
	void onSerProvRepClick(ActionEvent event) {
		Pane paneArea = new Pane();

		try {
			paneArea = (Pane) FXMLLoader
					.load(graphicsStart.class.getResource("gui/Reports/PrintServiceProviderReportMenu.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Stage s = new Stage();
		s.setScene(new Scene(paneArea));
		s.setTitle("Print Service Provider Report");
		s.show();

		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * The action when the user clicks the Print Manager Report button.
	 * 
	 * @param event
	 */
	@FXML
    void onManagerRepClick(ActionEvent event) {
		Pane paneArea = new Pane();

		try {
			paneArea = (Pane) FXMLLoader
					.load(graphicsStart.class.getResource("gui/Reports/PrintManagerReportMenu.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Stage s = new Stage();
		s.setScene(new Scene(paneArea));
		s.setTitle("Print Manager Report");
		s.show();

		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
    }
	
	/**
	 * The action when the user clicks the Back To Menu button.
	 * 
	 * @param event
	 */
	@FXML
	void onBTMenuClick(ActionEvent event) {
		Pane paneArea = new Pane();

		try {
			paneArea = (Pane) FXMLLoader.load(graphicsStart.class.getResource("gui/Main/MainMenu.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Stage s = new Stage();
		s.setScene(new Scene(paneArea));
		s.setTitle("Main Menu");
		s.show();

		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

}
