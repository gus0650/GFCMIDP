package gfc.graphics.particles.effects;

import gfc.graphics.AnimationGroup;
import gfc.graphics.particles.Streak;
import gfc.util.MathTool;

import java.util.Random;


public class Explosion extends AnimationGroup {

	private static final Random	random = new Random();
	
	//particle properties
	private int p_amount;
	private int p_ticks_to_live;
	private int p_velocity;
	private int p_deceleration;
	private int p_gravity;
	
	private int[] p_colors;

	
	/**
	 * 
	 * @param numStreaks - number of Streaks. (Note: the actual amount of Streaks created is numStreaks^2)
	 * @param pTicksToLive
	 * @param pVelocity
	 * @param pDeceleration
	 * @param pGravity
	 */
	public Explosion( int numStreaks, int pTicksToLive, int pVelocity, int pDeceleration, int pGravity, int[] colorList ) {
		p_amount 		= numStreaks;
		p_ticks_to_live	= pTicksToLive;
		p_velocity 		= pVelocity;
		p_deceleration 	= pDeceleration;
		p_gravity 		= pGravity;
		
		p_colors = colorList;

		int p = p_amount * p_amount;
		for (int i = 0; i < p; ++i) {
			addAnimation( new Streak() );
		}
	}
	
	
	public void reset (int ps_x, int ps_y) {
		
		int c = 0;
		
		for (int i = 0; i < p_amount; ++i) {
			for (int j = 0; j < p_amount; ++j) {
				
				int projected_angle 	= i * 3 + rand(3);
				int plane_angle 		= j * 3 + rand(3);

				int magnitudeTimes256 =
					p_velocity * MathTool.cosineTimes256(plane_angle);

				int vx =
					((MathTool.cosineTimes256(projected_angle) * magnitudeTimes256)
						<< Streak.FIXED_POINT_SHIFT)
						>> 16;
				
				int vy =
					((MathTool.sineTimes256(projected_angle) * magnitudeTimes256)
						<< Streak.FIXED_POINT_SHIFT)
						>> 16;

				int ticks =
					p_ticks_to_live + rand(p_ticks_to_live);

				int px = ps_x << Streak.FIXED_POINT_SHIFT;
				int py = ps_y << Streak.FIXED_POINT_SHIFT;

				Streak s = (Streak)getAnim(c);
				s.reset(	px, 
							py, 
							vx, 
							vy, 
							p_deceleration, 
							p_gravity, 
							p_colors, 
							ticks );
				
				c++;
			}
		}
		
		toFirstFrame();
	}
	
	
	private static int rand(int scale) {
		return (random.nextInt() << 1 >>> 1) % scale;
	}
}
