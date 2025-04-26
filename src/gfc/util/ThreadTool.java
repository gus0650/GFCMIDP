package gfc.util;

import gfc.microedition.midlet.*;

public class ThreadTool {

	/** 1000 millis = 1 second */
	public static void sleep(int millis) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("GameApp.sleep() " + millis);

		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
