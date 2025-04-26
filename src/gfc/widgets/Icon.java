package gfc.widgets;

import gfc.microedition.lcdui.*;


/**
 * A Widget that holds an Image.
 * 
 * @author Gustaf Stechmann
 */
public class Icon extends Widget {

	private Image image;
	
	
	public Icon(int x, int y, Displayable d, Image img) {
		super(img.getWidth(), img.getHeight(), x, y, d);
		
		image = img;
	}

	
	public void setImage(Image img) {
		image = img;
		
		setContentWidth(img.getWidth());
		setContentHeight(img.getHeight());
	}

	
	public Image getImage() {
		return image;
	}

	
        @Override
	public void draw( Graphics g ) {
		g.drawImage(image, 0, 0, 0);
	}
}
