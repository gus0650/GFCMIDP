package gfc.tools;

import java.io.*;
import java.util.Vector;

public class FileToolkit {

	public static String[] allFilesIn(String path) {
		Vector v = new Vector();

		walk(path, v);

		String[] filenames = new String[v.size()];
		v.copyInto(filenames);
		
		return filenames;
	}


	public static void walk(String filename, Vector v) {
		v.add(filename);

		File f = new File(filename);
		if (f.isDirectory()) {
			String list[] = f.list();
			for (int i = 0; i < list.length; i++)
				walk(filename + "/" + list[i], v);
		}
	}
	

	public static StringBuffer readFromFile(String filename) {
		StringBuffer sb = new StringBuffer();
		System.out.print("reading from file " + filename + "\n");
		try {
			RandomAccessFile file = new RandomAccessFile(filename, "r");
			String s;
			while ((s = file.readLine()) != null)
				sb.append(s + "\n");
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb;
	}

	
	public static void writeToFile(StringBuffer sb, String filename) {
		System.out.print("writing to file " + filename + "\n");
		try {
			RandomAccessFile file = new RandomAccessFile(filename, "rw");
			file.writeBytes(sb.toString());
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean existsFile(String f) {
		File file = new File(f);

		return (file.exists());
	}
}
