package gfc.microedition.lcdui;

import java.awt.event.*;
import javax.swing.JPanel;


final public class Display extends JPanel implements MouseListener, KeyListener {

	private static final long 	serialVersionUID = 1L;
	
	private Displayable 		current;
	private Displayable 		previous;

	private PaintThread			painter = new PaintThread();
	private gfc.microedition.lcdui.Graphics 	gfcg = new Graphics();

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


	public Displayable getCurrent() {
		return current;
	}

	public void paint( Graphics g ) {
		if (current == null) 	return;
		current.paint( g );
	}

	//------- input events
	
	public void keyPressed( KeyEvent ke ) {
		//nop
	}

	public void keyReleased( KeyEvent ke ) {
		//nop
	}

	public void keyTyped( KeyEvent ke ) {
		if (current == null)	return;
		current.keyEvent(ke.getKeyCode());
	}

	public void mousePressed( MouseEvent e ) {
		if (current == null)	return;
		current.pointerPressed( e.getX(), e.getY() );
	}

	public void mouseReleased( MouseEvent e ) {
		if (current == null)	return;
		current.pointerReleased();
	}

	public void mouseExited( MouseEvent e ) { 
		//nop
	}

	public void mouseEntered( MouseEvent e ) {
		//nop
	}

	public void mouseMoved( MouseEvent e ) {
		if (current == null)	return;
		current.pointerMoved( e.getX(), e.getY() );
	}
	
	public void mouseClicked( MouseEvent e ) {
		if (current == null)	return;
		//nop
	}

	//------notification
	
	protected void showNotify() {
		if (current == null)	return;
		current.showNotify();
	}

	protected void hideNotify() {
		if (current == null)	return;
		current.hideNotify();
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
		gfcg.set(g);
		paint( gfcg );
	}
}