package gfc.audio;

import javax.microedition.media.*;
import javax.microedition.media.control.*;
import java.io.*;

public class Sound extends Audible {
	
	public Sound(String filename, String format) {
		//System.out.println( "Sound() "+ filename );

		InputStream is;

		try {
			//init player
			is 	= getClass().getResourceAsStream( filename );
			player = Manager.createPlayer( is, format );
			player.realize();
			
			//init volumecontroler
			vc 	= (VolumeControl)player.getControl("VolumeControl");
		}
		catch (Exception e) { 
			/*config:debug:ON*/e.printStackTrace();
		}
	}
}
