package gfc.tools;

import java.io.*;

public class Package {
	
	static final String HELP = 
		"Usage: package <sourcepath> <targetfile>\n" +
		"Purpose: merges a set of files into a single file, " +
		"writing the size of each file chunk before the beginning of the chunk as an integer value.\n" +
		"If arg is a filename, this single file will be processed. " +
		"If arg is a directory, program will recurse into this directory and all subdirectories and" +
		"process the directories and the files contained therein.";
	
	public static void main(String[] args) {
		if (args.length < 1 || args.length > 2) throw new IllegalArgumentException(HELP);
		
		int r = concatWithIndex( 
					FileToolkit.allFilesIn(args[0]),
					args[1]	);
		
		if (r > 0) System.exit(0);
		else System.exit(-1);
	}
	
	
	static int concatWithIndex( String[] source_filenames, String target_filename ){
		RandomAccessFile source;
		RandomAccessFile target;

		//merge files
		try {
			System.out.println("creating file: " + target_filename);
			
			File f = new File(target_filename);
			f.createNewFile();

			target = new RandomAccessFile(target_filename, "rw");
			
			int i;
			for (i = 0; i < source_filenames.length; i++) {
				String source_filename = source_filenames[i];
				
				System.out.println("processing file: " + source_filename);
				
				source = new RandomAccessFile(source_filename, "r");
				int len = (int)source.length();
				
				byte[] b = new byte[ len ];
				source.readFully( b );
				
				target.writeInt( len );
				target.write( b );
					
				source.close();
			}
			
			target.close();
			
			System.out.println("*** done. " + i + " files packaged.");
			
			return i;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
