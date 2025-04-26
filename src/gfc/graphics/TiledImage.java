package gfc.graphics;

import gfc.microedition.lcdui.*;


/**
 * Handles a bitmap image that consists of multiple tiles of same size.
 * 
 * @author Gustaf Stechmann
 */

public class TiledImage {

	protected Image image;
	protected int tile_height, tile_width, tiles_horiz, tiles_vert, tiles;

	public TiledImage(Image image, int width, int height) {
		/*config:debug:OFF*///System.out.println("TiledImage()");

		this.image = image;

		tile_width 	= width;
		tile_height = height;

		tiles_horiz = image.getWidth()  / tile_width;
		tiles_vert 	= image.getHeight() / tile_height;
		
		tiles = tiles_horiz * tiles_vert;
	}
	
	public void paintTile(Graphics g, int tile, int x, int y) {
		if ( (tile > tiles) | (tile < -1) ) {
			System.err.println("ERROR in TiledImage.paintTile(): tile index out of range " + tile );
			return;
		} 

		if (tile == -1) return;

		//store clip
		int clip_x  	= g.getClipX();
		int clip_y 		= g.getClipY();
		int clip_width  = g.getClipWidth();
		int clip_height = g.getClipHeight();

		//set
		g.clipRect( x, y, tile_width, tile_height );

		//calculate tile's position within the tileset image
		int current_tile_y = tile / tiles_horiz;
		int current_tile_x = tile - current_tile_y * tiles_horiz;

		//draw tile
		g.drawImage(image, x - current_tile_x * tile_width, y - current_tile_y * tile_height);
		
		//restore clip
		g.setClip( clip_x, clip_y, clip_width, clip_height );
	}
	
	public int numTiles() {
		return tiles;
	}
	
	public int tileWidth() {
		return tile_width;
	}
	
	public int tileHeight() {
		return tile_height;
	}

	public Image getImage() {
		return image;
	}

	public Image getTileImage(int tile) {
		/*config:debug:OFF*///System.out.println("TiledImage.getTileImage() " + tile);
		
		if (tile > tiles) System.err.println("ERROR in TiledImage.getTileImage(): tile ID out of range -- " + tile);
		
		Image nimg = Image.createImage(tile_width, tile_height);
		
		Graphics g = nimg.getGraphics();

		paintTile(g, tile, 0, 0);
		
		return nimg;
	}
}
