package gfc.tools;

import java.io.*;


public class PNGAnalyze {

	public static void main(String[] args) {
		String filename = args[0];
		
		try {
			File file;
			FileInputStream fis;
			
			System.out.println("analyzing file " + filename);
			
			file = new File(filename);
			fis = new FileInputStream( file );
			
			System.out.println( PNGTool.getChunkHeaders( fis ) );
			
			fis.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		System.exit(0);
	}
}
