package Graphics.Services;

import java.io.IOException;

import Graphics.graphicsStart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ServiceOptionsMenuController {
	
	/**
	 * The action event when you click the New Service Provider button.
	 * 
	 * @param event
	 */
	@FXML
	void onNewServClick(ActionEvent event) {
		Stage s = new Stage();

		Pane mainpane = null;
		try {
			mainpane = (Pane) FXMLLoader.load(graphicsStart.class.getResource("gui/Services/NewServiceMenu.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		s.setScene(new Scene(mainpane));
		s.setTitle("New Service");
		s.show();

		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * The action event when you click the Delete Service Provider button.
	 * 
	 * @param event
	 */
	@FXML
	void onDelServClick(ActionEvent event) {
		Stage s = new Stage();

		Pane mainpane = null;
		try {
			mainpane = (Pane) FXMLLoader.load(graphicsStart.class.getResource("gui/Services/DeleteServiceMenu.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		s.setScene(new Scene(mainpane));
		s.setTitle("Delete Service");
		s.show();

		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * The action event when you click the Update Service Provider button.
	 * 
	 * @param event
	 */
	@FXML
	void onUpdServClick(ActionEvent event) {
		Stage s = new Stage();

		Pane mainpane = null;
		try {
			mainpane = (Pane) FXMLLoader.load(graphicsStart.class.getResource("gui/Services/UpdateServiceMenu.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		s.setScene(new Scene(mainpane));
		s.setTitle("Update Service");
		s.show();

		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * The action event when you click the Back To Menu button.
	 * 
	 * @param event
	 */
	@FXML
	void onBTMenuClick(ActionEvent event) {
		Stage s = new Stage();

		Pane mainpane = null;
		try {
			mainpane = (Pane) FXMLLoader.load(graphicsStart.class.getResource("gui/Main/MainMenu.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		s.setScene(new Scene(mainpane));
		s.setTitle("Main Menu");
		s.show();

		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

}
