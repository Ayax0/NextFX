package com.nextlvlup.nextfx.renderer;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;

public abstract class Renderer {
	
	private static List<String> styleSheets = new ArrayList<String>();
	
	public abstract Node getRoot();
	
	public static void addStaticStylesheet(String path) {
		styleSheets.add(path);
	}
	
	public static List<String> getStaticStylesheets(){
		return styleSheets;
	}

}
