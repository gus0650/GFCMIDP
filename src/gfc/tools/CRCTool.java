package gfc.tools;

import java.io.*;

public class CRCTool {

	public static void main(String[] args) {
		//if (args.length < 1 || args.length > 1) throw new IllegalArgumentException(HELP);
		
		byte[] test = null;
		byte[] data = null;
		String source_filename 	= args[0];

		try {
			//get data from file
			System.out.println("extracting palette data chunk from file " + source_filename + "... ");

			File source_file 	= new File(source_filename);
			FileInputStream fis = new FileInputStream( source_file );
			
			data 				= PNGTool.extractChunk( fis, "PLTE" );
			fis.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		test = new byte[data.length-4];

		System.out.println( calculateCRC32(test) );

		int b, cs = 0;
		for( int i = 3; i >= 0; i -- ) {
			b = data[data.length-4+i];
			
			cs |= b << (i * 8);
		}
		System.out.println( cs );
	}
	
	
	static byte calculateCRC32(byte[] in) {
		int i, j;
		int len = in.length;
		
		byte out = (byte) 0xC7;// bit-swapped 0xE3;

		for (j = 0; j < len; j++) {
			out = (byte) (out ^ (in[j] & 0xFF));
			for (i = 0; i < 8; i++) {
				if ((out & 0x80) != 0)
					out = (byte) ((out << 1) ^ 0x1D);
				else
					out = (byte) (out << 1);
			}
		}
		
		return out;
	}
}
