package gfc.microedition.lcdui.game;

import gfc.microedition.lcdui.*;

import java.util.Vector;


/**
 * class LayerManager
 * 
 * Replacement of javax.microedition.lcdui.game.LayerManager
 * 
 * @author Alexander Knorr
 * 
 * Created on 13.10.2004
 */
public class LayerManager {

    /** layer components */
    private Vector component = new Vector();

    /** x-position of view window */
    private int viewX = 0;

    /** x-position of view window */
    private int viewY = 0;

    /** width of view window */
    private int viewWidth = 1024;

    /** height of view window */
    private int viewHeight = 1024;

    /**
     * Append a layer to this layer manager
     * 
     * @param l -
     *            Layer to append
     */
    public void append(Layer l) {
        if (component.indexOf(l) != -1) {
            component.removeElement(l);
        }

        component.addElement(l);
    }

    /**
     * 
     * @param index
     * @return
     */
    public Layer getLayerAt(int index) {
        return (Layer) component.elementAt(index);
    }

    /**
     * 
     * @param l
     * @param index
     */
    public void insert(Layer l, int index) {
        if (component.indexOf(l) != -1) {
            component.removeElement(l);
        }

        component.insertElementAt(l, index);
    }

    /**
     * 
     * @return
     */
    public int getSize() {
        return component.size();
    }

    /**
     * 
     * @param g
     * @param x
     * @param y
     */
    public void paint(Graphics g, int x, int y) {

        int cx = g.getClipX();
        int cy = g.getClipY();
        int cw = g.getClipWidth();
        int ch = g.getClipHeight();

        g.clipRect(x, y, viewWidth, viewHeight);
        g.translate(viewX, viewY);

        for (int i = component.size() - 1; i >= 0; i--) {
            ((Layer) component.elementAt(i)).paint(g);
        }

        g.translate(-viewX, -viewY);
        g.setClip(cx, cy, cw, ch);
    }

    /**
     * 
     * @param l
     */
    public void remove(Layer l) {
        component.removeElement(l);
    }

    /**
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void setViewWindow(int x, int y, int width, int height) {

        viewX = x;
        viewY = y;
        viewWidth = width;
        viewHeight = height;
    }
}