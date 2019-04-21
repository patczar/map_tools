package net.patrykczarnik.map_tools.osm;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * An object of this class represents a point of a 2-dimensional plane.
 * It can be also used to represent a vector.
 *
 * The values are not restricted in any way and the scale is not determined.
 * 
 * Ths class in immutable. The fields are consciously left public to simplify the usage and enhance the performance.
 */
public class PlainPoint {
	/** The horizontal and vertical coordinates. */
	public final double x, y;
	
	/** Creates a point with the given coordinates.
	 * @param x the vertical coordinate
	 * @param y the horizontal coordinate
	 */
	public PlainPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/** Returns the result of moving this point by given coordinates.
	 * @return a fresh PlainPoint object; never null
	 */
	public PlainPoint moved(double aHorizontalMove, double aVerticalMove) {
		return new PlainPoint(this.x + aHorizontalMove, this.y + aVerticalMove);
	}

	/** Returns the result of moving this point by a given vector.
	 * @param aVector the specification of the move
	 * @return a fresh PlainPoint object; never null
	 */
	public PlainPoint moved(PlainPoint aVector) {
		return moved(aVector.x, aVector.y);
	}
	
	/** Returns this point (as a new instance) scaled with a given multiplicand.
	 * 
	 * This method uses standard multiplication as is vulnerable to floating-point inaccuracy.
	 * For scalling with expotentials of 2 better use @see #scaledExp2(int)
	 * 
	 * @param aScale
	 * @return a fresh PlainPoint object; never null
	 */
	public PlainPoint scaled(double aScale) {
		return new PlainPoint(aScale * this.x, aScale * this.y);
	}

	/** Returns this point (as a new instance) scaled with 2 to the given exponent.
	 * 
	 * This method offers better presicion than @see #scaled(double).
	 * 
	 * @param aScaleExp the exponent of 2, by which the scale will be changed; can be positive or negative,
	 * @return a fresh PlainPoint object with coordinates multiplied by 2^aScaleExp; never null
	 */
	public PlainPoint scaledExp2(int aScaleExp) {
		return new PlainPoint(Math.scalb(x, aScaleExp), Math.scalb(y, aScaleExp));
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		PlainPoint other = (PlainPoint) obj;
		if(Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if(Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
}
