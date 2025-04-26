package gfc.graphics.particles;

import gfc.graphics.*;
import gfc.microedition.lcdui.*;


public class Sphere implements Animation {

	private int				expansion;
	private int				radius;
	private boolean			fill;

	private int				x;
	private int				y;
	private int				colour;

	private int				ticks_to_live;

	
	public Sphere( int x, int y, int colour, int ticks_to_live, int radius, int exp, boolean fill ) {
		this.x = x;
		this.y = y;

		this.expansion 		= exp;
		this.colour 		= colour;
		this.radius 		= radius;
		this.ticks_to_live 	= ticks_to_live;
		this.fill 			= fill;
	}


	public void toFirstFrame() {
		/*config:debug:OFF*///System.err.println("WARNING Sphere.toFirstFrame(): Method not implemented");
		
		//TODO: remember setting & restore now
	}
	
	
	public void toNextFrame() {
		ticks_to_live--;
		
		radius += expansion * 2;
		x -= expansion;
		y -= expansion;
	}


	public boolean hasMoreFrames() {
		return (ticks_to_live > 0);
	}
	

	public void paint( Graphics g ) {
		g.setColor(colour);

		if (fill)
			g.fillArc(x, y, radius, radius, 0, 360);
		else
			g.drawArc(x, y, radius, radius, 0, 360);
	}
}