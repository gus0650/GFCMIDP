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
	
	
	boolean keyPressedPreprocess(int keyCode) {
		boolean intercepted = super.keyPressedPreprocess(keyCode);
		
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("TextBox.keyPressedPreprocess()");

		if (false) {}
		else if (keyCode == Canvas.KEY_NUM5 | keyCode == DisplayProperties.KEY_TRIGGER) {
			CommandListener cl = d_command_listener;
			if (cl != null) cl.commandAction(OK_COMMAND, this);
		}
		else if (keyCode == Canvas.KEY_NUM2 | getGameAction(keyCode) == Canvas.UP ) {
			tb_textfield.increase();
		}
		else if (keyCode == Canvas.KEY_NUM8 | getGameAction(keyCode) == Canvas.DOWN ) {
			tb_textfield.decrease();
		}
		else if (keyCode == Canvas.KEY_NUM4 | getGameAction(keyCode) == Canvas.LEFT ) {
			tb_textfield.selectPrevious();
		}
		else if (keyCode == Canvas.KEY_NUM6 | getGameAction(keyCode) == Canvas.RIGHT ) {
			tb_textfield.selectNext();
		}
		
		repaint();//TODO -- widgets should repaint internally!
		
		return intercepted;
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
