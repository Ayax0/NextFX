package com.nextlvlup.nextfx.renderer;

public abstract class OverlayRenderer extends ComponentRenderer {

	public OverlayRenderer(String fxmlPath) {
		super(fxmlPath);
		
		getRoot().setMouseTransparent(true);
	}
	
	public void show() {
		getRoot().setVisible(true);
		getRoot().setMouseTransparent(false);
	}
	
	public void hide() {
		getRoot().setVisible(false);
		getRoot().setMouseTransparent(true);
	}

}
