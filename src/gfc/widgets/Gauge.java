package gfc.widgets;

import gfc.microedition.lcdui.*;
import gfc.graphics.*;



/**
 * Abstract class that various Gauge implementations are derived from.
 * 
 * @author Gustaf Stechmann
 */
abstract class Gauge extends Widget {

	protected int value;
	protected int min_value, max_value;
	protected Color color_bg, color_fg;
	

	Gauge(int width, int height, int x, int y, Displayable d, int min_value, int max_value, Color bg, Color fg) {
		super(width, height, x, y, d);

		this.min_value = min_value;
		this.max_value = max_value;

		this.color_bg = bg;
		this.color_fg = fg;

		if (min_value > max_value) 	System.err.println("ERROR in Gauge(): min_value > max_value");
		if (max_value < min_value) 	System.err.println("ERROR in Gauge(): max_value < min_value");
		if (max_value < 0) 			System.err.println("ERROR in Gauge(): max_value < 0");
		if (min_value < 0) 			System.err.println("ERROR in Gauge(): min_value < 0");
	}
	
	
	public void setValue(int v) {
		/*config:debug:OFF*///System.out.println("Gauge.setValue() " +v);
		
		value = v;
		
		repaint();
	}
}
