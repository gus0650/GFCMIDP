package gfc.microedition.lcdui;

import gfc.widgets.*;


abstract public class Screen extends Canvas {

	protected Widget 	s_widget;
	
	Screen(String title) {
		/*config:debug:OFF*///System.out.println("Screen()");

		setTitle(title);
	}
	

	void setContent( Item item ) {
		/*config:debug:OFF*///System.out.println("Screen.setContent()");

		s_widget = item.getWidget();

		s_widget.setDisplayable(this);

		posHome();
	}
	
	
	public void paint(Graphics g) {
		/*config:debug:OFF*///System.out.println("Screen.paint()");
		
		//margins
		g.clipRect(
				g.getClipX() + DisplayProperties.getDefaultBorderHMargin(), 
				g.getClipY() + DisplayProperties.getDefaultBorderVMargin(), 
				getWidth()   - DisplayProperties.getDefaultBorderHMargin()*2,  
				getHeight()  - DisplayProperties.getDefaultBorderVMargin()*2  
				);

		//paint items
		if (s_widget != null) s_widget.paint(g);
	}


	void posDown() {
		/*config:debug:OFF*///System.out.println("Screen.posDown()");

		int visible = s_widget.getHeight() + (s_widget.getY() - (getPaintableAreaOffsetY() + DisplayProperties.getDefaultBorderVMargin()) );
		
		//is visible part of item smaller than area available for item?
		if (visible  < getHeight() - DisplayProperties.getDefaultBorderVMargin()*2 ) {
			//was last page; return to top
			posHome();
		}
		else {
			//next page
			int cy = (getHeight() - DisplayProperties.getDefaultBorderVMargin()*2) - DisplayProperties.getFont().getHeight();  //make sure last line is not cut
			s_widget.setY( s_widget.getY() - cy );
		}
		
		repaint();
	}


	void posHome() {
		/*config:debug:OFF*///System.out.println("Screen.posHome()");

		//center horz'y
		int cx = DisplayProperties.getScreenWidth()/2  - s_widget.getWidth()/2;
		s_widget.setX( cx );
	
		//position vert'y
		int cy = getPaintableAreaOffsetY();
		if (!isMultiPage()) {
			//panel completely fits into ItemArea -> center vert'y inside paintable area
			cy += getPaintableAreaHeight()/2 - s_widget.getHeight()/2;
		}
		s_widget.setY( cy );
	}
	
	
	void posAt(Item item) {
		//TODO flip to item
	}
	
	
	/**	
	 * @return - true if widget doesnt fit onto screen in one piece
	 */
	boolean isMultiPage() {
		return ( s_widget.getHeight() > getPaintableAreaHeight() );
	}



	//------

	int getPaintableAreaOffsetX() {
		int ox = DisplayProperties.getDefaultBorderHMargin();
		return ox;
	}
	
	int getPaintableAreaOffsetY() {
		int oy = DisplayProperties.getDefaultBorderVMargin();
		return oy;
	}
	
	int getPaintableAreaWidth() {
		int ow = getWidth() - DisplayProperties.getDefaultBorderHMargin()*2;
		return ow;
	}
	
	int getPaintableAreaHeight() {
		int oh = getHeight() - DisplayProperties.getDefaultBorderVMargin()*2;
		
		if (title != null) oh -= title.getHeight();

		//TODO consider buttons!
		
		return oh;
	}
}
