package gfc.audio;

/**
 * Only one Music can be active at a time.
 */
public class Music extends Sound {
	
	public static final int LOOP_INDEFINITELY = -1;
	
	public Music(String filename, String format, int loopcount) {
		super(filename, format);
		
		player.setLoopCount(loopcount);
	}
	
	public void play() {
		if (music != null) {
			//standard code
			
			music.stop();
			
			//motorola: some devices don't allow restarting a player after it has been stopped. that's why we dont stop() the music, only mute it
			//music.vc.setMute(true);
		}
		
		music = this;

		super.play();
	}
}
