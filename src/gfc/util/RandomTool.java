package gfc.util;


/**
 * class RandomRange
 * 
 * Expand the java.util.Random class in functionallity
 */
public class RandomTool {

	/** seed value */
	private long seed;

	/**
	 * Creates a new random number generator. Its seed is initialized to a value
	 * based on the current time.
	 */
	public RandomTool() {
		this(System.currentTimeMillis());
	}

	/**
	 * Creates a new random number generator using a single long seed.
	 * 
	 * @param seed -
	 *            the initial seed
	 */
	public RandomTool(long seed) {
		setSeed(seed);
	}

	/**
	 * Sets the seed of this random number generator using a single long seed.
	 * The general contract of setSeed is that it alters the state of this
	 * random number generator object so as to be in exactly the same state as
	 * if it had just been created with the argument seed as a seed.
	 * 
	 * @param seed -
	 *            the initial seed
	 */
	synchronized public void setSeed(long seed) {
		this.seed = (seed ^ 0x5DEECE66DL) & ((1L << 48) - 1);
	}

	/**
	 * Generates the next pseudorandom number. Subclass should override this, as
	 * this is used by all other methods. The general contract of next is that
	 * it returns an int value and if the argument bits is between 1 and 32
	 * (inclusive), then that many low-order bits of the returned value will be
	 * (approximately) independently chosen bit values, each of which is
	 * (approximately) equally likely to be 0 or 1.
	 * 
	 * @param bits -
	 *            random bits
	 * @return the next pseudorandom value from this random number generator's
	 *         sequence
	 */
	synchronized protected int next(int bits) {
		this.seed = (this.seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);
		return (int) (this.seed >>> (48 - bits));
	}

	/**
	 * Generate the next random boolean
	 */
	public boolean nextBoolean() {
		return (next(1) != 0);
	}

	/**
	 * Returns the next pseudorandom, uniformly distributed int value from this
	 * random number generator's sequence.
	 */
	public int nextInt() {
		return next(32);
	}

	/**
	 * Generate an random number between two values
	 * 
	 * @param min -
	 *            minimal random number
	 * @param max -
	 *            maximal random number
	 * @return a number between min and max (inclusive min, max)
	 */
	public int generate(int min, int max) {

		if (max <= min) {
			return (min);
		}

		int r = next(32) % ((max - min) + 1);
		if (r < 0) {
			r = -r;
		}

		return (r + min);
	}
}