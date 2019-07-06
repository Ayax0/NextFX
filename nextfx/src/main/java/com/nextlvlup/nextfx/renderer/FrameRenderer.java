package com.nextlvlup.nextfx.renderer;

import java.io.IOException;

import com.nextlvlup.nextfx.NextFX;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class FrameRenderer extends Renderer{
	
	//Stage
	private Stage stage;
	
	//Scene
	private Scene mainScene;
	
	//FXMLLoader
	private FXMLLoader fxmlLoader;
	
	//Component
	private Node comp_root;
	
	public FrameRenderer(Stage stage, StageStyle style, String fxml) {
		//define
		this.stage = stage;
		
		//Init FXMLLoader
		this.fxmlLoader = new FXMLLoader(NextFX.getResource(fxml));
		this.fxmlLoader.setController(this);
		
		//Init Component
		try { this.comp_root = fxmlLoader.load(); } catch (IOException e) {e.printStackTrace();}
		
		//Create New Scene
		this.mainScene = new Scene((Parent) comp_root);
		
		//Configuration
		
		this.stage.initStyle(style);
		this.stage.setScene(mainScene);
		this.stage.show();
	}
	
	public void setTitel(String titel) {
		this.stage.setTitle(titel);
	}
	
	public void setIcon(String icon) {
		this.stage.getIcons().add(new Image(NextFX.getResourceAsStream(icon)));
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public Node getRoot() {
		return comp_root;
	}
	
	public void drawComponent(ComponentRenderer component) {
		drawComponent(component.getRoot());
		component.setParent(this);
	}
	
	public void drawComponent(Node component) {
		if(getRoot() instanceof AnchorPane) {((AnchorPane)getRoot()).getChildren().add(component);		return;}
		if(getRoot() instanceof BorderPane) {((BorderPane)getRoot()).getChildren().add(component);		return;}
		if(getRoot() instanceof FlowPane) 	{((FlowPane)getRoot()).getChildren().add(component);		return;}
		if(getRoot() instanceof GridPane) 	{((GridPane)getRoot()).getChildren().add(component);		return;}
		if(getRoot() instanceof HBox) 		{((HBox)getRoot()).getChildren().add(component);			return;}
		if(getRoot() instanceof Pane) 		{((Pane)getRoot()).getChildren().add(component);			return;}
		if(getRoot() instanceof StackPane) 	{((StackPane)getRoot()).getChildren().add(component);		return;}
		if(getRoot() instanceof TilePane) 	{((TilePane)getRoot()).getChildren().add(component);		return;}
		if(getRoot() instanceof VBox) 		{((VBox)getRoot()).getChildren().add(component);			return;}
		System.out.println("No Supported Root Node found ...");
	}
}
