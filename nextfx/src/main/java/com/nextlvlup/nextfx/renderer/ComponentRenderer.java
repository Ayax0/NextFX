package com.nextlvlup.nextfx.renderer;

import java.io.IOException;

import com.nextlvlup.nextfx.NextFX;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public abstract class ComponentRenderer extends Renderer{
	
	//FXMLLoader
	private FXMLLoader loader;
	
	//Component
	private Node component;
	
	//Parent
	private FrameRenderer parent;
	
	public ComponentRenderer(String fxmlPath) {
		try {
			//init FXMLLoader
			this.loader = new FXMLLoader(NextFX.getResource(fxmlPath));
			this.loader.setController(this);
			
			//Load Components
			this.component = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void setParent(FrameRenderer parent) {
		this.parent = parent;
	}
	
	public FrameRenderer getParent() {
		return parent;
	}
	
	public Node getRoot() {
		return component;
	}
	
	public FXMLLoader getFXMLLoader() {
		return loader;
	}

}
