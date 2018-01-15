package net.patrykczarnik.map_tools.osm;

import net.patrykczarnik.map_tools.utils.MathUtils;

import static net.patrykczarnik.map_tools.osm.OSMConstants.*;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * A point (pixel) of the map expressed as absolute coordinates in the maximum scale ({@link OSMConstants.MAX_SCALE}
 */
public class OSMPoint {
	/** The absolute coordinates. Intentionally left public to promote ease of use and efficiency. */
	public final int x, y;

	private OSMPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/** Creates an OSMPoint of the given absolute coordintes in the maximum scale ({@link OSMConstants.MAX_SCALE}
	 * @param x
	 * @param y
	 * @return a fresh object, never null
	 * @throws IllegalArgumentException if any argument is negative
	 */
	public static OSMPoint of(int x, int y) {
		MathUtils.checkArgNonNegative(x, "OSMPoint x negative " + x);
		MathUtils.checkArgNonNegative(y, "OSMPoint y negative " + y);
		return new OSMPoint(x, y);
	}
	
	/** Returns the tile which the point belongs to.
	 * @param aScale the requested scale od the tile
	 * @return an OSMTile object describing the map tile this point belongs to
	 */
	public OSMTile getTile(int aScale) {
		final int tileX = x >> (PIXELS_OF_TILE_BITS + MAX_SCALE - aScale);
		final int tileY = y >> (PIXELS_OF_TILE_BITS + MAX_SCALE - aScale);
		return OSMTile.ofCoordinates(aScale, tileX, tileY);
	}
}
