package gfc.graphics;

import gfc.microedition.lcdui.*;


/**
 * intended to be drawn directly onto a Canvas
 */
abstract public class Paintable {

	private int x, y, width, height;
	private boolean visible = true;
	private Color bgcolor;
	private Canvas canvas;
	
	
	/**
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 * @param bgcolor
	 */
	public Paintable(int width, int height, int x, int y, Canvas canvas, Color bgcolor) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bgcolor = bgcolor;
		this.canvas = canvas;
	}
	
	
	/**
	 * @return Returns the height.
	 */
	public int getHeight() {
		return height;
	}
	
	
	/**
	 * @param height The height to set.
	 */
	public void setHeight(int height) {
		this.height = height;
		repaintAll();
	}
	
	
	/**
	 * @return Returns the width.
	 */
	public int getWidth() {
		return width;
	}
	
	
	/**
	 * @param width The width to set.
	 */
	public void setWidth(int width) {
		this.width = width;
		repaintAll();
	}
	
	
	/**
	 * @return Returns the x.
	 */
	public int getX() {
		return x;
	}
	
	
	/**
	 * @param x The x to set.
	 */
	public void setX(int x) {
		this.x = x;
		repaintAll();
	}
	
	
	/**
	 * @return Returns the y.
	 */
	public int getY() {
		return y;
	}
	
	
	/**
	 * @param y The y to set.
	 */
	public void setY(int y) {
		this.y = y;
		repaintAll();
	}

	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		repaintAll();
	}

	
	/**
	 * @return Returns the bgcolor.
	 */
	public Color getBG() {
		return bgcolor;
	}
	
	
	/**
	 * @param bgcolor The bgcolor to set.
	 */
	public void setBG(Color bgcolor) {
		this.bgcolor = bgcolor;
		repaint();
	}

	
	public void setVisible(boolean flag) {
		visible = flag;
	}
	
	
	public boolean isVisible() {
		return visible;
	}
	
	
	final public void paint(Graphics g) {
			if (!visible) return;
		
			//store
			int clip_x  	= g.getClipX();
			int clip_y 		= g.getClipY();
			int clip_width  = g.getClipWidth();
			int clip_height = g.getClipHeight();
			int trans_x 	= g.getTranslateX();
			int trans_y 	= g.getTranslateY();
			
			//set
			g.translate( -trans_x, -trans_y );
			g.translate( x, y );
			g.setClip( 0, 0, width, height );

			//clear background
			if ( bgcolor != null )  {
				g.setColor( bgcolor.getValue() );
				g.fillRect( 0, 0, width, height );
			}
			
			//call paint method of child class
			draw( g );

			//restore
			g.translate( -x, -y );
			g.translate( trans_x, trans_y );
			g.setClip( clip_x, clip_y, clip_width, clip_height );
	}

	
	final public void repaint() {
		//workaround for bug in Nokia S40
		int px = x-1;
		int py = y-1;
		int pw = width+2; 
		int ph = height+2;
		
		if (px < 1) {
			pw = pw + px;
			px = 0;
		}
		if (py < 1) {
			ph = ph + py;
			py = 0;
		}
		
		canvas.repaint();
	}

	
	final public void repaintAll() {
		canvas.repaint();
	}
	
	abstract protected void draw(Graphics g);
}
