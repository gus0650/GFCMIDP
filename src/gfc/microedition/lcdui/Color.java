package gfc.microedition.lcdui;

public class Color {

	java.awt.Color awtcolor;

	public Color(int rgb) {
		awtcolor = new java.awt.Color(rgb);
	}
	
	public int getRGB() {
		return awtcolor.getRGB();
	}
}
