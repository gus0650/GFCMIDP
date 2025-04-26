package gfc.microedition.lcdui;

import gfc.widgets.Icon;


/**
 * @see javax.microedition.lcdui.ImageItem
 */
//TODO
public class ImageItem extends Item {

	private Icon	ii_icon;
	private String 	ii_alt_text;
	private int 	ii_layout;

	
	public ImageItem( String label, Image img, int layout, String altText ) {
		super(label);
		
		ii_layout	= layout;
		ii_alt_text = altText;
		
		setImage(img);
	}

	
	public Image getImage() {
		return ii_icon.getImage();
	}


	public void setImage( Image img ) {
		ii_icon = new Icon( 
				0, 0, 
				getScreen(), 
				img 
			);
		
		setWidget(ii_icon);
	}


	public int getLayout() {
		return ii_layout;
	}


	public void setLayout( int layout ) {
		ii_layout = layout;
		//TODO
		
		System.err.println("WARNING -- ImageItem.setLayout() not yet implemented");
	}
}