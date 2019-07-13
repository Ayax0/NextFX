package com.nextlvlup.nextfx.modul.frame;

import java.io.IOException;
import com.nextlvlup.nextfx.NextFX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SnapFrame extends StackPane{
		
	@FXML private StackPane stackPane;
	
	private Stage window = new Stage();
	
	public enum ConsoleTab {
		CONSOLE, SPEECH_RECOGNITION;
	}
	
	public SnapFrame() {
		FXMLLoader loader = new FXMLLoader(NextFX.class.getResource("modul/ui/SnapFrame.fxml"));
		loader.setController(this);
		
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Window
		window.setTitle("Transparent Window");
		window.initStyle(StageStyle.TRANSPARENT);
		window.initModality(Modality.NONE);
		window.setScene(new Scene(this, Color.TRANSPARENT));
	}
	
	/**
	 * Called as soon as .fxml is initialized
	 */
	@FXML
	private void initialize() {
		BorderPane pane = new BorderPane();
		pane.setPrefWidth(900);
		pane.setPrefHeight(600);
		
		AnchorPane apane = new AnchorPane();
		apane.setStyle("-fx-background-color: #f00;");
		//apane.setStyle("-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);");
		pane.setTop(apane);
		
		WebView view = new WebView();
		apane.getChildren().add(view);
		AnchorPane.setTopAnchor(view, 0D);
		AnchorPane.setRightAnchor(view, 0D);
		AnchorPane.setBottomAnchor(view, 0D);
		AnchorPane.setLeftAnchor(view, 0D);
		
		stackPane.getChildren().add(pane);
	}
	
	/**
	 * @return the window
	 */
	public Stage getWindow() {
		return window;
	}
	
	/**
	 * Close the Window
	 */
	public void close() {
		window.close();
	}
	
	/**
	 * Show the Window
	 */
	public void show() {
		if (!window.isShowing())
			window.show();
		else
			window.requestFocus();
	}

}
