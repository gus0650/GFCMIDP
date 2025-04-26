package gfc.widgets;

import gfc.microedition.lcdui.*;
import gfc.graphics.*;


/**
 * A Gauge is a graphical representation of a value.<p>
 * 
 * This particular Gauge renders the value in the form of a small bar
 * nested inside a larger bar.
 * 
 * @author Gustaf Stechmann
 */
public class GaugeYBar extends Gauge {

	private int bar;

	
	public GaugeYBar(int width, int height, int x, int y, Displayable d, int min_value, int max_value, Color bg, Color fg) {
		super(width, height, x, y, d, min_value, max_value, bg, fg);

		bar 	= getHeight() / max_value +1;
	}

	
	public void draw(Graphics g) {
		g.setColor(color_bg.getValue());
		g.fillRect(0, 0, getWidth(), getHeight());

		int posy 	= (getHeight() * value) / max_value;

		g.setColor(color_fg.getValue());
		g.fillRect( 0, posy -bar, getWidth(), getHeight() );
	}
}
