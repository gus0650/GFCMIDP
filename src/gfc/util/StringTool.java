package gfc.util;


/**
 * Provides various stratic utility methods for String operations.
 */
public final class StringTool {

	public static final char WHITESPACE = 32;
	
	
	/** 
	 * Find the longest substring (ie, a substring terminated by a specified delimiter) 
	 * inside the String s.
	 * 
	 * @param str - string to search
	 * @param delimiter - character code (10 = line break)
	 */
	public final static String longestSubstring( String str, int delimiter ) {
		
		int longest_starts = -1;
		int longest_length = -1;
		
		int s = 0; /* start index */
		int e = 0; /* end index */
		
		for( ;; ) {
			
			int l;

			if( (e = str.indexOf( delimiter, s )) == -1 ) {

				if( (l = str.length() - s) > longest_length ) {
				
					longest_starts = s;
					longest_length = l;
				}
				
				break;
			}

			if( (l = e - s) > longest_length ) {
				
				longest_starts = s;
				longest_length = l;
			}
			
			s = e + 1;
		}

		return ( longest_length >= 0 ? str.substring( longest_starts, longest_starts + longest_length ) : str );
	}
	
	
	/** 
	 * Finds the length of the longest substring (ie, a substring terminated by a specified delimiter) 
	 * inside the String s.
	 * 
	 * @param str - string to search
	 * @param delimiter - character code (10 = line break)
	 */
	public final static int longestSubstringLength( String str, int delimiter ) {
		
		int longest_length = -1;
		
		int s = 0; /* start index */
		int e = 0; /* end index */
		
		for( ;; ) {
			
			int l;

			if( ( e = str.indexOf( delimiter, s ) ) == -1 ) {

				if( (l = str.length() - s) > longest_length ) {
				
					longest_length = l;
				}
				
				break;
			}

			if( (l = e - s) > longest_length ) {
				
				longest_length = l;
			}
			
			s = e + 1;
		}

		return ( longest_length >= 0 ? longest_length : str.length() );
	}
	
	
	/**
	 * Fill up the left side of the String with the specfied filler String.
	 * 
	 * StringBuffer.append() is stricly used insted of insert(), because
	 * of efficient and fast string appending in the JVM implementation
	 * of StringBuffer. An insert() would cause an additional move operation
	 * of the string right of the insert()'s index every time called.
	 * 
	 * @param str - the original String
	 * @param fill - the String with which to fill up the left side of the new String
	 * @param length - total length of the resulting String
	 * @return a String that consists of multiple copies of <code>fill</code> on the left,
	 * and is concluded by <code>string</code>.
	 */
	public final static String fillLeft( String str, String fill, int length ) {
		
		/* allocate fixed-size buffer */
		StringBuffer sb = new StringBuffer( length );
		
		
		/* check for appending fragment of filler */
		int l = (length - str.length()) % fill.length();
		if( l > 0 ) {
		
			sb.append( fill.substring( fill.length() - l ) );
			
			length -= l;
		}				

		/* append full fillers */
		while( length >= ( str.length() + fill.length() ) ) {
			
			sb.append( fill );
			
			length -= fill.length();
		}
		
		/* append original string (full or fragmented) */
		sb.append( ( str.length() <= length ) ? str : str.substring( str.length() - length ));

		return sb.toString();
	}


	/**
	 * Fill up the right side of the String with the specfied filler String.
	 * 
	 * @param str - the original String
	 * @param fill - the String with which to fill up the right side of the new String
	 * @param length - total length of the resulting String
	 * @return a String that consists of <code>string</code> on the left,
	 * followed by multiple copies of <code>fill</code>
	 */
	public final static String fillRight( String str, String fill, int length ) {
		
		/* allocate fixed-size buffer */
		StringBuffer sb = new StringBuffer( length );

		/* append original string (full or fragmented) */
		sb.append( (str.length() <= length) ? str : str.substring( 0, length ) );
		
		length -= str.length();

		/* append full-length fillers */
		while( length >= fill.length() ) {
			
			sb.append( fill );
			
			length -= fill.length();
		}
		
		/* check for appending filler fragment */
		if( length > 0 ) {
			
			sb.append( fill.substring( 0, length ) );
		}
	
		return sb.toString();
	}

	
	/** 
	 * Create an string that consists of "n" copies of the String "c",
	 * attached to each other.
	 * 
	 * @param str - the String to copy
	 * @param n - how often to repeat str
	 */
	public final static String repeat( String str, int n ) {
		
		StringBuffer sb = new StringBuffer(n * str.length());

		for( int i = n; i > 0; i -- ) {
			sb.append( str );
		}

		return sb.toString();
	}


	/**
	 * How often does str contain substr?
	 */
	public final static int countOccurences( String str, String substr ) {
		
		int o = 0;		
		int i = 0;
		int l = substr.length();
		
		while( (i = str.indexOf( substr, i )) != -1 ) {
			
			o ++;
			
			/* substr found, so set index after substring in str */
			i += l;
		}

		return ( o );
	}

	
	/**
	 * @param str
	 * @return an array of Strings, with each element of the array containing on line of the original text
	 */
	public final static String[] linesAsArray( String str ) {
		
		String[] strar = new String[ countOccurences( str, "\n" ) ];
		
		int curr_pos = 0;
		int line_end = 0;
		int arr_index = 0;
		
		while( curr_pos < str.length() ) {
			char character = str.charAt( curr_pos );			
			
			if( character == '\n' ) { 
				strar[arr_index] = str.substring( line_end, curr_pos );
				arr_index++;
				line_end = curr_pos+1;
			}

			curr_pos++;
		}
		
		return strar;
	}
	
	
	/**
	 * How often does str contain character?
	 */
	public final static int countOccurences( String str, int chr ) {
		
		int o = 0;		
		int i = 0;
		
		while( (i = str.indexOf( chr, i )) != -1 ) {
			
			o ++;
			
			/* substr found, so set index after substring in str */
			i ++;
		}

		return ( o );
	}
	
	
    /**
     * Append an substring of a String to a StringBuffer, while maintaining the
     * given chars_per_line with filler spaces, if needed.
     * 
     * @param sb - StringBuffer to append to
     * @param str - String to get substring from
     * @param start - first character of str to append
     * @param end - last character of str to append
     * @param chars_per_line - final length of line
     */
    private static final void appendLineJustifyed(StringBuffer sb, String str, int start, int end, int chars_per_line) {
        
        int num_spaces = 0;
        
        // count spaces
        boolean in_space = (str.charAt(start) == ' ');
        for (int i = start; i <= end; i++) {
            
            char character = str.charAt(i);
            
            if (!in_space && character == ' ') {
                
                num_spaces ++;                
                in_space = true;
                
            } else if (in_space && character != ' '){                
                in_space = false;
            }
        }

        if (num_spaces > 0) {	// insert filler-spaces in order to justify
            
            int atrest_length = chars_per_line - (end - start + 1);
            int spaces_count1 = atrest_length / num_spaces;
            int spaces_count2 = atrest_length - (spaces_count1 * (num_spaces - 1));
            int current_space = 0;

            in_space = (str.charAt(start) == ' ');
            for (int i = start; i <= end; i++) {
	            
	            char character = str.charAt(i);
	            
	            if (!in_space && character == ' ') {
	                
	                current_space ++;

                    int spaces = (current_space < num_spaces || num_spaces < 2) ?
                            spaces_count1 : spaces_count2;

                    // insert "word-to-word" filler spaces
                    for (int j = 0; j < spaces; j++) {
                        sb.append(' ');	                        
                    }
	                
	                in_space = true;
	                
	            } else if (in_space && character != ' '){	                
	                in_space = false;
	            }
	            
		        sb.append(character);
	        }
   
        } else {	// do normal copy
            
	        appendLine(sb, str, start, end);
        }
    }
    
    
    /**
     * Append an substring of a String to a StringBuffer
     */
    private static final void appendLine(StringBuffer sb, String str, int start, int end) {
        
        while (start <= end) {
            
            sb.append(str.charAt(start));            
            start++;
        }
    }
    

    /**
     * Format a String into multiple lines of a given length. This method makes
     * sure that line breaks do not occur inside words, and that the whitespace
     * between words is filled up so that the right side of the text is
     * "justified".
     * 
     * @param str -
     *            the source String
     * @param chars_per_line -
     *            the maximum number of characters per line
     */
    public static final String formatJustify(String str, int chars_per_line) {
        
        return formatJustify(str, chars_per_line, true);
    }
    
	public static final String formatJustify(String str, int chars_per_line, boolean justified) {
	    
	    int string_len = str.length();
	    int line_start = 0;	// position of first char in current line
	    int char_count = 0;	// number of characters in current line
	    
	    StringBuffer sb = new StringBuffer(string_len);
	    
	    while ((line_start + char_count) < string_len) {
	        
	        char character = str.charAt(line_start + char_count);
	        
	        if (char_count == 0 && character == ' ') {
	            
	            line_start++;
	            continue;
	        }
	        
	        if (character == '\n') {	// EOL in source
	            
                int line_end = line_start + char_count - 1;

                // eat back-spaces
                while (line_end >= line_start &&
                        str.charAt(line_end) == ' ') {
                    line_end--;
                }
	            
                if (justified) {
                    appendLineJustifyed(sb, str, line_start, line_end, chars_per_line);
                } else {
                    appendLine(sb, str, line_start, line_end);
                }
	            sb.append('\n');            
	            
	            line_start = line_start + char_count + 1;            	            
	            char_count = 0;
	            
	        } else {

 	            if (++char_count == chars_per_line) {
	                
	                int line_end = line_start + char_count - 1;
	                
	                if (character == ' ') {
	                    
	                    // eat back-spaces
	                    while (line_end >= line_start &&
	                            str.charAt(line_end) == ' ') {
	                        line_end--;
	                    }
	                    
	                } else {
	                    
	                    // step back to word start
	                	int _line_end = line_end;
	                    while (_line_end >= line_start &&
	                            str.charAt(_line_end) != ' ') {
	                    	_line_end--;
	                    }
	                    if (_line_end > line_start) {
	                    	line_end = _line_end;
	                    }
 
	                    // eat back-spaces
	                    while (line_end >= line_start &&
	                            str.charAt(line_end) == ' ') {
	                        line_end--;
	                    }
	                }
                
	                if (justified) {
	                    appendLineJustifyed(sb, str, line_start, line_end, chars_per_line);
	                } else {
	                    appendLine(sb, str, line_start, line_end);
	                }
		            sb.append('\n');
		            
		            line_start = line_end + 1;	            	            
		            char_count = 0;
	            }
	        }
	    }
	    
	    if (char_count > 0) { // flush out remaining chars
	        
	        int line_end = line_start + char_count - 1;
	        
	        // eat back-spaces
            while (line_end >= line_start &&
                    str.charAt(line_end) == ' ') {
                line_end--;
            }
	        
            if (justified) {
                appendLineJustifyed(sb, str, line_start, line_end, chars_per_line);
            } else {
                appendLine(sb, str, line_start, line_end);
            }
	    }
	    
	    return sb.toString();
	}



	
	public static int indexOf(StringBuffer s, int ch){
		return indexOf(s, ch, 0);
	}


	public static int indexOf(StringBuffer s, int ch, int fromIndex){
		int len = s.length();
		
		for (int i =  fromIndex; i < len; i++) {
			if (s.charAt(i) == ch) 	return i;
		}

		return -1;
	}
	
	
	/**
	 * Get line offsets of given text
	 * 
	 * @param text - text content to parse
	 * @return int[] array with text-offsets to every line in <code>text</code>
	 */
	public final static int[] getLineOffsets( String text ) {
		
		int offsets[] = new int[ countOccurences( text, '\n' ) + 1 ];
		
		int i =  0;
		int p = -1;
		
		do {
			
			p ++;
			
			offsets[ i++ ] = p;
			
		} while( (p = text.indexOf( '\n', p )) != -1 );
		
		return ( offsets );
	}

	
    public final static int indexOf(StringBuffer sb, String str, int fromIndex) {

        int str_len = str.length();
        int sb_len = sb.length() - str_len;
        int cur = 0;

        for (int i = fromIndex; i < sb_len; i++) {

            while (sb.charAt(i) == str.charAt(cur)) {
                if (++cur == str_len) {
                    return i;
                }
            }

            cur = 0;
        }

        return -1;
    }
    
    
    /**
	* Format a String into multiple lines of a given length.
	* Word boundaries are ignored; ie word may be cut.
	* 
	* @param text - the source String
	* @param chars_per_line - the maximum number of characters per line
	*/
	public final static String formatBlock( String text, int chars_per_line ) {
		
		int text_length = text.length();
		
		/*
		 * to avoid to much re-allocating in StringBuffer, create
		 * a buffer of at least .length() capacity of source string
		 */
		StringBuffer sb = new StringBuffer( text_length );
		
		/* allocate char-buffer for storing a single line */
		char line[] = new char[ chars_per_line ];

		/* split into lines */
		int s = 0;
		int l = chars_per_line;
				
		while ( s < text_length ) {

			if( ( s + l ) > text_length ) {
				l = text_length - s;
			}
			
			/* extract "l"-chars from start "s" */
			text.getChars( s, s + l, line, 0 );

			//make sure next line does not start with white-space
			while (line[0] == WHITESPACE)  {
				s++;
				if (s+l > text_length) l--;
					
				text.getChars( s, s + l, line, 0 );
			}
			
			/* advance to next starting point */
			s += l;
			
			/* append complete line */
			sb.append( line, 0, l );

			/* find last line-break in text-line (if any) */
			int i = l - 1;
			
			while( i >= 0 && ( line[ i ] != '\n' ) ) {
				i --;
			}
			
			if( i > 0 ) { /* line-break is done in source-text */
				/* we consumed (l - (i + 1) chars from actual line */
				l = chars_per_line - (l - (i + 1));
				
			} else { /* no line-break in source-text, so insert one */
				sb.append( '\n' );

				l = chars_per_line;
			}
		}
		
		return sb.toString();
	}
	
}
