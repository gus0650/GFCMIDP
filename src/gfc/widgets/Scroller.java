package gfc.widgets;

import gfc.microedition.lcdui.*;
import gfc.graphics.*;


/**
 * Implements a "ticker-tape", ie. a piece of text that runs continuously across
 * the Widget.
 * 
 * @author Alex Knorr
 */
public class Scroller extends Widget {

    private String	string;			// string to scroll    
    private Font	font;			// font for drawing the ticker-text
    private int		scrollPos = 0;	// string scroll position
    private int		step;			// scroll step (in pixels)

    
    /**
     * Create a Ticker with custom width and height.
     * 
     * @param step -
     *            	number of pixels scrolled each time scroll() is called 
     * 				(also defines the direction - negative value means backwards).
     */
    public Scroller(int width, int height, int x, int y, Displayable d, String text, Font font, Color backgroundColor, int step) {
        super(width, height, x, y, d);

        this.step = step;
        this.string = text;
        this.font = font;
        setBGColor(backgroundColor);
    }

    
    /**
     * Create a Ticker, that has the initial height of the text
     */
    public Scroller(int width, int x, int y, Displayable d, String text, Font font, Color backgroundColor, int speed) {
        this(width, font.getHeight(), x, y, d, text, font, backgroundColor, speed);
    }

    
    /**
     * Create a Ticker, that has the initial width and height of the given text
     */
    public Scroller(int x, int y, Displayable d, String text, Font font,
            Color backgroundColor, int speed) {
        this(font.stringWidth(text), font.getHeight(), x, y, d, text, font, backgroundColor, speed);
    }

    
    /**
     * Sets the string to be displayed by this ticker.
     * If this ticker is active and is on the display, it immediately
     * begins showing the new string.
     * 
     * @param text -
     *            string to be set for the Ticker.
     */
    public void setString(String text) {
        if (string == null) {
            new NullPointerException();
        }

        scrollPos = 0;
        string = text;
        repaint();
    }

    
    /**
     * Gets the string currently being scrolled by the ticker.
     */
    public String getString() {
        return (string);
    }

    
    /**
     * Draw the scroller text.
     * 
     * If Scroller Widget is wider than the current text, the text is repeated
     * until it fills out the whole length.
     */
    void draw(Graphics g) {

        int w = font.stringWidth(string);
        int x = (-w + (scrollPos % w)) % w;

        for (;x < getWidth(); x += w) {
            g.drawString(string, x, 0, 0);
        }
    }

    
    /**
     * This moves the text of the scroll forward/backward with "step" - width
     */
    public void scroll() {
        scrollPos += step;
    }
}