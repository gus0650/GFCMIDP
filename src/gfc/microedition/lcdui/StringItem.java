package gfc.microedition.lcdui;

import gfc.widgets.*;
import gfc.util.StringTool;


/**
 * @see javax.microedition.lcdui.StringItem
 * @author Gustaf Stechmann
 */
public class StringItem extends Item {

	private Label	si_text;
	
	
	public StringItem(String label, String text) {
		super(label);
		
		si_text	= new Label(
				0, 
				0, 
				getScreen(), 
				null, 
				Display.getGFCFont(), 
				Display.getPassiveColor() 
			);
		
		setWidget(si_text);
		
		setText(text);
	}
	
	
	public String getText() {
		return si_text.getString();
	}
	
	
	public void setText(String text) {
		//adjust text to fit screen width
		String ftext = StringTool.formatJustify( text, si_text.getFont().charsPerLine( Display.getDisplayWidth() - Display.getDefaultBorderHMargin() *2 ) , false );	//TODO all this border business is somewhat inconsistent
		
		si_text.setString(ftext);
	}
}
