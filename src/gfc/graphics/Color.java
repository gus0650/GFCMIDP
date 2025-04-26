package gfc.graphics;

public class Color {

	public java.awt.Color awtcolor;

	public Color(int r, int g, int b) {
		awtcolor = new java.awt.Color(r,g,b);
	}

	public Color(int rgb) {
		awtcolor = new java.awt.Color(rgb);
	}
	
	public int getRGB() {
		return awtcolor.getRGB();
	}
}
