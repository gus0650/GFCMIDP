package gfc.microedition.lcdui.game;

import gfc.microedition.lcdui.*;

/**
 * class TiledLayer
 * 
 * Replacement of javax.microedition.lcdui.game.TiledLayer
 * 
 * @author Alex Knorr
 *  
 */
public class TiledLayer extends Layer {

    /** source image containing tiles */
    private Image sourceImage;

    /** number of static tiles in sourceImage */
    private int numStaticTiles;

    /** cell width */
    private int cellWidth;

    /** cell height */
    private int cellHeight;

    /** cell matrix */
    private int cellMatrix[];

    /** number of columns in cellMatrix */
    private int cols;

    /** number of rows in cellMatrix */
    private int rows;

    /** animated tile table */
    private int animatedToStatic[] = null;

    /** number of animated tiles */
    private int numAnimatedTiles = 0;

    /**
     * Creates a new TiledLayer.
     * 
     * The TiledLayer's grid will be rows cells high and columns cells wide. All
     * cells in the grid are initially empty (i.e. they contain tile index 0).
     * The contents of the grid may be modified through the use of setCell(int,
     * int, int) and fillCells(int, int, int, int, int).
     * 
     * The static tile set for the TiledLayer is created from the specified
     * Image with each tile having the dimensions of tileWidth x tileHeight. The
     * width of the source image must be an integer multiple of the tile width,
     * and the height of the source image must be an integer multiple of the
     * tile height; otherwise, an IllegalArgumentException is thrown.
     * 
     * @param columns -
     *            the width of the TiledLayer, expressed as a number of cells
     * @param rows -
     *            the height of the TiledLayer, expressed as a number of cells
     * @param image -
     *            the Image to use for creating the static tile set
     * @param tileWidth -
     *            the width in pixels of a single tile
     * @param tileHeight -
     *            the height in pixels of a single tile
     */
    public TiledLayer(int columns, int rows, Image image, int tileWidth,
            int tileHeight) {
        super(columns * tileWidth, rows * tileHeight);

        if (columns < 1 || rows < 1) {
            throw new IllegalArgumentException();
        }

        this.cols = columns;
        this.rows = rows;
        this.cellMatrix = new int[columns * rows];

        createStaticTiles(image, tileWidth, tileHeight);
    }

    /**
     * Setup a new static tile-set
     * 
     * @param image -
     *            the Image to use for creating the static tile set
     * @param tileWidth -
     *            the width in pixels of a single tile
     * @param tileHeight -
     *            the height in pixels of a single tile
     */
    private void createStaticTiles(Image image, int tileWidth, int tileHeight) {

        if (image == null) {
            throw new NullPointerException();
        }

        if (tileWidth < 1 || tileHeight < 1) {
            throw new IllegalArgumentException();
        }

        if ((image.getWidth() % tileWidth) != 0
                || (image.getHeight() % tileHeight) != 0) {
            throw new IllegalArgumentException();
        }

        this.sourceImage = image;
        this.numStaticTiles = (image.getWidth() / tileWidth)
                * (image.getHeight() / tileHeight);
        this.cellWidth = tileWidth;
        this.cellHeight = tileHeight;
        this.width = this.cols * tileWidth;
        this.height = this.rows * tileHeight;
    }

    /**
     * Creates a new animated tile and returns the index that refers to the new
     * animated tile. It is initially associated with the specified tile index
     * (either a static tile or 0).
     * <p>
     * The indices for animated tiles are always negative. The first animated
     * tile shall have the index -1, the second, -2, etc.
     * 
     * @param staticTileIndex -
     *            the index of the associated tile (must be 0 or a valid static
     *            tile index)
     * @return the index of newly created animated tile
     */
    public int createAnimatedTile(int staticTileIndex) {

        final int ARRAY_ADVANCE = 5;

        if (this.animatedToStatic == null) {
            this.animatedToStatic = new int[ARRAY_ADVANCE];

        } else if (this.numAnimatedTiles == this.animatedToStatic.length) {

            int newArray[] = new int[this.numAnimatedTiles + ARRAY_ADVANCE];
            System.arraycopy(newArray, 0, this.animatedToStatic, 0,
                    this.animatedToStatic.length);
            this.animatedToStatic = newArray;
        }

        this.animatedToStatic[this.numAnimatedTiles++] = staticTileIndex;

        return (-this.numAnimatedTiles);
    }

    /**
     * Fills a region cells with the specific tile. The cells may be filled with
     * a static tile index, an animated tile index, or they may be left empty
     * (index 0).
     * 
     * @param col -
     *            the column of top-left cell in the region
     * @param row -
     *            the row of top-left cell in the region
     * @param numCols -
     *            the number of columns in the region
     * @param numRows -
     *            the number of rows in the region
     * @param tileIndex -
     *            the Index of the tile to place in all cells in the specified
     *            region
     */
    public void fillCells(int col, int row, int numCols, int numRows,
            int tileIndex) {

        // check, if area is inside matrix bounds
        if (((col + numCols) > this.cols) || ((row + numRows) > this.rows)) {
            throw new IndexOutOfBoundsException();
        }

        // check for valid tile index
        if ((tileIndex > numStaticTiles) || (tileIndex < -numAnimatedTiles)) {
            throw new IndexOutOfBoundsException();
        }

        int startIndex = row * this.cols + col;
        int colPadding = this.cols - numCols;

        while (numRows-- > 0) {

            for (int columnsToGo = numCols; columnsToGo > 0; columnsToGo--) {
                cellMatrix[startIndex++] = tileIndex;
            }

            startIndex += colPadding;
        }
    }

    /**
     * Gets the tile referenced by an animated tile.
     * 
     * @param animatedTileIndex -
     *            the index of the animated tile
     * @return the index of the tile reference by the animated tile
     */
    public int getAnimatedTile(int animatedTileIndex) {

        if (animatedTileIndex >= 0 || (-animatedTileIndex) >= numAnimatedTiles) {
            throw new IndexOutOfBoundsException();
        }

        return this.animatedToStatic[-animatedTileIndex - 1];
    }

    /**
     * Gets the contents of a cell. Gets the index of the static or animated
     * tile currently displayed in a cell. The returned index will be 0 if the
     * cell is empty.
     * 
     * @param col -
     *            the column of cell to check
     * @param row -
     *            the row of cell to check
     * @return the index of tile in cell
     */
    public int getCell(int col, int row) {

        int index = row * cols + col;

        /** check, if cell is inside matrix bounds */
        if (index < 0 || index >= cellMatrix.length) {
            throw new IndexOutOfBoundsException();
        }

        return cellMatrix[index];
    }

    /**
     * Gets the width of a single cell, in pixels.
     */
    public final int getCellWidth() {
        return this.cellWidth;
    }

    /**
     * Gets the height of a single cell, in pixels.
     */
    public final int getCellHeight() {
        return this.cellHeight;
    }

    /**
     * Gets the number of columns in the TiledLayer grid.
     */
    public final int getColumns() {
        return this.cols;
    }

    /**
     * Gets the number of rows in the TiledLayer grid.
     */
    public final int getRows() {
        return this.rows;
    }

    /**
     * Draws the TiledLayer. The entire TiledLayer is rendered subject to the
     * clip region of the Graphics object. The TiledLayer's upper left corner is
     * rendered at the TiledLayer's current position relative to the origin of
     * the Graphics object. The current position of the TiledLayer's upper-left
     * corner can be retrieved by calling Layer.getX() and Layer.getY(). The
     * appropriate use of a clip region and/or translation allows an arbitrary
     * region of the TiledLayer to be rendered.
     * 
     * This method is tightly optimized for only drawing cells, that are touched
     * by the clip area in the given Graphics object.
     * 
     * @param g -
     *            Graphics object
     */
    public final void paint(Graphics g) {

        // save some fields to local var´s for speed
        int cellWidth = this.cellWidth;
        int cellHeight = this.cellHeight;
        Image sourceImage = this.sourceImage;
        int[] cellMatrix = this.cellMatrix;
        int[] animatedToStatic = this.animatedToStatic;
        int framesHorz = sourceImage.getWidth() / cellWidth;

        // translate to this layers coordinate system
        g.translate(this.x, this.y);

        // save original clip
        int cx = g.getClipX();
        int cy = g.getClipY();
        int cw = g.getClipWidth();
        int ch = g.getClipHeight();

        // calculate start index into cell-matrix
        int startI = (((cy < 0 ? 0 : cy) / cellHeight) * this.cols)
                + ((cx < 0 ? 0 : cx) / cellWidth);

        // calculate x-position, we start drawing every row
        int startX = (cx < 0) ? 0 : cx - (cx % cellWidth);

        // calculate y-position, we start drawing first row
        int runY = (cy < 0) ? 0 : cy - (cy % cellHeight);

        // calculate x,y bounds, we won´t get over
        int xBound = cx + cw;
        if (xBound > this.width) {
            xBound = this.width;
        }

        int yBound = cy + ch;
        if (yBound > this.height) {
            yBound = this.height;
        }

        // loop trough rows
        for (; runY < yBound; runY += cellHeight) {

            int i = startI;

            // loop trough columns
            for (int runX = startX; runX < xBound; runX += cellWidth) {

                // get tile index
                int tile = cellMatrix[i++];

                if (tile > 0) {
                    tile--;
                } else if (tile < 0) {
                    tile = animatedToStatic[-tile - 1];
                } else {
                    continue; // empty cell
                }

                // clip to cell area
                g.clipRect(runX, runY, cellWidth, cellHeight);

                // draw cell
                g.drawImage(sourceImage,
                        runX - (tile % framesHorz) * cellWidth, runY
                                - (tile / framesHorz) * cellHeight);

                // restore clip
                g.setClip(cx, cy, cw, ch);
            }

            startI += cols;
        }

        // backtranslate to painters coordinate system
        g.translate(-this.x, -this.y);
    }

    /**
     * Associates an animated tile with the specified static tile.
     * 
     * @param animatedTileIndex -
     *            the index of the animated tile
     * @param staticTileIndex -
     *            the index of the associated tile (must be 0 or a valid static
     *            tile index)
     */
    public void setAnimatedTile(int animatedTileIndex, int staticTileIndex) {
        
        if (animatedTileIndex >= 0 || (-animatedTileIndex) >= numAnimatedTiles) {
            throw new IndexOutOfBoundsException();
        }
        
        if (staticTileIndex < 0 || staticTileIndex > numStaticTiles) {
            throw new IndexOutOfBoundsException();
        }
        
        this.animatedToStatic[-animatedTileIndex - 1] = staticTileIndex;
    }

    /**
     * Sets the contents of a cell. The contents may be set to a static tile
     * index, an animated tile index, or it may be left empty (index 0).
     * 
     * @param col -
     *            the column of cell to set
     * @param row -
     *            the row of cell to set
     * @param tileIndex -
     *            the index of tile to place in cell
     */
    public void setCell(int col, int row, int tileIndex) {

        // check for valid tile index
        if ((tileIndex > numStaticTiles) || (tileIndex < -numAnimatedTiles)) {
            throw new IndexOutOfBoundsException();
        }

        int index = row * cols + col;

        // check, if cell is inside matrix bounds
        if (index < 0 || index >= cellMatrix.length) {
            throw new IndexOutOfBoundsException();
        }

        this.cellMatrix[index] = tileIndex;
    }

    /**
     * Change the static tile set. Replaces the current static tile set with a
     * new static tile set. See the constructor TiledLayer(int, int, Image, int,
     * int) for information on how the tiles are created from the image.
     * 
     * @param image -
     *            the Image to use for creating the static tile set
     * @param tileWidth -
     *            the width in pixels of a single tile
     * @param tileHeight -
     *            the height in pixels of a single tile
     */
    public void setStaticTileSet(Image image, int tileWidth, int tileHeight) {

        int oldNumStaticTiles = numStaticTiles;

        createStaticTiles(image, tileWidth, tileHeight);

        if (numStaticTiles > oldNumStaticTiles) {
            fillCells(0, 0, cols, rows, 0);
            numAnimatedTiles = 0;
        }
    }

    /**
     * Package internal method. Used by Sprite to perform its
     * collidesWidth(TiledLayer, boolean) method.
     * 
     * @param x -
     *            x-position of the rectangle
     * @param y -
     *            y-position of the rectangle
     * @param width -
     *            width of the rectangle
     * @param height -
     *            height of the rectangle
     * @return
     */
    boolean collidesWithRect(int x, int y, int width, int height) {

        // this layer must be visible
        if (!this.visible) {
            return (false);
        }

        // translate x,y to this layers coordinate system
        x -= this.x;
        y -= this.y;

        // calculate start index into cell-matrix
        int startI = (((y < 0 ? 0 : y) / cellHeight) * this.cols)
                + ((x < 0 ? 0 : x) / cellWidth);

        // calculate x-position, we start drawing every row
        int startX = (x < 0) ? 0 : x - (x % cellWidth);

        // calculate y-position, we start drawing first row
        int runY = (y < 0) ? 0 : y - (y % cellHeight);

        // calculate x,y bounds, we won´t get over
        int xBound = x + width;
        if (xBound > this.width) {
            xBound = this.width;
        }

        int yBound = y + height;
        if (yBound > this.height) {
            yBound = this.height;
        }

        // loop trough rows
        for (; runY < yBound; runY += cellHeight) {

            int index = startI;

            // loop trough columns
            for (int runX = startX; runX < xBound; runX += cellWidth) {

                // simply check for solid cell
                if (cellMatrix[index++] != 0) {
                    return (true);
                }
            }

            startI += cols;
        }

        return (false);
    }
}