package gfc.microedition.lcdui;

import gfc.microedition.midlet.*;
import gfc.graphics.*;
import gfc.widgets.*;
import java.util.*;

public abstract class Displayable {

	protected Display display;
	
//TODO who uses this?
	void setActive(Display d) {
		display = d;
	}

	void setInactive() {
		display = null;
	}

	public boolean isActive() {
		return (display != null);
	}

	//-------- painting

//TODO who calls this?
	void draw(Graphics g){
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Displayable.draw()");

		//make sure there is no translation
		g.translate( -g.getTranslateX(), -g.getTranslateY() );
		
		//clear all
		g.setColor( Display.getPassiveColor() );
		g.fillRect( 0, 0, Display.getDisplayWidth(), Display.getDisplayHeight() );

		//translate to underneath title bar
		if (title != null) g.translate( 0, title.getHeight() );

		//actual clip & translation area excludes title, but includes buttons (if present)
/*		int paintable_height = getHeight();
		if ( (d_title  			!= null) ) 	paintable_height	-= d_title.getHeight();
		
			g.clipRect(
					0, 
					0, 
					getWidth(), 
					paintable_height );
*/	

		//call subclass' paint method
		paint(g);
		
		//reset clip to full screen & reset translation
		g.translate( -g.getTranslateX(), -g.getTranslateY() );
		
		g.setClip(
			0, 
			0,
			Display.getDisplayWidth(), 
			Display.getDisplayHeight() );

		//paint title
		if ( (title  != null) ) title.paint(g);

		//paint buttons
		if ( (btn_left  != null) ) 	btn_left.paint(g);
		if ( (btn_right != null) ) 	btn_right.paint(g);
	}

	public void repaint() {
		display.repaint();
	}

	void repaint(int x, int y, int width, int height) {
		//if (display == null) return;
		display.repaint(x, y, width, height);
	}
	

	abstract void paint( Graphics g );


	//---- size

	public int getWidth() {
		return display.getWidth();
	}
	
	public int getHeight() {
		int h = display.getHeight();
		
		if (ticker   != null) 			h -= ticker.getHeight();
		
		if (title    != null) 			h -= title.getHeight();
		
		if (btn_left != null) 			h -= btn_left.getHeight();
		else if (btn_right != null) 	h -= btn_right.getHeight();

		return  h;
	}

	protected void sizeChanged(int w, int h) {
		System.err.println("WARNING -- Displayable.sizeChanged() not implemented");
		//TODO
	}


	//---- title

	protected Label title;

	public String getTitle() {
		return title.getString();
	}

	public void setTitle(String s) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Displayable.setTitle()");

		if (s == null) {
			title = null;
			return;
		}
		
		title = new Label(
				Display.getDisplayWidth(), 
				Display.getGFCFont().getHeight(), 
				0, 
				0, 
				this, 
				s, 
				Display.getGFCFont(), 
				Display.getActiveColor()
			);
		
		int hborder = (	Display.getDisplayWidth() - Display.getGFCFont().stringWidth(s) ) / 2;
		title.setBorderProperties( hborder, Display.getDefaultBorderVMargin(), null, null);
	}


	//----- ticker

	Ticker ticker;

	public Ticker getTicker() {
		return ticker;
	}
	
	public void setTicker(Ticker ticker) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Displayable.setTicker()");

		if (ticker != null) ticker.stop();
		
		this.ticker = ticker;
		ticker.run();
	}


	//---- commands

	Vector d_commands = new Vector();
	Button btn_left, btn_right;
	Menu d_menu_left, d_menu_right;
	CommandListener d_command_listener;

	public void addCommand(Command cmd) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Displayable.addCommand() " + cmd.getLabel());

		final byte 
			SIDE_LEFT 	= 0,
			SIDE_RIGHT 	= 1;
		
		d_commands.addElement(cmd);
				
		byte side = -1;
		if (cmd.getCommandType() == Command.OK) 	side = SIDE_RIGHT;
		if (cmd.getCommandType() == Command.SCREEN) side = SIDE_RIGHT;
		if (cmd.getCommandType() == Command.ITEM) 	side = SIDE_RIGHT;
		if (cmd.getCommandType() == Command.HELP) 	side = SIDE_RIGHT;
		if (cmd.getCommandType() == Command.EXIT) 	side = SIDE_LEFT;
		if (cmd.getCommandType() == Command.BACK) 	side = SIDE_LEFT;
		if (cmd.getCommandType() == Command.CANCEL) side = SIDE_LEFT;
		if (side == -1) System.err.println("WARNING in Displayable.addCommand(): unsupported command type -- " + cmd.getCommandType());
		
		if ( side == SIDE_LEFT ) {
			if (btn_left == null) {
				//no button there -- place cmd on screen
				btn_left	= new Button(
						0, 
						Display.getDisplayHeight() - Display.getGFCFont().getHeight(), 
						this, 
						cmd.getLabel(), 
						Display.getGFCFont(), 
						Display.getPassiveColor() 
					);
				btn_left.setCommand(cmd);
			}
			else {
				//already a button there -- place cmd in menu
				
				if (d_menu_left == null) {
					//create menu
					d_menu_left = new Menu( MIDlet.GetAppProperty("TEXT_MENU_OPEN"), this );
					d_menu_left.add(btn_left.getCommand() );
					
					btn_left.setString( MIDlet.GetAppProperty("TEXT_MENU_OPEN") );
				}
				
				d_menu_left.add( cmd );
			}
		}
		
		else
		if ( side == SIDE_RIGHT ) {
			if (btn_right == null) {
				//no button there -- place cmd on screen
				btn_right	= new Button(
						Display.getDisplayWidth() - Display.getGFCFont().stringWidth( cmd.getLabel() ),
						Display.getDisplayHeight() - Display.getGFCFont().getHeight(), 
						this, 
						cmd.getLabel(), 
						Display.getGFCFont(),
						Display.getPassiveColor() 
					);
				btn_right.setCommand(cmd);
			}
			else {
				// already a button there -- place cmd in menu

				if (d_menu_right == null) {
					//create menu
					d_menu_right = new Menu( MIDlet.GetAppProperty("TEXT_MENU_OPEN"), this );
					d_menu_right.add(btn_right.getCommand() );
					
					btn_right.setString( MIDlet.GetAppProperty("TEXT_MENU_OPEN") );
				}
				
				d_menu_right.add( cmd );
			}
		}
	}

	public void removeCommand(Command cmd) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Displayable.removeCommand()");

		d_commands.removeElement(cmd);
	}

	public void setCommandListener(CommandListener cl) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Displayable.()");

		d_command_listener = cl;
	}


	//------------- called from Display

	protected void hideNotify() {}
	
	protected void showNotify() {}

	
	protected void keyEvent(int k) {}

	protected void pointerMoved(int x, int y) {}

	protected void pointerReleased() {}

	protected void pointerPressed(int x, int y) {}
	

}
