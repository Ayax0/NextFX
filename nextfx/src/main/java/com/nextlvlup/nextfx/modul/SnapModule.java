package com.nextlvlup.nextfx.modul;

import com.nextlvlup.nextfx.modul.frame.SnapFrame;
import com.nextlvlup.nextfx.renderer.FrameRenderer;

public class SnapModule extends FrameModule{

	public SnapModule(FrameRenderer renderer) {
		super(renderer);
		
		SnapFrame frame = new SnapFrame();
		frame.getWindow().initOwner(renderer.getStage().getOwner());
		
		frame.getChildren().add(renderer.getRoot());
	}

}
