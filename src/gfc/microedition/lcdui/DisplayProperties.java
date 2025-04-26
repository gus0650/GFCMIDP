package gfc.microedition.lcdui;

import gfc.graphics.*;

//TODO support ROUND and RECT widget backgrounds

/**
 * Hold default settings for the Display.<p>
 * 
 * The application must initialize this static class with purposeful data 
 * before any high-level UI class from the package can be used!<p>
 * 
 * Replacing the variables on the constants provided in this class
 * also allows customization of the default appearance and behavior of the UI.<p>
 *
 * @author Gustaf Stechmann
 */
public class DisplayProperties {

	//constants
	public static final int 
		BOX_RECT			= 1,
		BOX_ROUND			= 2;
	
	//key codes (default values = WTK)
	//...
	
	//key codes (values = com.nokia.mid.ui.FullCanvas)
	public static int 
		KEY_UP_ARROW 	= -1,
		KEY_DOWN_ARROW 	= -2,
		KEY_LEFT_ARROW 	= -3,
		KEY_RIGHT_ARROW = -4,
		KEY_SEND 		= -10,
		KEY_END 		= -11,
		KEY_SOFTKEY_L 	= -6,
		KEY_SOFTKEY_R 	= -7,
		KEY_TRIGGER 	= -5; //click joystick
	
	//ticker
	public static int 			TICKER_SPEED		= 1;

	//command texts
	public static String 		TEXT_DISMISS 		= "DISMISS";
	public static String 		TEXT_MORE 			= "MORE";
	public static String 		TEXT_OK 			= "OK";
	public static String 		TEXT_SELECT			= "SELECT";
	public static String 		TEXT_MENU_OPEN		= "MENU...";
	public static String 		TEXT_MENU_CLOSE		= "CLOSE";

	//mandatory parameters
	private static int 			screen_width;
	private static int 			screen_height;
	private static Font 		font;
	private static Color		col_passive;
	private static Color		col_active;
	private static Color		col_hilight;
	private static Image 		menuitem_inactive;
	private static Image 		menuitem_active;
	
	//optional parameters
	private static int 			border_v_margin		= 2;
	private static int 			border_h_margin		= 2;
	private static int 			box_style			= BOX_RECT;

	public static Image			SCREEN_BACKGROUND;
	
	
	public static void configure(
			int screenWidth, 
			int screenHeight,
			Font f, 
			Image menuitemInactive,
			Image menuitemActive,
			Color passive, 
			Color active, 
			Color hilight ) {
		
		screen_width		= screenWidth;
		screen_height		= screenHeight;
		font 				= f;
		menuitem_active 	= menuitemActive;
		menuitem_inactive 	= menuitemInactive;
		col_passive			= passive;
		col_active			= active;
		col_hilight			= hilight;
	}

	
	/**
	 * @param borderMarginH
	 * @param borderMarginV
	 * @param boxStyle - either BOX_ROUND or BOX_RECT
	 */
	public static void configureDecoration(
			int borderMarginH,
			int borderMarginV,
			int boxStyle ) {
		
		border_h_margin 	= borderMarginH;
		border_v_margin 	= borderMarginV;
		box_style			= boxStyle;
		
		if (boxStyle != BOX_RECT) System.err.println("DisplayProperties.configureDecoration() -- currently only ROUND_RECT is supported"); 
	}
	
	
	public static Font getFont() {
		return font;
	}
	
	public static int getScreenHeight() {
		return screen_height;
	}
	
	public static int getScreenWidth() {
		return screen_width;
	}
	
	public static Image getDefaultMenuitemInactive() {
		return menuitem_inactive;
	}
	
	public static Image getDefaultMenuitemActive() {
		return menuitem_active;
	}

	public static Color getPassiveColor() {
		return col_passive;
	}

	public static Color getActiveColor() {
		return col_active;
	}

	public static Color getHilightColor() {
		return col_hilight;
	}

	public static int getDefaultBorderHMargin() {
		return border_h_margin;
	}

	public static int getDefaultBorderVMargin() {
		return border_v_margin;
	}
}
