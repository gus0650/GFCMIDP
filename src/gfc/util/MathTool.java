package gfc.util;


/**
 * Provides various methods for mathematical operations.
 * 
 * Fully based on integer maths, thus also works on J2ME MIDP 1.0 devices!
 * 
 * Note: low precision maths
 */

public class MathTool {

	/**
	 * Returns the value of the first argument raised to the power of the second argument.
	 * 
	 * @param base
	 * @param exponent
	 * @return base ^ exponent
	 */
	public static int pow(int base, int exponent) {
		int result = 1;
		
		for (int i = 0; i < exponent; i++) {
			result = result * base;
		}
		
		return result;
	}
	
	
	/**
	 * Calculates the suqare root of a number through Newton's formula.
	 * <p>
	 * 
	 * This method does not use floating point math. It therefore returns only
	 * an approximated integer value as result.
	 * 
	 * @param a -
	 *            the number of which to calculate the square root
	 * @return - approx. square root value of a
	 */
	public static int sqrt(int a) throws IllegalArgumentException {
		if (a < 0)
			throw new IllegalArgumentException("argument must be > 0: " + a);
		if (a == 0)
			return 0;
		if (a == 1)
			return 1;

		int x, xp, err;
		x = a / 2;

		do {
			xp = a / x;
			xp = xp + x;
			xp = xp / 2;
			/* i.e. xp = (a/x + x)/2 */
			err = Math.abs(xp - x);
			x = xp;
		} while (err > 1);

		return x;
	}
	
	
	/**
	 * checks if two lines intersect
	 */
	public static boolean intersectLines(int i1, int l1, int i2, int l2) {
		return ((i1 <= (i2 + l2)) && ((i1 + l1) >= i2));
	}
	
	
	/**
	 * checks if two rectangles intersect
	 */
	public static boolean intersectRect(int x1, int y1, int w1, int h1,
	   int x2, int y2, int w2, int h2) {
			return (intersectLines(x1, w1, x2, w2)
					 && intersectLines(y1, h1, y2, h2));
    }

	
	public static int abs(int v) {
		return (v < 0) ? -v : v;
	}


    /** angle is in degrees/10, i.e. 0..36 for full circle */
    public static int sineTimes256(int angle) {
        /* sines of angles 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, all x256 */
    	final int[] SINES = { 0, 44, 88, 128, 165, 196, 222, 241, 252, 256 };
    	
    	angle %= 36;    		 // 360 degrees
        if (angle <= 9)          // 0..90 degrees
        {
            return SINES[angle];
        }
        else if (angle <= 18)    // 90..180 degrees
        {
            return SINES[18-angle];
        }
        else if (angle <= 27)    // 180..270 degrees
        {
            return -SINES[angle-18];
        }
        else                     // 270..360 degrees
        {
            return -SINES[36-angle];
        }
    }


    /** angle is in degrees/10, i.e. 0..36 for full circle */
    public static int cosineTimes256(int angle)
    {
        return sineTimes256(angle + 9);     // i.e. add 90 degrees
    }
}
