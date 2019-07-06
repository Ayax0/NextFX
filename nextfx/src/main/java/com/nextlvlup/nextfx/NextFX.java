package com.nextlvlup.nextfx;

import java.io.InputStream;
import java.net.URL;

import javafx.application.Application;

public class NextFX {
	
	private static Application instance = null;
	
	public static void initialize(Application instance) {
		NextFX.instance = instance;
	}
	
	public static URL getResource(String path) {
		if(instance == null) System.out.println("NextFX ist not initialized!");
		
		return instance.getClass().getResource(path);
	}
	
	public static InputStream getResourceAsStream(String path) {
		if(instance == null) System.out.println("NextFX ist not initialized!");
		
		return instance.getClass().getResourceAsStream(path);
	}
	
}
