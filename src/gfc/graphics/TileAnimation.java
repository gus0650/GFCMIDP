package gfc.graphics;

import gfc.microedition.lcdui.*;

/**
 * @author Gus
 * @version UNTESTED
 */
public class TileAnimation extends TiledImage implements Animation {

	int frame;
	
	public TileAnimation( Image image, int width, int height ) {
		super(image, width, height);
	}

	public void paint(Graphics g) {
		super.paintTile(g, frame, 0, 0);
	}

	public void paint(Graphics g, int x, int y) {
		super.paintTile(g, frame, x, y);
	}

	public void toFirstFrame() {
		frame = 0;
	}

	public void toNextFrame() {
		if ( frame < numTiles() ) frame++;
	}

	public boolean hasMoreFrames() {
		return ( frame < numTiles()-1 );
	}
}
