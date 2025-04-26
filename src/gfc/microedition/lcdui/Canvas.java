
package gfc.microedition.lcdui;

/**
 * @see javax.microedition.lcdui.Canvas
 * @author Gustaf Stechmann
 */
public abstract class Canvas extends Displayable {

	public final static int
		UP    		= 1,
		DOWN   		= 6,
		LEFT  		= 2,
		RIGHT 		= 5,
		FIRE   		= 8,
		GAME_A 		= 9,
		GAME_B 		= 10,
		GAME_C 		= 11,
		GAME_D 		= 12,
		KEY_NUM0 	= 48,
		KEY_NUM1 	= 49,
		KEY_NUM2 	= 50,
		KEY_NUM3 	= 51,
		KEY_NUM4 	= 52,
		KEY_NUM5 	= 53,
		KEY_NUM6 	= 54,
		KEY_NUM7 	= 55,
		KEY_NUM8 	= 56,
		KEY_NUM9 	= 57,
		KEY_STAR  	= 42,
		KEY_POUND 	= 35;


	@Override
	public abstract void paint(Graphics g);

	
	public boolean hasPointerEvents() {
		return true;
	}
	
	public boolean hasPointerMotionEvents() {
		return true;
	}
	
	public boolean hasRepeatEvents() {
		return false;
	}
	
	public boolean isDoubleBuffered() {
		return true; //double buffering is handled by AWT
	}
	
	public void keyPressed(int keyCode) {}
	
	public void keyReleased(int keyCode) {}
	
	
	@Override
	public void pointerPressed(int x, int y) {}
	
	public void pointerReleased(int x, int y) {}
	
	@Override
	public void hideNotify() {}
	
	@Override
	public void showNotify() {}


	//--- keys

	public int getGameAction(int keyCode) {
		if (display == null) return -1;
		
		if (keyCode == KEY_POUND) 	return GAME_A;
		if (keyCode == KEY_STAR) 	return GAME_B;
		if (keyCode == KEY_NUM0) 	return GAME_C;
		if (keyCode == KEY_NUM9) 	return GAME_D;
		if (keyCode == KEY_NUM2) 	return UP;
		if (keyCode == KEY_NUM8) 	return DOWN;
		if (keyCode == KEY_NUM4) 	return LEFT;
		if (keyCode == KEY_NUM6) 	return RIGHT;
		if (keyCode == KEY_NUM5) 	return FIRE;

		return -1;
	}
	
	public int getKeyCode(int gameAction) {
		if (display == null) return -1;

		if (gameAction == GAME_A) 	return KEY_POUND;
		if (gameAction == GAME_B) 	return KEY_STAR;
		if (gameAction == GAME_C) 	return KEY_NUM0;
		if (gameAction == GAME_D) 	return KEY_NUM9;
		if (gameAction == UP) 		return KEY_NUM2;
		if (gameAction == DOWN) 	return KEY_NUM8;
		if (gameAction == LEFT) 	return KEY_NUM4;
		if (gameAction == RIGHT) 	return KEY_NUM6;
		if (gameAction == FIRE) 	return KEY_NUM5;

		return -1;
	}
	
	public String getKeyName(int keyCode) {
		if (display == null) return null;
		else return getKeyName(keyCode);
	}
}