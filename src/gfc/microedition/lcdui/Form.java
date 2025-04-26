package gfc.microedition.lcdui;

/**
 * @see javax.microedition.lcdui.Form
 * @author Gustaf Stechmann
 * @version pre-alpha
 */

public class Form extends Screen {

	private ItemStateListener 	f_item_state_listener;
	private ItemGroup			f_item_group;

	
	public Form( String title ) {
		super(title);

		f_item_group = new ItemGroup( null );
		
		setContent(f_item_group);
	}

	
	public int append( Item item ) {
		item.setScreen(this);
		
		f_item_group.append( item );

		return 1;
	}

	
	public int append( String s ) {
		StringItem si = new StringItem( "none", s );
		
		append( si );
		
		posHome();
		
		return 1;
	}


	public int append( Image img ) {
		ImageItem ii = new ImageItem( "none", img, 0, "none" );
		
		append( ii );

		posHome();
		
		return 1;
	}


	public void delete( int item_id ) {
		f_item_group.delete(item_id);
		
		posHome();
	}


	public void insert( int item_id, Item item ) {
		f_item_group.insert(item_id, item);
		
		posHome();
	}


	public void set( int item_id, Item item ) {
		f_item_group.set(item_id, item);
		
		posHome();
	}


	public void setItemStateListener( ItemStateListener item_state_listener ) {
		f_item_state_listener = item_state_listener;
	}


	public int size() {
		return f_item_group.size();
	}
	
/*	
	boolean keyPressedPreprocess(int keyCode) {
		//
		return false;
	}
	
	void keyReleasedPreprocess(int keyCode) {
		//
	}
	
	void keyRepeatedPreprocess(int keyCode) {
		//
	}
	
	void pointerDraggedPreprocess(int x, int y) {
		//
	}
	
	void pointerPressedPreprocess(int x, int y) {
		//
	}
	
	void pointerReleasedPreprocess(int x, int y) {
		//
	}
	
	void hideNotifyPreprocess() {
		//
	}
	
	void showNotifyPreprocess() {
		//
	}
	*/
}