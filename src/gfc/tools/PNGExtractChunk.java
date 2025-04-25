package gfc.tools;

import java.io.*;


/**
 * extract a data chunk from one or more PNG files
 * 
 * arguments: <palette identifier> <list of source files><p>
 * 
 * palette identifier e.g. 'PLTE'
 */
public class PNGExtractChunk {

	public static void main(String[] args) {
		String chunk_id 		= args[0];
		

		for (int a = 1; a < args.length; a++) {
			String source_filename 	= args[a];
			String target_filename 	= args[a] + "." + chunk_id;

			try {
				//get data from file
				System.out.println("extracting palette data chunk from file " + source_filename + "... ");
	
				File source_file 	= new File(source_filename);
				FileInputStream fis = new FileInputStream( source_file );
				
				byte[] data 		= PNGTool.extractChunk( fis, chunk_id );
				fis.close();
	
				//write data to file
				System.out.println("writing to new file " + target_filename + "... ");
				
				File target_file = new File(target_filename);
				target_file.createNewFile();
				
				FileOutputStream fos = new FileOutputStream( target_file );
	
				fos.write(data);
				fos.close();
	
				//done
				System.out.println("done");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
