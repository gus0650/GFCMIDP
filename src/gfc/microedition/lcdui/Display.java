package gfc.microedition.lcdui;

import gfc.graphics.Color;
import gfc.microedition.midlet.*;
import java.awt.event.*;
import javax.swing.JPanel;

//final public class Display extends java.awt.Canvas implements MouseListener, KeyListener {
final public class Display extends JPanel implements MouseListener, KeyListener  {
//using JPanel because it has paintImmediately(x,y,w,h);

	static final long serialVersionUID = 1L;
	
	Displayable current;
	Displayable previous;

	PaintThread painter = new PaintThread();
	gfc.microedition.lcdui.Graphics g = new Graphics();

	static Font font;

	public final static int
		KEY_UP    	= 38,	//cursor
		KEY_DOWN   	= 40,
		KEY_LEFT  	= 37,
		KEY_RIGHT 	= 39,
		KEY_CR  	= 10,	//return
		KEY_SPACE  	= 32,	//space
		KEY_NUM0 	= 36,	//keypad
		KEY_NUM1 	= 38,
		KEY_NUM2 	= 33,
		KEY_NUM3 	= 34,
		KEY_NUM4 	= 37,
		KEY_NUM5 	= 12,
		KEY_NUM6 	= 39,
		KEY_NUM7 	= 155,
		KEY_NUM8 	= 35,
		KEY_NUM9 	= 40;
		
	public Display(java.awt.Frame frame) {
		setFocusable(true);
		
		addMouseListener(this);
		addKeyListener(this);
		
		setSize(frame.getWidth(), frame.getHeight());

		frame.add(this);
		
		painter.start();

		font = null;//TODO initialize font from file!
	}

	public static Display getDisplay(MIDlet m) {
		return m.getDisplay();
	}
	
	public void setCurrent( Displayable s ) {
		if (s == current) {
			System.err.println("ERROR in Display.setCurrent(): this Screen is already the current one");
			return;
		}
		
		/* deactivate old Screen */
		if (current != null) {
			current.setInactive();
			current.hideNotify();

			previous = current;
		}

		/* activate new Screen */
		current = s;
		current.setActive(this);
		current.showNotify();

		repaint();
	}
	
	public void setPrevious() {
		if (current == null)
			return;
		
		if (previous == null)
			return;

		setCurrent(previous);
	}

	protected void showNotify() {
		if (current == null)	return;
		current.showNotify();
	}

	protected void hideNotify() {
		if (current == null)	return;
		current.hideNotify();
	}

	public Displayable getCurrent() {
		return current;
	}

	public void paint( Graphics g ) {
		if (current == null) 	return;
		current.paint( g );
	}


	//------- input events from AWT
	
	@Override
	public void keyPressed( KeyEvent ke ) {
		//nop
	}

	@Override
	public void keyReleased( KeyEvent ke ) {
		//nop
	}

	@Override
	public void keyTyped( KeyEvent ke ) {
		if (current == null)	return;
		current.keyEvent(ke.getKeyCode());
	}

	@Override
	public void mousePressed( MouseEvent e ) {
		if (current == null)	return;
		current.pointerPressed( e.getX(), e.getY() );
	}

	@Override
	public void mouseReleased( MouseEvent e ) {
		if (current == null)	return;
		current.pointerReleased();
	}

	@Override
	public void mouseExited( MouseEvent e ) { 
		//nop
	}

	@Override
	public void mouseEntered( MouseEvent e ) {
		//nop
	}

	@Override
	public void mouseClicked( MouseEvent e ) {
		//nop
	}

	//custom add
	public void mouseMoved( MouseEvent e ) {
		if (current == null)	return;
		current.pointerMoved( e.getX(), e.getY() );
	}

	
	//------painting
	
	public void threadedRepaint() {
		//System.out.println("threadedRepaint()");
		painter.fullClip();
		painter.run();
	}

	public void threadedRepaint(int x, int y, int w, int h) {
		//System.out.println("threadedRepaint() xywh");
		painter.fullClip();		//painter.setClip(x, y, w, h);
		painter.run();
	}

	class PaintThread extends Thread {
		
		private int x, y, w, h;
		
		public void run() {
			repaint();
			paintImmediately(x,y,w,h);
		}
		
		void setClip(int x, int y, int w, int h) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}
		
		void fullClip() {
			x = 0;
			y = 0;
			w = getWidth();
			h = getHeight();
		}
	}
	
	public void paint(java.awt.Graphics g) {
		this.g.set(g);
		paint(this.g );
	}


	//----- custom methods

	static Color acol = new Color(
		Integer.parseInt(MIDlet.GetAppProperty("COL_PASSIVE")) );
	public static Color getActiveColor() {
		return acol;
	}

	static Color pcol = new Color(
		Integer.parseInt(MIDlet.GetAppProperty("COL_ACTIVE")) );
	public static Color getPassiveColor() {
		return pcol;
	}

	public static Font getGFCFont() {
		return font;
	}

	public static int getDisplayWidth() {
		return WIDTH;		
	}

	public static int getDisplayHeight() {
		return HEIGHT;
	}

	public static int getDefaultBorderVMargin() {
		return Integer.parseInt(MIDlet.GetAppProperty("BORDERV"));
	}

	public static int getDefaultBorderHMargin() {
		return Integer.parseInt(MIDlet.GetAppProperty("BORDERH"));
	}

	public static Image getDefaultMenuitemInactive(){
		return null; //TODO
	}

	public static Image getDefaultMenuitemActive(){
		return null; //TODO
	}

}