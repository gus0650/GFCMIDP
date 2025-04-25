package gfc.microedition.lcdui;

public class Graphics {

	private java.awt.Graphics awtgraphics;
	
	private int trans_x, trans_y;
	
	void dispose() {
		awtgraphics.dispose();
		awtgraphics = null;
	}
	
	void set(java.awt.Graphics g) {
		if (g == null) new NullPointerException();
		awtgraphics = g;
	}

	public int getClipX() {
		java.awt.Rectangle r = awtgraphics.getClipBounds();
		return r.x;
	}

	public int getClipY() {
		java.awt.Rectangle r = awtgraphics.getClipBounds();
		return r.y;
	}

	public int getClipWidth() {
		java.awt.Rectangle r = awtgraphics.getClipBounds();
		return r.width;
	}
	
	public int getClipHeight() {
		java.awt.Rectangle r = awtgraphics.getClipBounds();
		return r.height;
	}
	
	public void setColor(int rgb) {
		awtgraphics.setColor( new java.awt.Color(rgb) );
	}

	public void setColor(Color col) {
		if (col == null) return;

		awtgraphics.setColor( col.awtcolor );
	}

	public int getColor() {
		return awtgraphics.getColor().getRGB();
	}
	
	public void drawImage(Image i, int x, int y) {
		awtgraphics.drawImage( i.getAWTImage(), x, y, null );
	}
	
	public void translate(int x, int y) {
		trans_x += x;
		trans_y += y;
		
		awtgraphics.translate(x,y);
	}

	public int getTranslateX() {
		return trans_x;
	}

	public int getTranslateY() {
		return trans_y;
	}
	
	public void clipRect(int x, int y, int w, int h) {
		awtgraphics.clipRect(x,y,w,h);
	}

	public void setClip(int x, int y, int w, int h) {
		awtgraphics.setClip(x,y,w,h);
	}

	public void drawLine(int x, int y, int x2, int y2) {
		awtgraphics.drawLine(x,y,x2,y2);
	}

	public void drawRect(int x, int y, int x2, int y2) {
		awtgraphics.drawRect(x,y,x2,y2);
	}

	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		awtgraphics.drawRoundRect(x,y,width,height,arcWidth,arcHeight);
	}

	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		awtgraphics.fillRoundRect(x,y,width,height,arcWidth,arcHeight);
	}
	
	public void fillRect(int x, int y, int w, int h) {
		awtgraphics.fillRect(x,y,w,h);
	}

	public void drawArc(int x, int y, int w, int h, int a, int b) {
		awtgraphics.drawArc(x,y,w,h,a,b);
	}

	public void fillArc(int x, int y, int w, int h, int a, int b) {
		awtgraphics.fillArc(x,y,w,h,a,b);
	}
	
	public void drawString(String s, int x, int y, int layout) {
		awtgraphics.drawString(s, x, y);
	}

	public void setFont(Font font) {
		// TODO
	}
}
