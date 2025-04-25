package gfc.microedition.lcdui;

import java.awt.event.*;
import javax.swing.JPanel;


final public class Display extends JPanel implements MouseListener, KeyListener {

	private static final long 	serialVersionUID = 1L;
	
	private Displayable 		active_screen;
	private Displayable 		previous_screen;

	private PaintThread			painter = new PaintThread();
	private gfc.microedition.lcdui.Graphics 	gfcg = new Graphics();

	
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
		if (s == active_screen) {
			System.err.println("ERROR in Display.setCurrent(): this Screen is already the current one");
			return;
		}
		
		/* deactivate old Screen */
		if (active_screen != null) {
			//active_screen.setInactive();
			active_screen.hideNotify();

			previous_screen = active_screen;
		}


		/* activate new Screen */
		active_screen = s;
		//active_screen.setActive(this);
		active_screen.showNotify();

		repaint();
	}
	

	public void setPrevious() {
		if (active_screen == null)
			return;
		
		if (previous_screen == null)
			return;

		setCurrent(previous_screen);
	}


	public Displayable getCurrent() {
		return active_screen;
	}

	public void paint( Graphics g ) {
		if (active_screen == null) 	return;
		active_screen.paint( g );
	}

	//------- input events
	
	public void keyPressed( KeyEvent ke ) {
		//nop
	}

	public void keyReleased( KeyEvent ke ) {
		//nop
	}

	public void keyTyped( KeyEvent ke ) {
		if (active_screen == null)	return;
		active_screen.keyEvent(ke.getKeyCode());
	}

	public void mousePressed( MouseEvent e ) {
		if (active_screen == null)	return;
		active_screen.pointerPressed( e.getX(), e.getY() );
	}

	public void mouseReleased( MouseEvent e ) {
		if (active_screen == null)	return;
		active_screen.pointerReleased();
	}

	public void mouseExited( MouseEvent e ) { 
		//nop
	}

	public void mouseEntered( MouseEvent e ) {
		//nop
	}

	public void mouseMoved( MouseEvent e ) {
		if (active_screen == null)	return;
		active_screen.pointerMoved( e.getX(), e.getY() );
	}
	
	public void mouseClicked( MouseEvent e ) {
		if (active_screen == null)	return;
		//nop
	}

	//------notification
	
	protected void showNotify() {
		if (active_screen == null)	return;
		active_screen.showNotify();
	}

	protected void hideNotify() {
		if (active_screen == null)	return;
		active_screen.hideNotify();
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