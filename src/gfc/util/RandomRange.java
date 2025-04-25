package gfc.util;


/**
 * A simple random number generator.
 * 
 * Generates a random integer in the range [min..max].
 * 
 * @author Gustaf Stechmann
 */
public class RandomRange extends java.util.Random {
	
	/** 
	 * Generate the random number. 
	 */
	public int generate(int min, int max) {
		int r;

		if (max <= min) return min;

		r = nextInt() % ((max-min) + 1);
		if (r < 0) r = r * -1;
		r = r + min;

		return r;
	}
}
