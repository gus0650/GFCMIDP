package gfc.util;

public class ThreadTool {

	/** 1000 millis = 1 second */
	public static void sleep(int millis) {
		/*config:debug:OFF*///System.out.println("GameApp.sleep() " + millis);

		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
