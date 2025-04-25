package gfc.util;

import java.io.*;


/**
 * Utility class that provides static methods for various i/o operations.
 */
final public class IOTool {
	
	
	public static void loadFile(String filename, byte[] into) {
		//System.out.println("FileTool.load() " + filename);
		
		try {
			DataInputStream dis;
			
			dis = new DataInputStream( into.getClass().getResourceAsStream( filename ) );
			dis.readFully(into);
			
			dis.close();
		}
		catch (IOException e) {
			/*config:debug:ON*/e.printStackTrace();
		}
	}

	public static void loadFile(String filename, byte[] into, int skip) {
		//System.out.println("FileTool.load() " + filename);
		
		try {
			DataInputStream dis;
			
			dis = new DataInputStream( into.getClass().getResourceAsStream( filename ) );
			
			dis.skip(skip);
			
			dis.read(into);
			
			dis.close();
		}
		catch (IOException e) {
			/*config:debug:ON*/e.printStackTrace();
		}
	}

	/*
	public static byte[] load(String filename) {
		
		byte[] into = null;
		
		try {
			DataInputStream in;
			
			in = new DataInputStream( into.getClass().getResourceAsStream( filename ) );
			
			into = new byte[ in.available() ];

			in.readFully(into);
			
			in.close();
		}
		catch (IOException e) {
			config:debug:ON e.printStackTrace();
		}
		
		return into;
	}
	*/
	
	public static int getFileSize(String filename) {
		//System.out.println("FileTool.getFileSize() " + filename);
		
		long fs = 0;

		try {
			InputStream is;

			is = filename.getClass().getResourceAsStream( filename );

			fs = is.skip(Long.MAX_VALUE);

			is.close();
		}
		catch (IOException e) {
			/*config:debug:ON*/e.printStackTrace();
		}
		
		return (int)fs;
	}
	
	
	/**
	 * Test if a file exists.
	 */
	public static boolean existsFile(String filename) {
    	try {
			InputStream is;

			is = filename.getClass().getResourceAsStream( filename );
			is.close();
    		
    		return true;
    	}
    	catch (Exception e) {
        	return false;
    	}
	}

	
	/**
	 * Read a chunk of data from a file.<p>
	 * 
	 * The data format of a chunk is 
	 * <li>int - length of chunk
	 * <li>byte[] - chunk data.<p>
	 * 
	 * This kind of file can be generated with the GFCFileConcat tool.
	*/
	public static byte[] readChunk(InputStream is, int chunkNum) {
		int len;
		byte[] data = null;

		try {
			for (int i = 0; i < chunkNum; i++) {
				len = is.read();
				is.skip(len);
			}
			
			len = is.read();
			data = new byte[len];
			
			is.read(data);
		}
		catch (Exception e) {
			/*config:debug:ON*/e.printStackTrace();
		}

		return data;
	}
	
	
	public static byte[] readChunk(String filename, int chunkNum) {
		byte[] data = null;
		
		try {
			InputStream in;
			
			in = filename.getClass().getResourceAsStream( filename );
			data = readChunk(in, chunkNum);
			in.close();
		}
		catch (Exception e) {
			/*config:debug:ON*/e.printStackTrace();
		}
		
		return data;
	}

}
