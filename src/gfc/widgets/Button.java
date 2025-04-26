package gfc.widgets;

import gfc.microedition.lcdui.*;
import gfc.graphics.*;


/**
 * Like a Label, except that it is selectable.
 * 
 * @author Gustaf Stechmann
 */
public class Button extends Label {

	public Button(int x, int y, Displayable d, String text, Font font, Color backgroundColor) {
		super( x, y, d, text, font, backgroundColor );

		setSelectable(true);
	}
}
