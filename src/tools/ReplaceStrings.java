package gfc.tools;

public class ReplaceStrings {

	static final String HELP = 
		"Usage: package <token> <sourcefile> <targetfile>\n" +
		"Purpose: replace all occurences of <token> string in <sourcefile> with contents of <sourcefile>.";
	
	public static void main(String[] args) {
		if (args.length < 1 || args.length > 2) throw new IllegalArgumentException(HELP);

		int r = replaceTokens( 
				args[0], 
				args[1],
				args[2] );
		
		if (r > 0) System.exit(0);
		else System.exit(-1);
	}
	
	
	static int replaceTokens(String token, String source_filename, String target_filename) {
		StringBuffer target = FileToolkit.readFromFile(target_filename);
		StringBuffer source = FileToolkit.readFromFile(source_filename);

		System.out.print(
				"replacing all occurences of '" + token
				+ "' in file '" + target_filename
				+ "' with contents of file '" + source_filename + "'" );

		int i;
		for (i = 0; target.indexOf(token) >= 0; target.insert(i, source)) {
			i = target.indexOf(token);
			target.delete(i, i + token.length());
		}

		FileToolkit.writeToFile(target, target_filename);

		System.out.print("*** done.");

		return i;
	}
}
