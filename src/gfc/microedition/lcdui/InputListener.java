package gfc.microedition.lcdui;


/**
 * interface damit Displayables events von AWT empfangen können
 */
interface InputListener {

	void keyPressedPreprocess(int keyCode);
	public void keyPressed(int keyCode);
	
	void keyReleasedPreprocess(int keyCode);
	public void keyReleased(int keyCode);
	
	void pointerPressedPreprocess(int x, int y);
	public void pointerPressed(int x, int y);
	
	void pointerReleasedPreprocess(int x, int y);
	public void pointerReleased(int x, int y);
}
