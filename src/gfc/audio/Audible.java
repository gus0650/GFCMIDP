package gfc.audio;

abstract public class Audible {

	protected static Audible	music;
	
	private static int 			vol 	= 50;
	private static boolean 		mute 	= false;
	
	
	public void play() {
		try {
			setVolume(vol);
			//vc.setMute(mute);
			//if (player.getMediaTime() > 0) player.setMediaTime(0);
			//player.start();
		} 
		catch (Exception e) { 
			/*config:debug:ON*/e.printStackTrace();
		}
	}
	
	public void stop() {
		try {
			//player.stop();
		} 
		catch (Exception e) { 
			/*config:debug:ON*/e.printStackTrace();
		}
	}
	
	public static void stopMusic() {
		if (music != null) music.stop();
	}

	public static void startMusic() {
		if (music != null) music.play();
	}
	
	public static void setVolume(int v) {
		vol = v;

		if (music != null) {
			//music.vc.setLevel(vol);
		}
	}

	public static void setMute(boolean b) {
		mute = b;

		if (music != null) {
			//music.vc.setMute(mute);
		}
	}
	
	public static boolean isMuted() {
		return mute;
	}
}
