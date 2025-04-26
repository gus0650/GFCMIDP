package gfc.graphics;

import gfc.microedition.lcdui.*;


public interface Animation {
	
	public void paint(Graphics g);

	void toFirstFrame();

	void toNextFrame();

	public boolean hasMoreFrames();
}
