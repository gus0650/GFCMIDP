package gfc.widgets;

import gfc.graphics.*;
import gfc.microedition.lcdui.*;
import gfc.util.StringTool;


/**
 * A Widget displaying a short text.
 * 
 * @author Gustaf Stechmann
 */
public class Label extends Widget {

	String 		text = "";
	Font 		font;
	boolean		dynamic;
	Color		bg;


	/**
	 * Constructs a new Label, the dimensions (width, height) of which will be automatically
	 * adjusted to fit the text.
	 * 
	 * @param text - the label text
	 * @param font - the font to be used
	 */
	public Label(int x, int y, Displayable d, String text, Font font, Color backgroundColor) {
		super( 0, 0, x, y, d );
		
		this.font 			= font;
		this.dynamic		= true;
		setBGColor( backgroundColor );

		bg = backgroundColor;
		
		setString(text);
	}

	
	/**
	 * Constructs a new Label with fixed dimensions.
	 **/
	public Label(int width, int height, int x, int y, Displayable d, String text, Font font, Color backgroundColor) {
		super( width, height, x, y, d );
		
		this.font 			= font;
		this.dynamic		= false;
		setBGColor( backgroundColor );

		bg = backgroundColor;

		setString(text);
	}
	
	@Override
	void draw(Graphics g) {
		g.drawString( text, 0, 0, 0 );
	}


	public void setString(String s) {
		if (s == null) s = " ";
		
		boolean length_changed = ( s.length() != text.length() );
		
		text = s;
		
		if ( dynamic && length_changed ) {
			//adjust the dimensions so that the entire content is shown
			setContentWidth  (font.stringWidth( StringTool.longestSubstring(text, '\n')  ) );
			setContentHeight (font.getHeight() * (StringTool.countOccurences(text, '\n') + 1) );
		}
		
		repaint();
	}

	public String getString() {
		return text;
	}
	
	public Font getFont() {
		return font;
	}

	@Override
    protected void notifyDeselected() {
    	setBGColor( bg );
    }

	@Override
    protected void notifySelected() {
    	setBGColor( new Color(0xcccccc) );
    }
}
