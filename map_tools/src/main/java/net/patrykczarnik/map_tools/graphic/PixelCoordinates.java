package net.patrykczarnik.map_tools.graphic;

import net.patrykczarnik.map_tools.osm.OSMPoint;
import net.patrykczarnik.map_tools.utils.MathUtils;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * Coordinates of a pixel within an image.
 * 
 * This class is immutable.
 */
public class PixelCoordinates {
	public final int x, y;

	private PixelCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/** Returns a new object with the given coordinates.
	 * @param x the horizontal coordinate, counted from 0 (left to right)
	 * @param y the vertical coordinate, counted from 0 (top down)
	 * @return a fresh PixelCoordinates object, never null
	 * @throws IllegalArgumentException if any of the arguments is negative
	 */
	public static PixelCoordinates of(int x, int y) throws IllegalArgumentException {
		MathUtils.checkArgNonNegative(x, "Negative pixel x coordinate " + x);
		MathUtils.checkArgNonNegative(x, "Negative pixel y coordinate " + y);
		return new PixelCoordinates(x, y);
	}

	@Override
	public int hashCode() {
		return x ^ (y >> 16 | y << 16);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OSMPoint other = (OSMPoint) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
