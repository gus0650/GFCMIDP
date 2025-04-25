package gfc.tools;

import java.io.*;


public class PNGTool {

	
	
	/**
	 * Read a chunk of data from a PNG file.
	 */
	static public String getChunkHeaders( InputStream is ) {
		//System.out.println(filename);
		StringBuffer profile = new StringBuffer();
		
		int len 	= -1;
		int b;
		
		try {
			//read png-ident code
			int[] PNG_SIGNATURE = { 137, 80, 78, 71, 13, 10, 26, 10 };
			
			for( int i = 0; i < 8; i++ ) {
				b = is.read();
				if (b != PNG_SIGNATURE[i]) return ("ERROR: not a valid PNG file");
			}
			
			while( true ) {
				//determine chunk length
				len = 0;
				for( int i = 3; i >= 0; i -- ) {
					b = is.read();
					if( b == -1 ) throw new EOFException();
					
					len |= b << (i * 8);
				}
				
				//read chunk-ident string and check for match
				for( int i = 0; i < 4; i++ ) {
					b = is.read();
					if( b == -1 ) throw new EOFException();
					
					profile.append((char)b);
				}

				profile.append(" "+ len +"\n");

				//skip chunk data + crc
				is.skip(len+4);
			}
		} 
		catch (IOException e) {
			if ( e.getClass().getName() != "java.io.EOFException" ) e.printStackTrace();
		}
		
		return profile.toString();
	}
	
	
	
	static public byte[] extractChunk( InputStream is, String chunkheader ) {
		//System.out.println(filename);
		
		byte[] data = null;
		int len 	= -1;
		
		try {
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
				len += 4;	//+ checksum
				
				//read chunk-ident string and check for match
				chunk_found = true;
				
				for( int i = 0; i < 4; i++ ) {
					b = is.read();
					if( b == -1 ) throw new EOFException();
					
					if( chunkheader.charAt( i ) != b ) {
						chunk_found = false;
					}
				}

				//if chunk not found, then skip data + crc
				if (!chunk_found) is.skip(len);
			}
			
			//chunk found, so read data
			data = new byte[len];
			
			if (is.read( data, 0, len ) != len) throw new EOFException();
			
			is.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return (data);
	}
}
