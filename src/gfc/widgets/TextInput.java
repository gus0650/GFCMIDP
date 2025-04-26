package gfc.widgets;

import gfc.microedition.lcdui.*;
import gfc.graphics.*;


/**
 * A UI component that lets the user enter a short text.<p>
 * 
 * A field of [numChars] characters is shown.
 * The selectNext() and selectPrevious() methods, the user can select one of these characters
 * for manipulation.
 * Using the increase() and decrease() methods, the user can then
 * cycle through the letters of the alphabet for the selected character.
 * When finished, the getString() method will return the compound String 
 * that the user has thus entered.<p>
 * 
 * (You may have seen this kind of entering a string in the Hiscore Lists 
 * of many arcade games.)
 * 
 * @author Gustaf Stechmann
 */
public final class TextInput extends Panel {

	private byte[] 	sequence;
	private byte 	first_char  = 0x41;
	private byte 	last_char  	= 0x5A;
	private byte 	empty_char  = 0x20; //space

	static String ARROW_UP 		= "+";
	static String ARROW_DOWN 	= "-";
	
	
	public TextInput(int x, int y, Displayable d, Font font, String text, int maxSize, Color backgroundColor) {
		super(x, y, d);

		sequence = new byte[maxSize];

		for (int i = 0; i < sequence.length; i++) {
			super.append( new Label( 0, 0, d, "", font, backgroundColor ) );
		}

		setString(text);
		
		/*config:debug*///System.out.println("TextInput()");
	}
	
	
	public void append(Widget w) {
		/*config:debug*///System.out.println("TextInput.append()");
		
		System.out.println("TextInput.append() ERROR -- cant append items to an existing TextInput widget");
	}
	
	
	public void increase() {
		/*config:debug*///System.out.println("TextInput.increase()");

		sequence[p_selected_index]--;
		if (sequence[p_selected_index] < first_char) sequence[p_selected_index] = last_char;

		setElement( getSelectedIndex(), new String(sequence, p_selected_index, 1) );
	}

	
	public void decrease() {
		/*config:debug*///System.out.println("TextInput.decrease()");

		sequence[p_selected_index]++;
		if (sequence[p_selected_index] > last_char) sequence[p_selected_index] = first_char;

		setElement( getSelectedIndex(), new String(sequence, p_selected_index, 1) );
	}

	
	/**
	 * @return the String that the user has entered
	 */
	public String getString() {
		return new String(sequence);
	}

	
	public void setString(String text) {
		/*config:debug*///System.out.println("TextInput.setString()");

		if (text.length() > sequence.length) System.err.println("TextInput() ERROR: text is longer than maxSize");
		
		for (int i = 0; i < sequence.length; i++) {
			if (i >= text.length()) sequence[i] = empty_char;
			else sequence[i] = (byte)text.charAt(i);
			
			setElement( i, new String(sequence, i, 1) );
		}
		
		setLayout(Layout.createTableLayout(sequence.length, 1));
	}

	
	private void setElement(int index, String c) {
		/*config:debug*///System.out.println("TextInput.setElement()" + index);

		Label l = (Label)p_widgets.elementAt(index);
		
		l.setString( new String(ARROW_UP + "\n"+ c +"\n" + ARROW_DOWN) );
	}
}
