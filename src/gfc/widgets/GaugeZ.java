package gfc.widgets;

import gfc.microedition.lcdui.*;
import gfc.graphics.*;


/**
 * A Gauge is a graphical representation of a value.<p>
 * 
 * This particular Gauge renders the value in the form of a small circle
 * nested inside a larger circle. The smaller circle represents the current value,
 * and the encompassing circle represents the maximum value of the Gauge.
 * 
 * @author Gustaf Stechmann
 */
public class GaugeZ extends Gauge {


	public GaugeZ(int width, int height, int x, int y, Displayable d, int min_value, int max_value, Color bg, Color fg) {
		super(width, height, x, y, d, min_value, max_value, bg, fg);
	}

	
	public void draw(Graphics g) {
		g.setColor(color_bg.getValue());
		g.fillArc(0, 0, getWidth(), getHeight(), 0, 360);

		int inner_width		= (getWidth()  * value) / max_value;
		int inner_height	= (getHeight() * value) / max_value;
		
		int posx = getWidth()/2 - inner_width/2;
		int posy = getHeight()/2 - inner_height/2;
		
		g.setColor(color_fg.getValue());
		g.fillArc( posx, posy, inner_width, inner_height, 0, 360);
	}
}
