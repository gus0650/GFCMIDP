package gfc.widgets;

import gfc.graphics.*;
import gfc.microedition.lcdui.*;

/**
 * A Widget
 * <li> has a border
 * <li> has a background image
 * <li> is aligned inside the cells of a LayoutManager
 * <li> is selectable or non-selectable (clickable)
 * <li> can be enabled or disabled. a disabled Widget cannot be selected.<p>
 * 
 * Default settings: non-selectable, not selected, enabled.
 * 
 * @author Gustaf Stechmann
 */

//TODO - test if borders work properly
abstract public class Widget {
	
	private int 			x				= 0;
	private int 			y				= 0;
	private int				width, height;
	private boolean 		visible 		= true;
	private Displayable 	displayable		= null;
	private Panel 			panel			= null;
	private Command			command			= null;
	private WidgetSelectListener wsl;

	//behavior
	private boolean			selectable 		= true;
	private boolean			selected 		= false;
	private boolean			enabled 		= true;
	
	//appearance
	private int				alignment		= Graphics.TOP | Graphics.LEFT;
	private Color			bg_color 		= null;
	private Image			bg_image 		= null;

	//border
	protected Color			border_inactive = null;
	protected Color			border_active 	= null;
	protected int			border_horz 	= 0,
							border_vert 	= 0;
	
	
	public Widget(int width, int height, int x, int y, Displayable displayable) {
		this.width  = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.displayable = displayable;

		/*config:debug:OFF*///System.out.println("Widget()");
	}
	
	
    public Widget(int width, int height, Displayable displayable) {
        this(width, height, 0, 0, displayable);
        
		/*config:debug:OFF*///System.out.println("Widget()");
    }

    
	abstract void draw(Graphics g);
	
	
	/**
	 * Paint this Widget.
	 */
	public void paint(Graphics g) {
		/*config:debug:OFF*///System.out.println("Widget.paint()");

		//store clip
		int clip_x  	= g.getClipX();
		int clip_y 		= g.getClipY();
		int clip_width  = g.getClipWidth();
		int clip_height = g.getClipHeight();
		
		//set
		g.translate( getX(), getY() );
		g.clipRect( 0, 0, getWidth(), getHeight() );

		//background color
		if ( bg_color != null )  {
			g.setColor( bg_color );
			g.fillRect( 0, 0, getWidth(), getHeight() );
		}

		//background image
		if ( bg_image != null )  {
			g.drawImage( bg_image, 0, 0, 0 );
		}

		//border
		Color tc = null;
		if ( !selected  && border_inactive != null )	tc = border_inactive;
		if ( selected   && border_active != null )		tc = border_active;
		
		if (tc != null) {
			g.setColor( tc.getRGB() );
			if (border_horz > 0) {
				g.fillRect( 0, 0, 						getWidth(), border_horz );
				g.fillRect( 0, height + border_horz, 	getWidth(), border_horz );
			}
			if (border_vert > 0) {
				g.fillRect( 0, 0, 						border_vert, getHeight() );
				g.fillRect( width + border_vert, 0, 	border_vert, getHeight() );
			}
		}
		
		//call paint method of child class
		g.translate( border_horz, border_vert );
        g.clipRect( 0, 0, width, height );
        draw( g );
		g.translate( -border_horz, -border_vert );

		//restore clip
		g.translate(-getX(), -getY());
		g.setClip( clip_x, clip_y, clip_width, clip_height );
	}


	final public void setAlignment( int flags ) {
		/*config:debug:OFF*///System.out.println("Widget.setAlignment()");

		alignment	= flags;

		if (panel != null) panel.doLayout();
		repaint();
	}
	
	
	public int getAlignment() {
		return alignment;
	}

	
	final public void setBorderProperties( int hMargin, int vMargin, Color activeColor, Color inactiveColor ) {
		/*config:debug:OFF*///System.out.println("Widget.setBorderProperties()");

		border_horz 	= hMargin;
		border_vert 	= vMargin;
		
		border_inactive	= inactiveColor;
		border_active 	= activeColor;
		
        if (panel != null) panel.doLayout();
		repaint();
	}

	
	final public int getBorderHMargin() {
		return border_horz;
	}

	
	final public int getBorderVMargin() {
		return border_vert;
	}
	
	
	/**
	 * Set the background color.
	 * 
	 * @param backgroundColor - background color. If it is null, the background is transparent
	 */
	final public void setBGColor( Color backgroundColor ) {
		/*config:debug:OFF*///System.out.println("Widget.setBGColor()");

		bg_color 		= backgroundColor;
		repaint();
	}

	
	final public Color getBGColor( ) {
		return bg_color;
	}
	
	
	final public void setBGImage( Image img ) {
		/*config:debug:OFF*///System.out.println("Widget.setBGImage()");

		bg_image 		= img;
		repaint();
	}

	
	final public Image getBGImage() {
		return bg_image;
	}

	
	public void setSelected(boolean flag) {
		/*config:debug:OFF*///System.out.println("Widget.setSelected()");

		if (!isSelectable()) return;

		if (!isEnabled()) System.err.println("cannot select a disabled Widget"); 

		selected = flag;
		
		if (selected) 	notifySelected();
		else 			notifyDeselected();
		
		if (wsl != null) wsl.widgetStateChanged( this );
		
		repaint();
	}

	
	public boolean isSelected() {
		return selected;
	}

	
	public void setWidgetSelectListener(WidgetSelectListener listener) {
		/*config:debug:OFF*///System.out.println("Widget.setWidgetSelectListener()");

		wsl = listener;
	}

	
	protected void setSelectable(boolean flag) {
		/*config:debug:OFF*///System.out.println("Widget.setSelectable()");

		selectable = flag;
	}

	
	protected boolean isSelectable() {
		return selectable;
	}

	
	public void setEnabled(boolean flag) {
		/*config:debug:OFF*///System.out.println("Widget.setEnabled()");

		enabled = flag;
	}

	
	public boolean isEnabled() {
		return enabled;
	}

	
	final public boolean isVisible() {
		return visible;
	}


	final public void setVisible(boolean flag) {
		/*config:debug:OFF*///System.out.println("Widget.setVisible()");

		if( flag == visible ) return;
	
		visible = flag;

		repaint();
	}
	
	
	Panel getPanel() {
		return panel;
	}
	
	
	void setPanel( Panel panel ) {
		/*config:debug:OFF*///System.out.println("Widget.setPanel()");

		this.panel = panel;
	}

	
	public void setDisplayable(Displayable d) {
		/*config:debug:OFF*///System.out.println("Widget.setDisplayable()");

		displayable = d;
	}

	
	public Displayable getDisplayable() {
		return displayable;
	}

	
	public void setCommand(Command c) {
		/*config:debug:OFF*///System.out.println("Widget.setCommand()");

		command = c;
	}

	
	public Command getCommand() {
		return command;
	}
	

	public void triggerCommand(CommandListener cl, Displayable d) {
		/*config:debug:OFF*///System.out.println("Widget.triggerCommand()");

		cl.commandAction(command, d);
	}


	/**
	 * Get the horizontal position of this Widget's upper-left corner, 
	 * relative to the painter's coordinate system.
	 */
	final public int getX() {
		return x;
	}

	
	/**
	 * Get the vertical position of this Widget's upper-left corner, 
	 * relative to the painter's coordinate system.
	 */
	final public int getY() {
		return y;
	}

	
	/**
	 * Get the absolute x coordinate of the Widget.
	 * 
	 * If this Widget is part of a hierarchy of Panels, this hierarchy
	 * is traversed backwards to calculate the coordinate.
	 * 
	 * @return - the x of the Widget relative to the "root" Panel
	 */
	final int getAbsX() {
		int abs_x = x;
		
		for( Panel p = panel; p != null; p = p.getPanel() ) {
			abs_x += p.getX();
		}

		return abs_x;
	}

	
	/**
	 * Get the absolute y coordinate of the Widget.
	 * 
	 * If this Widget is part of a hierarchy of Panels, this hierarchy
	 * is traversed backwards to calculate the coordinate.
	 * 
	 * @return - the y of the Widget relative to the "root" Panel
	 */
	final int getAbsY() {
		int abs_y = y;
		
		for( Panel p = panel; p != null; p = p.getPanel() ) {
			abs_y += p.getY();
		}
		
		return abs_y;
	}


	final public void move(int dx, int dy) {
		/*config:debug:OFF*///System.out.println("Widget.move()");

		setPosition( x + dx, y + dy );
	}

	
	public void setX(int x) {
		/*config:debug:OFF*///System.out.println("Widget.setX()");

		this.x 	= x;
		repaint();
	}

	
	public void setY(int y) {
		/*config:debug:OFF*///System.out.println("Widget.setY()");

		this.y 	= y;
		repaint();
	}
	

	/**
	 * Sets this Widget's position such that its upper-left corner is located at (x,y) in the painter's coordinate system.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	final public void setPosition( int x, int y ) {
		/*config:debug:OFF*///System.out.println("Widget.setPosition()");

		this.x 	= x;
		this.y 	= y;
		repaint();
	}
	

	public void setContentWidth(int w) {
		/*config:debug:OFF*///System.out.println("Widget.setContentWidth()");

        width = w;
        
        if (panel != null) panel.doLayout();
        repaint();
	}

	
	public void setContentHeight(int h) {
		/*config:debug:OFF*///System.out.println("Widget.setContentHeight()");

		height = h;
		
        if (panel != null) panel.doLayout();
        repaint();
	}
	
	
	/**
	 * Gets the current width of this layer, in pixels.
	 * 
	 * @return - content width plus border width
	 */
	public int getWidth() {
		return width + border_horz*2;
	}


	/**
	 * Gets the current height of this layer, in pixels.
	 * 
 	 * @return - content height plus border height
	 */
	public int getHeight() {
		return height + border_vert*2;
	}
	
	
	/**
	 * (this method processes faster than setting width & height separately)
	 */
    public void setDimensions(int w, int h) {
		/*config:debug:OFF*///System.out.println("Widget.setDimensions()");

        width 	= w;
        height 	= h;
        
        if (panel != null) panel.doLayout();
        repaint();
    }
    
	
	public void setBounds(int x, int y, int width, int height) {
		/*config:debug:OFF*///System.out.println("Widget.setBounds()");

		this.x = x;
		this.y = y;
		this.width 	= width;
		this.height = height;
		
        if (panel != null) panel.doLayout();
        repaint();
	}
	
	
	/**
	 * Test if a point is inside the bounds of this Widget.
	 * 
	 * @param coord_x - x coordinate of the point to be tested
	 * @param coord_y - y coordinate of the point to be tested
	 * @return TRUE if the point is inside the Widget, FALSE otherwise
	 */
	final public boolean containsPoint(int coord_x, int coord_y) {
		boolean flag = false;
		
		if ( 	(getAbsX() 					<= coord_x) && 
				(getAbsX() + getWidth() 	>  coord_x) &&
				(getAbsY() 					<= coord_y) &&
				(getAbsY() + getHeight()	>  coord_y) ) 		flag = true;

		return flag;
	}
	
	public void repaint() {
        displayable.repaint();
	}
	
    @Override
	public String toString() {
		String s;

		s = getClass().getName() + 
				" x=" + getX() + 
				" y=" + getY() + 
				" w=" +	getWidth() + 
				" h=" + getHeight() +
				" absX=" + getAbsX() + 
				" absY=" + getAbsY() + 
				" layout=" +
				(((alignment & Graphics.TOP) != 0) ? "TOP " : "") +
				(((alignment & Graphics.BOTTOM) != 0) ? "BOTTOM " : "") +
				(((alignment & Graphics.LEFT) != 0) ? "LEFT " : "") +
				(((alignment & Graphics.RIGHT) != 0) ? "RIGHT " : "") +
				(((alignment & Graphics.HCENTER) != 0) ? "HCENTER " : "") +
				(((alignment & Graphics.VCENTER) != 0) ? "VCENTER " : "");

		return s;
	}
	
	
    /**
     * This method is called when the Widget is deselected.
     * 
     * Subclasses may override to respond to such an event.
     */
    protected void notifyDeselected() {
		/*config:debug:OFF*///System.out.println("Widget.notifyDeselected()");
    }
    
    /**
     * This method is called when the Widget is selected.
     * 
     * Subclasses may override to respond to such an event.
     */
    protected void notifySelected() {
		/*config:debug:OFF*///System.out.println("Widget.notifySelected()");
    }
}
