package gfc.widgets;

import gfc.microedition.lcdui.*;
import gfc.graphics.*;


/**
 * A Gauge is a graphical representation of a value.<p>
 * 
 * This particular Gauge renders the value in the form of a vertical bar.
 * 
 * @author Gustaf Stechmann
 */
public class GaugeY extends Gauge {

	private int bar;
	
	
	public GaugeY(int width, int height, int x, int y, Displayable d, int min_value, int max_value, Color bg, Color fg) {
		super(width, height, x, y, d, min_value, max_value, bg, fg);

		bar 	= getHeight() / max_value +1;
	}

	
	public void draw(Graphics g) {
		g.setColor(color_bg.getRGB());
		g.fillRect(0, 0, getWidth(), getHeight());

		int posy 	= (getHeight() * value) / max_value;

		g.setColor( color_fg.getRGB() );
		g.fillRect( 0, getHeight() - posy, getWidth(), bar );
	}
}
