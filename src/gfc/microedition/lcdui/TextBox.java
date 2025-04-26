package gfc.microedition.lcdui;

import gfc.microedition.midlet.*;

/**
 * @see javax.microedition.lcdui.TextBox
 * @author Gustaf Stechmann
 * @version alpha
 */

public class TextBox extends Screen {

	public static final Command OK_COMMAND = new Command(MIDlet.GetAppProperty("TEXT_OK"), Command.OK, 0 );

	private static TextField 	tb_textfield;
	
	
	public TextBox(String title, String text, int maxSize, int constraints) {
		super(title);
		
		tb_textfield 	= new TextField( "", text, maxSize, constraints );
		
		setContent(tb_textfield);
		
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("TextBox()");
	}
	
	public String getString() {
		return tb_textfield.getString();
	}

	public void setString(String s) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("TextBox.setString()");

		tb_textfield.setString(s);
		
		posHome();
	}
	
	public int getConstraints() {
		return tb_textfield.getConstraints();
	}
}
