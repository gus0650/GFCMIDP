package gfc.util;

import gfc.microedition.midlet.*;
import gfc.microedition.lcdui.*;

import java.io.*;


/**
 * Manipulate the palette of an image.
 * 
 * @author Alex Knorr
 */
public class ImageColorizer {
	
	/** color channel id for red */
	static public final int RED = 0;
	
	/** color channel id for green */
	static public final int GREEN = 1;
	
	/** color channel id for blue */
	static public final int BLUE = 2;
	
	/** range of values for internal color-calculations */
	static public int VALUE_RANGE = 100;
	
	
	/** filename of current image */
	private String png_file;

	/** png data array */
	private byte png_data[] = null;
	
	/** actual data position, when reading chunks */
	private int png_data_pos = 0;
	
	/** current position in data */
	private int png_pal_pos = 0;
	
	/** number of different colors in palette (each color is represented by 3 bytes) */
	private int png_pal_colors = 0;
	
	
	/**
	 * Construct an image
	 */
	public ImageColorizer( String filename, int filesize ) {
		loadSource( filename, filesize );
	}

	
	public ImageColorizer( String filename ) {
		loadSource( filename );
	}

	
	public ImageColorizer() {
	}

	
	/**
	 * Return the number of palette-colors in current source image
	 * 
	 * @return int - number of colors in palette.
	 */
	public final int getNumColors() {
		
		return( this.png_pal_colors );
	}
	
	
	/**
	 * Set color of at specific palette index
	 * 
	 * @param index color index in palette
	 * @param rgb rgb color to set (0x00rrggbb)
	 */
	public final void setColor( int index, int rgb ) {
		
		int pos = this.png_pal_pos + (index * 3);
		
		byte png_data[] = this.png_data;
		
		png_data[ pos++ ] = (byte)((rgb >> 16) & 0xff);
		png_data[ pos++ ] = (byte)((rgb >>  8) & 0xff);
		png_data[ pos++ ] = (byte)((rgb      ) & 0xff);
	}
	
	
	/**
	 * Get color of at specific palette index
	 * 
	 * @param index color index in palette
	 * @return int color value (0x00rrggbb)
	 */
	public final int getColor( int index ) {
		
		int pos = this.png_pal_pos + (index * 3);
		int rgb = 0;
		
		byte png_data[] = this.png_data;
		
		rgb |= png_data[ pos++ ] << 16;
		rgb |= png_data[ pos++ ] << 8;
		rgb |= png_data[ pos++ ];
		
		return( rgb );
	}
	
	
	/**
	 * Search for a given color in the palette and replace with another color
	 *  
	 * @param search_color color to search for
	 * @param replace_color color to replace with
	 */
	public final void searchAndReplaceColor( int search_color, int replace_color ) {
		
		search_color &= 0x00ffffff;
		
		int png_pal_colors = this.png_pal_colors;
		
		for( int i = 0; i < png_pal_colors; i++ ) {
			
			if( getColor( i ) == search_color ) {				
				setColor( i, replace_color );
			}
		}
	}
	
	
	/**
	 * Set image back to it�s original palette
	 */
	public final void originalPalette() {
		
		try {
			InputStream is = getClass().getResourceAsStream( this.png_file );

			/* skip to palette chunk */
			is.skip( this.png_pal_pos );
			/* read palette into png-data */
			is.read( this.png_data, this.png_pal_pos, this.png_pal_colors * 3 );			
			is.close();
		
		} catch (IOException e) {
			/*config:debug:ON*/e.printStackTrace();
		}
	}
	
	
	/**
	 * Change the brightness of the current image.
	 * This method performs an color blend to black, if brightness
	 * is less than 0, or to white, if brightness is greater than 0.
	 * 
	 * @param brightness brightness value (-VALUE_RANGE ~ VALUE_RANGE)
	 */
	public final void changeBrightness( int brightness ) {
		
		if( brightness < 0 ) {
			
			blendToColor( 0x00000000, -brightness );
		} else {
			blendToColor( 0x00ffffff,  brightness );
		}
	}
	
	
	/**
	 * Change the intensity of a given color channel.
	 * 
	 * @param channel color channel to change (RED, GREEN, BLUE)
	 * @param intensity intensity value (-VALUE_RANGE ~ VALUE_RANGE)
	 */
	public final void changeChannelIntensity( int channel, int intensity ) {
		
		if( intensity < 0 ) {
			
			blendChannelToValue( channel, 0x00, -intensity );
		} else {
			blendChannelToValue( channel, 0xff,  intensity );
		}
	}
	
	
	/**
	 * Blend current image to a given color
	 * 
	 * @param color color to blend image to
	 * @param blend blend value (0 no blending ~ VALUE_RANGE full blending)
	 */
	public final void blendToColor( int color, int blend ) {
		
		/* save png_data to local var for performance */
		byte png_data[] = this.png_data;
		
		/* loop trough color blocks */
		int pos = this.png_pal_pos;
		
		for( int i = this.png_pal_colors; i > 0; i -- ) {

			for( int j = 2; j >= 0; j -- ) {
				
				int c = (png_data[ pos ] & 0xff);
				int p = (color >> j * 8) & 0xff;
				
				c += ((p - c) * blend) / VALUE_RANGE;
				if( c > 255 ) {
					c = 255;
				} else
				if( c < 0 ) {
					c = 0;
				}
				
				png_data[ pos++ ] = (byte)c;
			}	
		}
	}
	
	
	/**
	 * Blend a color channel of current image to a given value.
	 * 
	 * @param channel color channel to blend to
	 * @param value value to blend channel to
	 * @param blend blend value (0 no blending ~ VALUE_RANGE full blending)
	 */
	public final void blendChannelToValue( int channel, int value, int blend ) {
		
		/* save png_data to local var for performance */
		byte png_data[] = this.png_data;
		
		/* loop trough color part of each block */
		int pos = this.png_pal_pos + channel;
		
		for( int i = this.png_pal_colors; i > 0; i -- ) {
				
			int c = (png_data[ pos ] & 0xff);
			
			c += ((value - c) * blend) / VALUE_RANGE;
			if( c > 255 ) {
				c = 255;
			} else
			if( c < 0 ) {
				c = 0;
			}
			
			png_data[ pos ] = (byte)c;
			pos += 3;
		}
	}
	
	
	/**
	 * Invert current image.
	 */
	public final void invert() {
		
		int end_pos = (this.png_pal_pos + (this.png_pal_colors*3));
		
		/* save png_data to local var for performance */
		byte png_data[] = this.png_data;
		
		/* loop trough all color bytes */
		for( int i = this.png_pal_pos; i < end_pos; i++ ) {

			png_data[ i ] = (byte)(255 - (png_data[ i ] & 0xff));	
		}
	}
	
	
	/**
	 * Create an image in it�s current state.
	 *
	 * @return Image - image of current state
	 */
	public final Image currentImage() {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("ImageColorizer.currentImage()");
		
		return Image.createImage( png_data, 0, png_data.length );
	}
	
	
	/**
	 * Load a new source image.<p>
	 * 
	 * This method is provided for compatibility ith devices where InputStream.available()
	 * does not work -- such as Nokia Series 60 V1.
	 * 
	 * @param filename - name of the image resource
	 * @param filesize - size of the image file in bytes
	 */
	public final void loadSource( String filename, int filesize ) {		
		//System.out.println("ImageColorizer.loadSource(): " + filename + " " + filesize);
		
		try {
			InputStream is = getClass().getResourceAsStream( filename );
			
			/* set new file-name */
			png_file = filename;
		
			/* load the stream */
			loadSourceFromStream( is, filesize );
			is.close();
		} 
		catch (IOException e) {
			/*config:debug:ON*/e.printStackTrace();
		}
	}
	
	
	/**
	 * Load a new source image
	 * 
	 * @param filename - name of the image resource
	 */
	public final void loadSource( String filename ) {		
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("ImageColorizer.loadSource(): " + filename );
		
		try {
			InputStream is = getClass().getResourceAsStream( filename );
			
			/* set new file-name */
			png_file = filename;
			
			/* load the stream */
			loadSourceFromStream( is, is.available() );
			is.close();
		} 
		catch (IOException e) {
			/*config:debug:ON*/e.printStackTrace();
		}
	}
	
	
	/**
	 * Load an image from an InputStream source
	 * 
	 * @param is - InputStream to load from
	 * @param size - final size of the resource in <code>is<code>
	 */
	private final void loadSourceFromStream( InputStream is, int size ) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("ImageColorizer.loadSourceFromStream() " + size);
		
		try {
			
			/* allocate data array for png data */
			this.png_data = new byte[ size ];
			
			/* reset data pointer */
			this.png_pal_pos = this.png_data_pos = 0;
			
			/* try finding PLTE chunk & check for palette */
			if( (this.png_pal_colors = forwardToChunk( is, true, "PLTE" ) / 3) > 0 ) {
				
				/* set palette start-position in png_data */
				this.png_pal_pos = this.png_data_pos;
							
				/* read in rest of file-data */
				is.read( this.png_data, this.png_data_pos, this.png_data.length - this.png_data_pos );
			
			} else {
				
				/* no palette found */
				/*config:debug:ON*/System.err.println("ERROR in ImageColorizer: Image '" + this.png_file + "' doesn�t contain a palette chunk!");
			}
		} 
		catch (IOException e) {	
			/*config:debug:ON*/e.printStackTrace();
		}
	}
	
	
	/**
	 * Forward data position to the data section of given chunk
	 * 
	 * @param chunk - name of the chunk to forward to
	 * @param read_in - indicate of data should be stored or discarded
	 * 
	 * @return length of palette chunk
	 */
	private final int forwardToChunk( InputStream is, boolean read_in, String chunk ) {
		try {
			
			int b;
			
			/* read or skip png-ident code, if not already done */
			if( this.png_data_pos < 8 ) {
				
				if( read_in ) {
					is.read( this.png_data, this.png_data_pos, 8 - this.png_data_pos );
				} else {
					is.skip( 8 - this.png_data_pos );
				}
				
				this.png_data_pos += ( 8 - this.png_data_pos );
			}

			/* loop trough chunks */
			for( ;; ) {	
	
				/* read chunk length */
				int chunk_len = 0;
				
				for( int i = 3; i >= 0; i -- ) {

					if( (b = is.read()) == -1 ) {
						return ( 0 );
					}
										
					chunk_len |= b << (i * 8);
					
					if( read_in ) {
						this.png_data[ this.png_data_pos ] = (byte)b;
					}
					
					this.png_data_pos ++;
				}
	
				/* read chunk-ident string and check for match */
				boolean is_chunk = true;
				
				for( int i = 0; i < 4; i++ ) {
					
					if( (b = is.read()) == -1 ) {
						return ( 0 );
					}
					
					if( read_in ) {
						png_data[ this.png_data_pos ] = (byte)b;
					}
					
					if( chunk.charAt( i ) != b ) {
												
						is_chunk = false;
					}

					this.png_data_pos ++;
				}
				
				if( is_chunk ) {

					return( chunk_len ); /* chunk found, pointing to it�s first byte of data */		
				}
				
				/* skip or read data & crc */
				if( read_in ) {
					
					if( is.read( this.png_data, this.png_data_pos, chunk_len + 4 ) < (chunk_len + 4) ) {
						return ( 0 );						
					}
				} else {
					
					is.skip( chunk_len + 4 );
				}
				
				this.png_data_pos += chunk_len + 4;
			}

		} catch (IOException e) {
			/*config:debug:ON*/e.printStackTrace();
		}
		
		return( 0 );
	}
	
	
	/**
	 * Reads a single chunk from a PNG file.
	 * 
	 * @param filename - file to read from
	 * @param chunkheader - chunk identifier string (eg "PLTE")
	 * 
	 * @return the chunk data, sans heading identifier string
	 * 
	 * @throws IOException if data could not be read as expected
	 */
	static public byte[] readChunk( String filename, String chunkheader ) {
		//System.out.println(filename);
		
		byte[] data = null;
		int len 	= -1;
		
		try {
			//open file
			InputStream is = filename.getClass().getResourceAsStream( filename );
			
			//skip png-ident code
			is.skip( 8 );
			
			//go through chunks until the wanted chunk is encountered
			boolean chunk_found = false;
			
			while( !chunk_found ) {	
				int b;
				
				//determine chunk length
				len = 0;
				for( int i = 3; i >= 0; i -- ) {
					b = is.read();
					if( b == -1 ) throw new EOFException();
					
					len |= b << (i * 8);
				}
				len += 4;
				
				//read chunk-ident string and check for match
				chunk_found = true;
				
				for( int i = 0; i < 4; i++ ) {
					b = is.read();
					if( b == -1 ) throw new EOFException();
					
					if( chunkheader.charAt( i ) != b ) {
						chunk_found = false;
					}
				}

				//chunk not found, so skip data + crc
				if (!chunk_found) is.skip(len);
			}
			
			//chunk found, so read data
			data = new byte[len];
				
			if (is.read( data, 0, len ) != len) throw new EOFException();
				
			is.close();
		} 
		catch (IOException e) {
			/*config:debug:ON*/e.printStackTrace();
		}
		
		return (data);
	}
	
	
	/**
	 * Load palette from a file containing binary data.<p>
	 * 
	 * The palette file must contain "getNumColors()" - blocks
	 * of three bytes (R,G,B) for each color entry in the palette.<p>
	 * 
	 * @param filename resource name of palette file
	 * @return Image - resulting image after palette change
	 */
	public final void loadPaletteFromPLTEFile( String filename ) {
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("ImageColorizer.loadPaletteFromPLTEFile() " + filename);
		
		try {
			InputStream is = getClass().getResourceAsStream( filename );
			
			//read palette into png-data
			is.read( this.png_data, this.png_pal_pos, this.png_pal_colors*3+4 );			
			is.close();
		
		} catch (IOException e) {
			/*config:debug:ON*/e.printStackTrace();
		}
	}
	
	
	/**
	 * Load palette from another image file.
	 * The given image file must have the same palette as the current source image.
	 * 
	 * TODO: This doesn�t work properbly when mixing colored and grayscaled images.
	 * 
	 * @param filename resource name of image file
	 */
	public final void loadPaletteFromPNGFile( String filename ) {		
		if (MIDlet.GetAppProperty("debug").equals("true")) System.out.println("ImageColorizer.loadPaletteFromPNGFile() " + filename);

		try {
			InputStream is = getClass().getResourceAsStream( filename );
			
			/* reset data position */
			this.png_data_pos = 0;
			
			/* get number of palette colors */
			int png_pal_colors = forwardToChunk( is, false, "PLTE" ) / 3;
			
			/* check for match */
			if( png_pal_colors >= this.png_pal_colors ) {
				
				if( png_pal_colors > this.png_pal_colors ) {
					
					/* palettes don�t match */
					/*config:debug:ON*/System.err.println("WARNING in ImageColorizer: Palette of image '" + filename + "' has more colors than '" + this.png_file + "'! " + png_pal_colors + " > " + this.png_pal_colors);
				}
				
				/* read in new palette */
				is.read( this.png_data, this.png_pal_pos, (this.png_pal_colors * 3) + 4 );
				
			} else
			if( png_pal_colors == 0 ) {	
				
				/* image has no palette */
				/*config:debug:ON*/System.err.println("ERROR in ImageColorizer: Image '" + filename + "' doesn�t contain a palette!");
				
			} else			
			if( png_pal_colors < this.png_pal_colors ) {
				
				/* palettes doesn�t match */
				/*config:debug:ON*/System.err.println("ERROR in ImageColorizer: Palette of image '" + filename + "' has less colors than '"
				/*config:debug:ON*/	+ this.png_file + "'! " + png_pal_colors + " < " + this.png_pal_colors);
			}
			
			is.close();
			
		} catch( IOException e ) {
			/*config:debug:ON*/e.printStackTrace();
		}
	}
	
	void recalcCRC() {
		//TODO -- re-calculate checksum for PLTE chunk after changeBrightness() etc.
	}
}
