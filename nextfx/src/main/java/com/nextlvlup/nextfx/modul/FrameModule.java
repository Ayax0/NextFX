package com.nextlvlup.nextfx.modul;

import com.nextlvlup.nextfx.renderer.FrameRenderer;

public abstract class FrameModule {
	
	private FrameRenderer renderer;
	
	public FrameModule(FrameRenderer renderer) {
		this.renderer = renderer;
	}
	
	public FrameRenderer getRenderer() {
		return renderer;
	}

}
