package gfc.widgets;

import gfc.microedition.lcdui.*;
import gfc.graphics.*;

import java.util.Vector;


//TODO: Horiz, Vert
//TODO: padding

/**
 * 
 * class Layout
 * 
 * @author Alex Knorr
 * 
 * Created on 25.10.2004
 */
public final class Layout {

    /** Layout types */
    private static final int TABLE = 0;
    private static final int TABLE_HORZ = 1;
    private static final int TABLE_VERT = 2;
    private static final int STACK = 3;

    /** type of the Layout */
    private int type;

    /** global stack-layout class */
    private static Layout stackLayout = null;

    /** Layout parameters depending on <code>type</code> */
    private int param1;

    private int param2;

    /**
     * Various Layout creators
     */
    public static Layout createTableLayout(int columns, int rows) {
        return new Layout(TABLE, columns, rows);
    }

    public static Layout createHorzTableLayout(int columns) {
        return new Layout(TABLE_HORZ, columns, 0);
    }

    public static Layout createVertTableLayout(int rows) {
        return new Layout(TABLE_HORZ, 0, rows);
    }

    public static Layout createStackLayout() {
        if (stackLayout == null) {
            stackLayout = new Layout(STACK, 0, 0);
        }

        return stackLayout;
    }

    /**
     * Construct a new Layout
     * 
     * @param type -
     *            type of Layout
     * @param param1 -
     *            meaning depends on <code>type</code>
     * @param param2 -
     *            meaning depends on <code>type</code>
     * @param param3 -
     *            meaning depends on <code>type</code>
     */
    private Layout(int type, int param1, int param2) {

        this.type = type;
        this.param1 = param1;
        this.param2 = param2;
    }

    /**
     * Return the Layout-Type
     */
    public int getType() {
        return type;
    }

    /**
     * Layout a list of Widgets. Widgets are positioned depending on the type of
     * this Layout.
     * 
     * @param widgets -
     *            list of widgets.
     */
    public void layoutWidgets(Vector widgets) {
        switch (type) {
        case TABLE:
        case TABLE_HORZ:
        case TABLE_VERT:
            tableLayout(widgets);
            break;
        case STACK:
            stackLayout(widgets);
            break;
        }
    }

    /**
     * Position Widgets in a Table.
     * <p>
     * Meaning of Layout parameters:
     * <p>
     * <code>param1</code>- max number of columns. <code>param2</code>-
     * max number of rows. <code>param3</code>- padding between table cells.
     */
    private void tableLayout(Vector widgets) {

        int nWidget = widgets.size();
        int iWidget = 0;
        int xExtend = 0;
        int yExtend = 0;

        // loop trough rows
        int rows = 0;
        for (; ((type == TABLE_HORZ) || (rows < param2)) && (iWidget < nWidget); rows++) {

            int rowOffset = iWidget; // save start index of this row
            int rowHeight = 0;

            // loop trough columns
            for (int col = 0; ((type == TABLE_VERT) || (col < param1))
                    && (iWidget < nWidget); col++) {

                Widget w = (Widget) widgets.elementAt(iWidget);

                // find out highest Widget in current row
                if (w.getHeight() > rowHeight) {
                    rowHeight = w.getHeight();
                }

                if (rows > 0) {
                    // get x from Widget at column in first row
                    w.setPosition(((Widget) widgets.elementAt(col)).getX(),
                            yExtend);

                } else {
                    w.setPosition(xExtend, yExtend);

                    // find out width of current column in this first row
                    int colWidth = 0;

                    for (int j = col; j < widgets.size(); j += param1) {
                        int width = ((Widget) widgets.elementAt(j)).getWidth();

                        if (width > colWidth) {
                            colWidth = width;
                        }
                    }

                    xExtend += colWidth;
                }

                iWidget++; // step to next widget in list
            }

            // align rows > 0
            if ((rows > 0) && (rowOffset < iWidget)) {

                for (; rowOffset < (iWidget - 1); rowOffset++) {
                    Widget w = (Widget) widgets.elementAt(rowOffset);
                    alignWidgetToRect(w, w.getX(), w.getY(), ((Widget) widgets
                            .elementAt(rowOffset + 1)).getX()
                            - w.getX(), rowHeight);
                }

                Widget w = (Widget) widgets.elementAt(rowOffset);
                alignWidgetToRect(w, w.getX(), w.getY(), xExtend - w.getX(),
                        rowHeight);
            }

            yExtend += rowHeight;
        }

        // align first row
        if (nWidget > 0) {

            // height of first row
            int rowHeight = (rows > 1) ? ((Widget) widgets.elementAt(param1))
                    .getX() : yExtend;

            iWidget = 0;
            for (; ((type == TABLE_VERT) || (iWidget < (param1 - 1)))
                    && iWidget < nWidget; iWidget++) {
                Widget w = (Widget) widgets.elementAt(iWidget);
                alignWidgetToRect(w, w.getX(), w.getY(), ((Widget) widgets
                        .elementAt(iWidget + 1)).getX()
                        - w.getX(), rowHeight);
            }

            Widget w = (Widget) widgets.elementAt(iWidget);
            alignWidgetToRect(w, w.getX(), w.getY(), xExtend - w.getX(),
                    rowHeight);
        }
    }

    /**
     * Stack widgets one on another.
     * <p>
     * At first a rectangle of (0, 0, longest_widget_width,
     * highest_widget_height) is assumed. All widgets are then aligned to this
     * rectangle.
     */
    private void stackLayout(Vector widgets) {

        int xExtend = 0;
        int yExtend = 0;

        // find out horz. & vert. extension of the stack
        for (int i = widgets.size() - 1; i >= 0; i--) {

            Widget w = (Widget) widgets.elementAt(i);

            if (w.getWidth() > xExtend) {
                xExtend = w.getWidth();
            }
            if (w.getHeight() > yExtend) {
                yExtend = w.getHeight();
            }
        }

        // align all Widgets to rectangle (0, 0, xExtend, yExtend)
        for (int i = widgets.size() - 1; i >= 0; i--) {
            alignWidgetToRect((Widget) widgets.elementAt(i), 0, 0, xExtend,
                    yExtend);
        }
    }

    /**
     * Align a Widget to a rectangle.
     * 
     * @param w -
     *            Widget to align
     * @param x -
     *            x position of rectangle to align to
     * @param y -
     *            y position of rectangle to align to
     * @param width -
     *            width of rectangle to align to
     * @param height -
     *            height of rectangle to align to
     */
    private void alignWidgetToRect(Widget w, int x, int y, int width, int height) {

        int alignment = w.getAlignment();

        if ((alignment & Graphics.HCENTER) != 0) {
            x = x + (width / 2 - w.getWidth() / 2);
        } else if ((alignment & Graphics.RIGHT) != 0) {
            x = x + width - w.getWidth();
        }

        if ((alignment & Graphics.VCENTER) != 0) {
            y = y + (height / 2 - w.getHeight() / 2);
        } else if ((alignment & Graphics.BOTTOM) != 0) {
            y = y + height - w.getHeight();
        }

        w.setPosition(x, y);
    }
}