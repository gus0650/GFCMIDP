package gfc.microedition.lcdui;


/**
 * @author Gustaf Stechmann
 */
public interface Choice {

	public final static int	
		EXCLUSIVE	= 1, 
		IMPLICIT 	= 2, 
		MULTIPLE 	= 3;


	int append( String stringPart, Image imagePart );

	void delete( int elementNum );

	Image getImage( int elementNum );

	int getSelectedFlags( boolean[] selectedArray_return );

	int getSelectedIndex();

	String getString( int elementNum );

	void insert( int elementNum, String stringPart, Image imagePart );

	boolean isSelected( int elementNum );

	void set( int elementNum, String stringPart, Image imagePart );

	void setSelectedFlags( boolean[] selectArray );

	void setSelectedIndex( int elementNum, boolean selected );

	int size();
}