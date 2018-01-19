package net.patrykczarnik.map_tools.osm;

import net.patrykczarnik.map_tools.graphic.PixelCoordinates;
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
		MathUtils.checkArgBetweenCC(aScale, 0, OSMConstants.MAX_SCALE, "incorrect scale " + aScale);
		final int max = (1 << aScale) - 1;
		MathUtils.checkArgBetweenCC(x, 0, max, "x (" + x + ") out of proper interval for the given scale");
		MathUtils.checkArgBetweenCC(y, 0, max, "y (" + y + ") out of proper interval for the given scale");
		return new OSMTile(aScale, x, y);
	}
	
	@Override
	public String toString() {
		return "OSMTile scale="+scale+", point=(" + x +","+ y +")";
	}

	/** Returns absolute pixel coordinates of the top-left corner of this tile within the whole world map scaled in this tile scale.
	 * @return the computed PixelCoordinates, never null
	 */
	public PixelCoordinates getAbsoluteCoordinates() {
		final int pxX = OSMConstants.PIXELS_OF_TILE * x;
		final int pxY = OSMConstants.PIXELS_OF_TILE * y;
		return PixelCoordinates.of(pxX, pxY);
	}
}
