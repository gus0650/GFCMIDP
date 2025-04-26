package gfc.graphics;

/**
 * An object that holds and manages a set of colors.<p>
 * 
 * For use with ImageTool.
 * 
 * NOT FULLY IMPLEMENTED
 * 
 * @author Gustaf Stechmann
 */
public class Palette {

	byte[] colors;
	int capacity;
	
	public Palette(int capacity) {
		this.capacity = capacity;

		colors = new byte[capacity];
	}
	
	
	/**
	 * @return - the number of colors in this Palette
	 */
	public int numColors() {
		return capacity;
	}

	
	/**
	 * @return - the Color at the specified index
	 */
	public Color getColor(int index) {
		if ((index > capacity) | (index < 0)) System.err.println("ERROR in Palette: illegal index value "+ index);

		return new Color(colors[index]);
	}

	
	/**
	 * @return - the color value at the specified index
	 */
	public byte getColorValue(int index) {
		if ((index > capacity) | (index < 0)) System.err.println("ERROR in Palette: illegal index value "+ index);

		return colors[index];
	}


	/**
	 * Set the Color at the specified index
	 */
	public void setColor(int index, Color c) {
		if ((index > capacity) | (index < 0)) System.err.println("ERROR in Palette: illegal index value "+ index);

		colors[index] = (byte)c.getRGB();
	}

	
	/**
	 * Set the color value at the specified index
	 */
	public void setColor(int index, byte colorvalue) {
		if ((index > capacity) | (index < 0)) System.err.println("ERROR in Palette: illegal index value "+ index);
		
		colors[index] = colorvalue;
	}
}
