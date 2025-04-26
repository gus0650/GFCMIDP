package gfc.microedition.lcdui;

//DEPRECATE -> midlet properties

final public class DeviceSettings {
	public static final String DEVICE 			= "WTK.Generic";

	public static final String SLASH 			= "/";

	public static final int AUDIO_CHANNELS		= 0;
	
	public static final int SCREEN_WIDTH 		= 176;
	public static final int SCREEN_HEIGHT 		= 208;
	
	public static final int KEY_UP 				= -1;
	public static final int KEY_DOWN 			= -2;
	public static final int KEY_LEFT 			= -3;
	public static final int KEY_RIGHT 			= -4;
	public static final int KEY_FIRE 			= -5;
	public static final int KEY_SOFTLEFT 		= -6;
	public static final int KEY_SOFTRIGHT 		= -7;

	public static Image IMG_BG 					= null;
	public static Image IMG_ITEM 				= null;

	public static Font FONT 					= Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL);
	public static Font FONT_BOLD 				= Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_SMALL);

	public static int COL_BG 					= 0x000000;
	public static int COL_TEXT 					= 0xffffff;
	public static int COL_HEADER_BG				= 0xcccccc;
	public static int COL_HEADER_TEXT			= 0x222222;
	public static int COL_BOXHI 				= 0x888888;
	public static int COL_BOXLO 				= 0x444444;
	
	public static int MARGINV 					= 1;
	
	public static int PADDINGV 					= 2;
	public static int PADDINGH 					= 2;

	public static int BORDERV 					= 24;
	public static int BORDERH 					= 32;

	public static int ROUNDH 					= 16;
	public static int ROUNDV 					= 16;
	
}
