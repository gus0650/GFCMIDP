package gfc.microedition.lcdui.game;

import gfc.microedition.lcdui.*;


/**
 * class Layer
 * 
 * Replacement of javax.microedition.lcdui.game.Layer
 * 
 * @author Alexander Knorr
 * 
 * Created on 13.10.2004
 */
public abstract class Layer /**extends javax.microedition.lcdui.game.Layer*/{

    /** x-position of the layer */
    int x = 0;

    /** y-position of the layer */
    int y = 0;

    /** width of the layer */
    int width;

    /** height of the layer */
    int height;

    /** visibility of the layer */
    boolean visible = true;
    
    /**
     * Package Constructor
     * 
     * @param width - width of the new layer
     * @param height - height of the new layer
     */
    Layer(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    /**
     * Gets the current width of this layer, in pixels.
     * 
     * @return
     */
    public final int getWidth() {
        return width;
    }

    /**
     * Gets the current height of this layer, in pixels.
     * 
     * @return
     */
    public final int getHeight() {
        return height;
    }

    /**
     * Gets the horizontal position of this Layer's upper-left
     * corner in the painter's coordinate system.
     * 
     * @return
     */
    public final int getX() {
        return x;
    }

    /**
     * Gets the vertical position of this Layer's upper-left
     * corner in the painter's coordinate system.
     * 
     * @return
     */
    public final int getY() {
        return y;
    }

    /**
     * Gets the visibility of this Layer.
     * 
     * @return
     */
    public final boolean isVisible() {
        return visible;
    }

    /**
     * Moves this Layer by the specified horizontal and vertical distances.
     * 
     * @param dx
     * @param dy
     */
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Paint the Layer
     * 
     * @param g
     */
    public abstract void paint(Graphics g);

    /**
     * Sets this Layer's position such that its upper-left corner is
     * located at (x,y) in the painter's coordinate system.
     * 
     * @param x
     * @param y
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the visibility of this Layer.
     * 
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}