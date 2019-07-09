package com.nextlvlup.nextfx.modul;

import com.nextlvlup.nextfx.renderer.FrameRenderer;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

public class ResizeModule extends FrameModule {
	
	private Cursor resizeable = null;
	
	private boolean enabled = true;
	
	private boolean resizeCorner = true;
	
	private double minWidth = 80;
	private double minHeight = 50;

	public ResizeModule(FrameRenderer renderer) {
		super(renderer);
		
		getRenderer().getScene().addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {

			public void handle(MouseEvent e) {
				if(enabled) {
					double threshold = 5;
					
					int x = 		(int) (e.getSceneX() / threshold);
					int y = 		(int) (e.getSceneY() / threshold);
					
					int width = 	(int) (getRenderer().getScene().getWidth() / threshold) - 1;
					int height = 	(int) (getRenderer().getScene().getHeight() / threshold) - 1;
					
					//Top
					if(y == 0) {
						if(resizeCorner){
							//Top-Left
							if(x == 0) {
								setResizeable(Cursor.NW_RESIZE);
								return;
							}
							//Tep-Right
							if(x== width) {
								setResizeable(Cursor.NE_RESIZE);
								return;
							}
						}
						setResizeable(Cursor.N_RESIZE);
						return;
					}
					
					//Bottom
					if(y == height) {
						if(resizeCorner) {
							//Bottom-Left
							if(x == 0) {
								setResizeable(Cursor.SW_RESIZE);
								return;
							}
							//Bottom-Right
							if(x == width) {
								setResizeable(Cursor.SE_RESIZE);
								return;
							}
						}
						setResizeable(Cursor.S_RESIZE);
						return;
					}
					
					//Left
					if(x == 0) {
						setResizeable(Cursor.W_RESIZE);
						return;
					}
					
					//Right
					if(x == width) {
						setResizeable(Cursor.E_RESIZE);
						return;
					}
					
					setResizeable(Cursor.DEFAULT);
				}
			}
		});

		getRenderer().getScene().addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {

			public void handle(MouseEvent e) {
				if(enabled) {
					double x = getRenderer().getStage().getX();
					double y = getRenderer().getStage().getY();
					
					double width = getRenderer().getStage().getWidth();
					double height = getRenderer().getStage().getHeight();
					
					//Left
					if(resizeable == Cursor.W_RESIZE) {
						double resizeX = getRenderer().getStage().getWidth() - (e.getScreenX() - x);
						
						if(resizeX >= minWidth) {
							getRenderer().getStage().setX(e.getScreenX());
							getRenderer().getStage().setWidth(resizeX);
						}
						return;
					}
					
					//Right
					if(resizeable == Cursor.E_RESIZE) {
						double resizeX = getRenderer().getStage().getWidth() - ((x + width)- e.getScreenX());
						
						if(resizeX >= minWidth) getRenderer().getStage().setWidth(resizeX);
						return;
					}
					
					//Top
					if(resizeable == Cursor.N_RESIZE) {
						double resizeY = getRenderer().getStage().getHeight() - (e.getScreenY() - y);
						
						if(resizeY >= minHeight) {
							getRenderer().getStage().setY(e.getScreenY());
							getRenderer().getStage().setHeight(resizeY);
						}
						return;
					}
					
					//Bottom
					if(resizeable == Cursor.S_RESIZE) {
						double resizeY = getRenderer().getStage().getHeight() - ((y + height)- e.getScreenY());
						
						if(resizeY >= minHeight) getRenderer().getStage().setHeight(resizeY);
						return;
					}
					
					if(resizeCorner) {
						//Top-Left
						if(resizeable == Cursor.NW_RESIZE) {
							double resizeX = getRenderer().getStage().getWidth() - (e.getScreenX() - x);
							double resizeY = getRenderer().getStage().getHeight() - (e.getScreenY() - y);
							
							if(resizeX >= minWidth) {
								getRenderer().getStage().setX(e.getScreenX());
								getRenderer().getStage().setWidth(resizeX);
							}
							
							if(resizeY >= minHeight) {
								getRenderer().getStage().setY(e.getScreenY());
								getRenderer().getStage().setHeight(resizeY);
							}
							return;
						}
						
						//Top_Right
						if(resizeable == Cursor.NE_RESIZE) {
							double resizeX = getRenderer().getStage().getWidth() - ((x + width)- e.getScreenX());
							double resizeY = getRenderer().getStage().getHeight() - (e.getScreenY() - y);
							
							if(resizeX >= minWidth) getRenderer().getStage().setWidth(resizeX);
							
							if(resizeY >= minHeight) {
								getRenderer().getStage().setY(e.getScreenY());
								getRenderer().getStage().setHeight(resizeY);
							}
							return;
						}
						
						//Bottom-Left
						if(resizeable == Cursor.SW_RESIZE) {
							double resizeX = getRenderer().getStage().getWidth() - (e.getScreenX() - x);
							double resizeY = getRenderer().getStage().getHeight() - ((y + height)- e.getScreenY());
							
							if(resizeX >= minWidth) {
								getRenderer().getStage().setX(e.getScreenX());
								getRenderer().getStage().setWidth(resizeX);
							}
							
							if(resizeY >= minHeight) getRenderer().getStage().setHeight(resizeY);
							return;
						}
						
						//Bottom-Right
						if(resizeable == Cursor.SE_RESIZE) {
							double resizeX = getRenderer().getStage().getWidth() - ((x + width)- e.getScreenX());
							double resizeY = getRenderer().getStage().getHeight() - ((y + height)- e.getScreenY());
							
							if(resizeX >= minWidth) getRenderer().getStage().setWidth(resizeX);
							
							if(resizeY >= minHeight) getRenderer().getStage().setHeight(resizeY);
							return;
						}
					}
				}
			}
		});
	}
	
	public void setMinWidth(double width) {
		this.minWidth = width;
	}
	
	public void setMinHeight(double height) {
		this.minHeight = height;
	}
	
	public void disable() {
		enabled = false;
	}
	
	public void enable() {
		enabled = true;
	}
	
	public void setCornerResizeable(boolean state) {
		resizeCorner = state;
	}
	
	private void setResizeable(Cursor cursor) {
		this.resizeable =  cursor == Cursor.DEFAULT ? null : cursor;
		getRenderer().getScene().setCursor(cursor);
	}

}
