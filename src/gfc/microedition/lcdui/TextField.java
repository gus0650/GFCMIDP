package gfc.microedition.lcdui;

import gfc.widgets.*;


/**
 * Note: unlike the original MIDP implementation,
 * the number of characters stored in GFC TextField is constant.
 * It is determined by the length of the text String passed into the contructor.
 * Thus, size() and getMaxSize() always return identical values.
 * 
 * 
 * @see javax.microedition.lcdui.TextField
 * @author Gustaf Stechmann
 */
//TODO
public class TextField extends Item {

	public final static int	
		ANY				= 0, 
		EMAILADDR 		= 1, 
		NUMERIC 		= 2, 
		PHONENUMBER 	= 3, 
		URL 			= 4, 
		PASSWORD 		= 0x10000, 
		CONSTRAINT_MASK = 0xFFFF;

	private int			tf_constraints		= 0;
	private int 		tf_max_size			= 3;
	private TextInput 	tf_selector;


	public TextField( String label, String text, int maxSize, int constraints ) {
		super(label);

		tf_max_size 	= maxSize;
		tf_constraints 	= constraints;
		
		tf_selector = new TextInput( 0, 0, getScreen(), DisplayProperties.getFont(), text, maxSize, DisplayProperties.getPassiveColor() );
		tf_selector.setAlignment(Graphics.HCENTER);		
		tf_selector.selectFirst();

		setWidget(tf_selector);
		
		/*config:debug*///System.out.println("TextField()");
	}

	
	void increase() {
		/*config:debug*///System.out.println("TextField.increase()");
		
		tf_selector.increase();
	}
	
	void decrease() {
		/*config:debug*///System.out.println("TextField.decrease()");

		tf_selector.decrease();
	}

	void selectPrevious() {
		/*config:debug*///System.out.println("TextField.selectPrevious()");

		tf_selector.selectPrevious();
	}

	void selectNext() {
		/*config:debug*///System.out.println("TextField.selectNext()");

		tf_selector.selectNext();
	}


	public void delete( int offset, int length ) {
		//TODO
		System.err.println("TextField.delete() -- method not implemented");
	}


	public int getCaretPosition() {
		//TODO
		System.err.println("TextField.getCaretPosition() -- method not implemented");

		return -1;
	}


	public int getChars( char[] data ) {
		//TODO
		System.err.println("TextField.getChars() -- method not implemented");

		return -1;
	}


	public int getConstraints() {
		return tf_constraints;
	}


	public int getMaxSize() {
		return tf_max_size;
	}


	public String getString() {
		return (tf_selector.getString());
	}


	public void insert( char[] data, int offset, int length, int position ) {
		//TODO
		System.err.println("TextField.insert() -- method not implemented");
	}


	public void insert( String src, int position ) {
		//TODO
		System.err.println("TextField.insert() -- method not implemented");
	}


	public void setChars( char[] data, int offset, int length ) {
		//TODO
		System.err.println("TextField.setChars() -- method not implemented");
	}


	public void setConstraints( int constraints ) {
		tf_constraints = constraints;
	}


	public int setMaxSize( int maxSize ) {
		tf_max_size = maxSize;
		return tf_max_size;
	}


	public void setString( String text ) {
		tf_selector.setString( text );
	}


	public int size() {
		return tf_max_size;
	}
}