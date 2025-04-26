package gfc.graphics;

import gfc.util.TimerTask;


/**
 * @author Gus
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AnimationTask extends TimerTask {

		private Animation at_anim;
		private boolean at_loop, at_finished;

		
		public AnimationTask( Animation anim, boolean loop ) {
			at_anim = anim;
			at_loop = loop;
			
			at_anim.toFirstFrame();
		}
		

		public boolean isFinished() {
			return at_finished;
		}

		
		public void run() {
			if ( at_anim.hasMoreFrames() ) at_anim.toNextFrame();
			else {
				if (at_loop) at_anim.toFirstFrame();
				else at_finished = true;
			}
		}
}
