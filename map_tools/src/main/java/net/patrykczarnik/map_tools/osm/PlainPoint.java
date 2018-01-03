package net.patrykczarnik.map_tools.osm;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * An object of this class represents a point of a 2-dimensional plane.
 * It can be also used to represent a vector.
 *
 * The values are not restricted in any way and the scale is not determined.
 * Ths class in immutable. The fields are consciously left public to simplify the usage and enhance the performance.
 */
public class PlainPoint {
	public final double x, y;

	public PlainPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	
}
