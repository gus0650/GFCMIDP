package gfc.tools;

import java.io.*;

public class Touch {

	static final String HELP = 
		"Usage: touch <path>\n" +
		"Purpose: update the 'file last modified' date stamp with the current system time.\n" +
		"If arg is a filename, this single file will be processed. " +
		"If arg is a directory, program will recurse into this directory and all subdirectories and" +
		"process the directories and the files contained therein.";

	public static void main(String[] args) {
		if (args.length < 1 || args.length > 1) throw new IllegalArgumentException(HELP);
		
		int r = touch( 
					FileToolkit.allFilesIn(args[0]) );
			
		if (r > 0) System.exit(0);
		else System.exit(-1);
	}
	
	
	static int touch(String[] filenames) {
		long time = System.currentTimeMillis();

		int i;
		for (i = 0; i < filenames.length; i++) {
			File file = new File(filenames[i]);
			boolean touched = file.setLastModified(time);

			if (touched)
				System.out.println("touched file '" + filenames[i] + "'");
			else
				System.err.println("touch failed on file '" + filenames[i] + "'");
		}
		
		System.out.println("*** done. " + i + " files were touched.");
		
		return i;
	}
}
