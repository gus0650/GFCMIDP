package gfc.util;

/**
 * A point representing a location in 2D (x, y) coordinate space, specified in integer precision. 
 */

public class Point {
	public int x;
	public int y;


	/**
	 * Constructs and initializes a point at the origin (0, 0) of the coordinate space.
	 */

	public Point() {
		x = 0;
		y = 0;
	}


	/**
	 * Constructs and initializes a point at the specified (x, y) location in the coordinate space.
	 */

	public Point(int _x, int _y) {
		x = _x;
		y = _y;
	}


	/**
	 * Constructs and initializes a point with the same location as the specified Point object.
	 */

	public Point(Point p) {
		this.x = p.x;
		this.y = p.y;
	}


	/** Returns the distance from this Point to a specified Point. */
	public int distance(Point p) {

		int dx = x - p.x;
		int dy = y - p.y;

		if (dx < 0) dx = dx * -1;
		if (dy < 0) dy = dy * -1;
		
		if (dx < dy) 	return dy;
		else 			return dx;
	}


	/** Returns the square of the distance from this Point to a specified Point. */
	public int distanceSq(Point p) {
		int d;

		d = distance(p);
		d = d*d;

		return d;
	}


	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
