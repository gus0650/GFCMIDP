package gfc.microedition.lcdui;

public abstract class Displayable {

	protected Display display;
	
	//private void setActive(Display d) {}

	//private void setInactive() {}

	protected void paint( Graphics g ) {}
	
	
	protected void hideNotify() {}
	
	protected void showNotify() {}

	
	protected void keyEvent(int k) {}
	

	protected void pointerMoved(int x, int y) {}

	protected void pointerReleased() {}

	protected void pointerPressed(int x, int y) {}
	
	
	public final int getWidth() {
		return display.getWidth();
	}
	
	public final int getHeight() {
		return display.getHeight();
	}
}
