package gfc.microedition.lcdui;


abstract public class Canvas extends Displayable {

	public final void repaint() {
		display.repaint();
	}
	
	protected void paint(Graphics g) { }
}
