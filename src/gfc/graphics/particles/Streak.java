package gfc.graphics.particles;

import gfc.graphics.*;
import gfc.microedition.lcdui.*;


public class Streak implements Animation {

	public static final int	FIXED_POINT_SHIFT	= 8;

	private int				x;
	private int				y;
	private int				vx;
	private int				vy;
	private int				oldx;
	private int				oldy;

	private int 			deceleration;
	private int 			gravity;

	private int[]			colors;
	private int				color_index;

	private int				ticks;	//ticks to live

	private int				default_x, default_y;
	private int				default_vx, default_vy;
	private int				default_ticks;


	public void reset( int pX, int pY, int pVX, int pVY, int pDeceleration, int pGravity, int[] pColors, int pTicksToLive ) {
		default_x 		= pX;
		default_y 		= pY;

		default_vx 		= pVX;
		default_vy 		= pVY;

		colors 			= pColors;
		
		default_ticks 	= pTicksToLive;
		
		deceleration 	= (1 << Streak.FIXED_POINT_SHIFT) / (10 * pDeceleration);
		gravity 		= (1 << Streak.FIXED_POINT_SHIFT) / (10 * pGravity);
	}

	
	public void toFirstFrame() {
		x = default_x;
		y = default_y;

		vx = default_vx;
		vy = default_vy;
		
		oldx = x;
		oldy = y;

		color_index = 0;
		
		ticks = default_ticks;
	}
	
	
	public void toNextFrame() {
		if (ticks == 0) return;
		
		oldx = x;
		oldy = y;

			x += vx;
			y += vy;

            vx -= (vx * deceleration) >> Streak.FIXED_POINT_SHIFT;
            vy -= (vy * deceleration) >> Streak.FIXED_POINT_SHIFT;
			
			vy += gravity;
			
			color_index++;
 			if (color_index >= colors.length) color_index = 0;

 		ticks--;
	}


	public boolean hasMoreFrames() {
		return (ticks > 0);
	}
	

	public void paint( Graphics g ) {
		if (ticks == 0) return;
		
		g.setColor(colors[color_index]);

		g.drawLine(
				x >> FIXED_POINT_SHIFT, 
				y >> FIXED_POINT_SHIFT, 
				oldx >> FIXED_POINT_SHIFT, 
				oldy >> FIXED_POINT_SHIFT
			);
	}
}