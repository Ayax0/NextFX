package com.nextlvlup.nextfx.modul;

import com.nextlvlup.nextfx.renderer.FrameRenderer;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SnapModule extends FrameModule{

	public SnapModule(FrameRenderer renderer) {
		super(renderer);
		
		AnchorPane root = new AnchorPane();
		root.setEffect(new DropShadow(10, 0, 0, Color.BLACK));
		
		TextField field = new TextField();
		field.setEditable(false);
		field.setBackground(Background.EMPTY);
		root.getChildren().add(field);
		
		AnchorPane.setTopAnchor((Node) field, 0D);
		AnchorPane.setBottomAnchor((Node) field, 0D);
		AnchorPane.setLeftAnchor((Node) field, 0D);
		AnchorPane.setRightAnchor((Node) field, 0D);
		
		Scene scene = new Scene(root, Color.TRANSPARENT);
		Stage stage = new Stage();
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.initModality(Modality.NONE);
		stage.setX(0);
		stage.setY(0);
		stage.setWidth(200);
		stage.setHeight(100);
		stage.setScene(scene);
		
		stage.show();
	}

}
