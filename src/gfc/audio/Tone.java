package gfc.audio;

import javax.microedition.media.*;
import javax.microedition.media.control.*;

public class Tone extends Audible {

	public static final byte 
		C4 = ToneControl.C4,
		D4 = (byte)(C4 + 2),
		E4 = (byte)(C4 + 4),
		F4 = (byte)(C4 + 5),
		G4 = (byte)(C4 + 7),
		C5 = (byte)(C4 + 12),
		C6 = (byte)(C4 + 24);
	
	private byte[] tones;
	
	public Tone( byte[] toneSequence, byte volume, byte tempo ) {
		
		tones = new byte[toneSequence.length+6];
		
		System.arraycopy( toneSequence, 0, tones, 6, toneSequence.length );

		tones[0] = ToneControl.VERSION;
		tones[1] = 1;
				
		tones[2] = ToneControl.TEMPO;
		tones[3] = tempo;
			
		tones[4] = ToneControl.SET_VOLUME;
		tones[5] = volume;
		
	    try {
	    	//init player
	    	player = Manager.createPlayer(Manager.TONE_DEVICE_LOCATOR);
	        player.realize();
	        ToneControl tc = (ToneControl)player.getControl("ToneControl");
	        tc.setSequence(tones);
	        
			//init volumecontroler
			vc 	= (VolumeControl)player.getControl("VolumeControl");
	    }
	    catch (Exception e) {
	    	/*config:debug:ON*/e.printStackTrace();
	    }
	}
}
