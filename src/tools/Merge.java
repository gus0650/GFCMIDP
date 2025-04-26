package gfc.tools;

import java.io.*;
import java.util.*;

public class Merge {

	static final String HELP = 
		"Usage: merge <target file> <list of source files>\n" +
		"Purpose: merges a set of files into a single file\n" +
		"If list of source files contains directories, these will be recursed " +
		"into, and all subdirectories and files contained therein will be processed.";

	public static void main(String[] args) {
		if (args.length < 2) throw new IllegalArgumentException(HELP);
		
		String[] files;
		if (args.length == 2) {
			//single file or directory specified
			files = FileToolkit.allFilesIn(args[1]);
		}
		else {
			//more than 1 source file specified
			Vector v = new Vector();
			
			for (int i = 1; i < args.length; i++) {
				String[] morefiles = FileToolkit.allFilesIn(args[i]);

				for (int j = 0; j < morefiles.length; j++) {
					v.add(morefiles[j]);
					
					System.out.println(morefiles[j]);
				}
			}
			
			files = new String[v.size()];
			v.copyInto(files);
		}

		//process files
		int r;
		r = concat( args[0], files );

		if (r == 0) System.exit(-1); //terminate with error code if no files were processed
	}
	
	
	static int concat( String target_filename, String[] source_filenames ){
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
				
				target.write( b );
					
				source.close();
			}
			
			target.close();
			
			System.out.println("*** done. " + i + " files merged.");
			
			return i;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
