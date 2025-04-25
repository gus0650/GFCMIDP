package gfc.microedition.lcdui;

//TODO incomplete implementation

abstract public class Canvas extends Displayable {

	public final void repaint() {
		display.repaint();
	}
	
	protected void paint(Graphics g) { }
}
