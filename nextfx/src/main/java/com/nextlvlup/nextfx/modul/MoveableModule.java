package com.nextlvlup.nextfx.modul;

import com.nextlvlup.nextfx.renderer.FrameRenderer;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class MoveableModule extends FrameModule {
	
	//Var
	private double xOffset;
	private double yOffset;

	public MoveableModule(final FrameRenderer renderer, Node pickPoint) {
		super(renderer);
		
		pickPoint.setOnMousePressed(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
                yOffset = event.getSceneY();
			}
			
		});
		
		pickPoint.setOnMouseDragged(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				if(renderer.isMaximized()) {
					renderer.minimize();
				}
				
				renderer.getStage().setX(event.getScreenX() - xOffset);
				renderer.getStage().setY(event.getScreenY() - yOffset);
			}
			
		});
		
		pickPoint.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				if(event.getClickCount() == 2 && renderer.isResizeable()) {
					if(renderer.isMaximized()) {
						renderer.minimize();
					}else {
						renderer.maximize();
					}
				}
			}
			
		});
	}

}
