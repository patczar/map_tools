package net.patrykczarnik.map_tools.graphic;

import java.awt.Rectangle;

import net.patrykczarnik.map_tools.osm.OSMPoint;
import net.patrykczarnik.map_tools.utils.MathUtils;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * An object of this class contains four numbers which usually denote extreme coordinates of a set of points.
 * 
 * This class is immutable.
 */
public class PixelBounds {
	public final int minX, maxX, minY, maxY;
	
	private PixelBounds(int aMinX, int aMaxX, int aMinY, int aMaxY) {
		minX = aMinX;
		maxX = aMaxX;
		minY = aMinY;
		maxY = aMaxY;
	}

	/** Returns a new object with the given coordinates.
	 * @param aMinX the minimal horizontal coordinate, counted from 0 (left to right)
	 * @param aMaxX the maximal horizontal coordinate, counted from 0 (left to right)
	 * @param aMinY the minimal vertical coordinate, counted from 0 (top down)
	 * @param aMaxY the maximal vertical coordinate, counted from 0 (top down)
	 * @return a fresh PixelBounds object, never null
	 * @throws IllegalArgumentException if any of the arguments is negative
	 */
	public static PixelBounds of(int aMinX, int aMaxX, int aMinY, int aMaxY) throws IllegalArgumentException {
		MathUtils.checkArgNonNegative(aMinX, "Negative pixel x coordinate " + aMinX);
		MathUtils.checkArgNonNegative(aMaxX, "Negative pixel x coordinate " + aMaxX);
		MathUtils.checkArgNonNegative(aMinY, "Negative pixel y coordinate " + aMinY);
		MathUtils.checkArgNonNegative(aMaxY, "Negative pixel y coordinate " + aMaxY);
		MathUtils.checkLE(aMinX, aMaxX, "Pixel bound x " + aMinX + " is greater than " + aMaxX);
		MathUtils.checkLE(aMinY, aMaxY, "Pixel bound y " + aMinY + " is greater than " + aMaxY);
		return new PixelBounds(aMinX, aMaxX, aMinY, aMaxY);
	}

	@Override
	public int hashCode() {
		return (minX ^ (minY >> 16 | minY << 16)) * 31 + (maxX ^ (maxY >> 16 | maxY << 16));
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
		if (minX != other.x)
			return false;
		if (minY != other.y)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "PixelBounds [x: " + minX + "↔" + maxX + ", y:" + minY + "↔" + maxY + "]";
	}

	/** Returns the coordinates of this object relative to the parameter.
	 * This operation can also be understood as subtraction of pixel coordinates.
	 * @param aCoords 
	 * @return a fresh {@link PixelBounds} object, never null; note that the returned object may contain negative coordinates
	 */
	public PixelBounds relativize(PixelCoordinates aCoords) {
		return new PixelBounds(this.minX - aCoords.x, this.maxX - aCoords.x, this.minY - aCoords.y, this.maxY - aCoords.y);
	}

	public int getWidth() {
		return maxX - minX;
	}

	public int getHeight() {
		return maxY - minY;
	}
	
	public Rectangle toAwtRectangle() {
		return new Rectangle(minX, minY, getWidth(), getHeight());
	}

}
