package gfc.microedition.lcdui;

import gfc.microedition.midlet.*;
import gfc.widgets.*;


abstract public class Screen extends Canvas {

	protected Widget 	widget;
	
	Screen(String title) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Screen()");

		setTitle(title);
	}
	

	void setContent( Item item ) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Screen.setContent()");

		widget = item.getWidget();

		widget.setDisplayable(this);

		posHome();
	}
	
	
	public void paint(Graphics g) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Screen.paint()");
		
		//margins
		g.clipRect(
				g.getClipX() + Display.getDefaultBorderHMargin(), 
				g.getClipY() + Display.getDefaultBorderVMargin(), 
				getWidth()   - Display.getDefaultBorderHMargin()*2,  
				getHeight()  - Display.getDefaultBorderVMargin()*2  
				);

		//paint items
		if (widget != null) widget.paint(g);
	}


	void posDown() {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Screen.posDown()");

		int visible = widget.getHeight() + (widget.getY() - (getPaintableAreaOffsetY() + Display.getDefaultBorderVMargin()) );
		
		//is visible part of item smaller than area available for item?
		if (visible  < getHeight() - Display.getDefaultBorderVMargin()*2 ) {
			//was last page; return to top
			posHome();
		}
		else {
			//next page
			int cy = (getHeight() - Display.getDefaultBorderVMargin()*2) - Display.getGFCFont().getHeight();  //make sure last line is not cut
			widget.setY(widget.getY() - cy );
		}
		
		repaint();
	}


	void posHome() {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("Screen.posHome()");

		//center horz'y
		int cx = Display.getDisplayWidth()/2  - widget.getWidth()/2;
		widget.setX( cx );
	
		//position vert'y
		int cy = getPaintableAreaOffsetY();
		if (!isMultiPage()) {
			//panel completely fits into ItemArea -> center vert'y inside paintable area
			cy += getPaintableAreaHeight()/2 - widget.getHeight()/2;
		}
		widget.setY( cy );
	}
	
	
	void posAt(Item item) {
		//TODO flip to item
	}
	
	
	/**	
	 * @return - true if widget doesnt fit onto screen in one piece
	 */
	boolean isMultiPage() {
		return ( widget.getHeight() > getPaintableAreaHeight() );
	}



	//------

	int getPaintableAreaOffsetX() {
		int ox = Display.getDefaultBorderHMargin();
		return ox;
	}
	
	int getPaintableAreaOffsetY() {
		int oy = Display.getDefaultBorderVMargin();
		return oy;
	}
	
	int getPaintableAreaWidth() {
		int ow = getWidth() - Display.getDefaultBorderHMargin()*2;
		return ow;
	}
	
	int getPaintableAreaHeight() {
		int oh = getHeight() - Display.getDefaultBorderVMargin()*2;
		
		if (title != null) oh -= title.getHeight();

		//TODO consider buttons!
		
		return oh;
	}
}
