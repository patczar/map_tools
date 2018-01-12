package net.patrykczarnik.map_tools.osm;

import net.patrykczarnik.map_tools.utils.MathUtils;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 * 
 * Specification of a map tile. Consists of three coordinates:
 * the map scale, horizontal and vertical tile number (counted from 0).
 */
public class OSMTile {
	public final int scale;
	public final int x, y;

	private OSMTile(int aScale, int x, int y) {
		scale = aScale;
		this.x = x;
		this.y = y;
	}
	
	/** Creates a new object based on provided coordinates validating the values.
	 * 
	 * @param aScale the scale of the map
	 * @param x the number of the tile horizontally; must be between 0 and 2^scale - 1
	 * @param y the number of the tile vertically; must be between 0 and 2^scale - 1
	 * @return a fresh OSMTile object, never null
	 * @throws IllegalArgumentException if scale is negative or x/y are out of the proper interval
	 */
	public static OSMTile ofCoordinates(int aScale, int x, int y) {
		MathUtils.checkArgBetween(aScale, 0, OSMConstants.MAX_SCALE, "incorrect scale " + aScale);
		final int max = 1 << aScale - 1;
		MathUtils.checkArgBetween(x, 0, max, "x out of proper interval for the given scale");
		MathUtils.checkArgBetween(y, 0, max, "y out of proper interval for the given scale");
		return new OSMTile(aScale, x, y);
	}
	

}
