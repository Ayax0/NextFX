package com.nextlvlup.nextfx.renderer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.nextlvlup.nextfx.NextFX;
import com.nextlvlup.nextfx.listener.WindowListener;
import com.nextlvlup.nextfx.states.WindowState;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
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
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;

public abstract class FrameRenderer extends Renderer{
	
	//Stage
	@Getter private Stage stage;
	
	//Scene
	@Getter private Scene scene;
	
	//FXMLLoader
	private FXMLLoader fxmlLoader;
	
	//Component
	@Getter private StackPane stackPane;
	@Getter private Node root;
	
	//Listener
	private List<WindowListener> listener_window = new ArrayList<WindowListener>();
	
	//Var
	@Getter @Setter private boolean resizeable = true;
	
	private double x;
	private double y;
	private double width;
	private double height;
	
	//Constructor
	public FrameRenderer(Stage stage, StageStyle style, String fxml) {
		//define
		this.stage = stage;
		
		//Init FXMLLoader
		this.fxmlLoader = new FXMLLoader(NextFX.getResource(fxml));
		this.fxmlLoader.setController(this);
		
		//Init Component
		try { this.root = fxmlLoader.load(); } catch (IOException e) {e.printStackTrace();}
		
		//Stacked Root
		this.stackPane = new StackPane(getRoot());
		this.stackPane.setAlignment(Pos.TOP_LEFT);
		this.stackPane.setStyle("-fx-background-color: #fff;");
		
		//Create New Scene
		this.scene = new Scene((Parent) getStackPane());
		
		//Init Style
		for(String stylesheet : getStaticStylesheets()) 
			getScene().getStylesheets().add(NextFX.getResource(stylesheet).toExternalForm());
		
		//Configuration
		
		this.stage.initStyle(style);
		this.stage.setScene(getScene());
		this.stage.show();
	}
	
	//FXMLless Constructor
	public FrameRenderer(Stage stage, StageStyle style, Parent parent) {
		//define
		this.stage = stage;
		
		//Init Component
		this.root = parent;
		
		//Stacked Root
		this.stackPane = new StackPane(getRoot());
		this.stackPane.setAlignment(Pos.TOP_LEFT);
		this.stackPane.setStyle("-fx-background-color: #fff;");
		
		//Create New Scene
		this.scene = new Scene((Parent) getStackPane());
		
		//Init Style
		for(String stylesheet : getStaticStylesheets()) 
			getScene().getStylesheets().add(NextFX.getResource(stylesheet).toExternalForm());
		
		//Configuration
		this.stage.initStyle(style);
		this.stage.setScene(getScene());
		this.stage.show();
	}
	
	//Window Controll
	public void setTitel(String titel) {
		this.stage.setTitle(titel);
	}
	
	public void setIcon(String icon) {
		this.stage.getIcons().add(new Image(NextFX.getResourceAsStream(icon)));
	}
	
	public void setBorder(String color) {
		getRoot().setStyle("-fx-border-color: " + color + "; -fx-border-width: 1;");
	}
	
	public void addOverlay(OverlayRenderer overlay) {
		getStackPane().getChildren().add(overlay.getRoot());
	}
	
	public void maximize() {
		if(!isMaximized()) {
			Rectangle2D bounds = getScreenBounds();
			
			x = getStage().getX();
			y = getStage().getY();
			width = getStage().getWidth();
			height = getStage().getHeight();
			
			getStage().setX(bounds.getMinX());
			getStage().setY(bounds.getMinY());
			getStage().setWidth(bounds.getWidth());
			getStage().setHeight(bounds.getHeight());
			
			for(WindowListener listener : listener_window) {
				listener.onAction(this, WindowState.MAXIMIZED);
			}
		}
	}
	
	public void minimize() {
		getStage().setX(x);
		getStage().setY(y);
		getStage().setWidth(width);
		getStage().setHeight(height);
		
		for(WindowListener listener : listener_window) {
			listener.onAction(this, WindowState.DEFULT);
		}
	}
	
	//Getter
	public boolean isMaximized() {
		Rectangle2D bounds = Screen.getScreensForRectangle(getStage().getX(), getStage().getY(), getStage().getWidth(), getStage().getHeight()).get(0).getVisualBounds();
		if(getStage().getWidth() != bounds.getWidth()) return false;
		if(getStage().getHeight() != bounds.getHeight()) return false;
		if(getStage().getX() != bounds.getMinX()) return false;
		if(getStage().getY() != bounds.getMinY()) return false;
		return true;
	}
	
	//Content Management
	public void drawComponent(ComponentRenderer renderer) {
		renderer.setParent(this);
		drawComponent(renderer.getRoot());
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

	//Screen Info
	public Rectangle2D getScreenBounds() {
		return Screen.getScreensForRectangle(getStage().getX(), getStage().getY(), getStage().getWidth(), getStage().getHeight()).get(0).getVisualBounds();
	}
	
	//Listener
	public void setOnWindowResize(WindowListener listener) {
		this.listener_window.add(listener);
	}
}
