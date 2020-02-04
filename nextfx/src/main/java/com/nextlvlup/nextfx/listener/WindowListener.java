package com.nextlvlup.nextfx.listener;

import com.nextlvlup.nextfx.renderer.FrameRenderer;
import com.nextlvlup.nextfx.states.WindowState;

public abstract class WindowListener {
	
	public abstract void onAction(FrameRenderer renderer, WindowState state);

}
