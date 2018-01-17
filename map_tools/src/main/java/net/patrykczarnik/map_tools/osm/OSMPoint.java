package net.patrykczarnik.map_tools.osm;

import net.patrykczarnik.map_tools.utils.MathUtils;

import static net.patrykczarnik.map_tools.osm.OSMConstants.*;

import net.patrykczarnik.map_tools.graphic.PixelCoordinates;

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
		final int shift = PIXELS_OF_TILE_BITS + MAX_SCALE - aScale;
		final int tileX = x >> shift;
		final int tileY = y >> shift;
		return OSMTile.ofCoordinates(aScale, tileX, tileY);
	}
	
	/** Returns the coordinates of this point as a pixel within the tile in the given scale.
	 * @param aScale
	 * @return
	 */
	public PixelCoordinates getCoordinatesWithinTile(int aScale) {
		final int shift = MAX_SCALE - aScale;
		final int mask = PIXELS_OF_TILE - 1; // is ~ more efficient?
		final int pixelX = mask & (x >> shift);
		final int pixelY = mask & (y >> shift);
		return PixelCoordinates.of(pixelX, pixelY);
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
