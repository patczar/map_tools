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
	
	/** Creates an OSMPoint of fractional coordinates scaled in the range of [0,1] in each dimension.
	 * (0,0) is the top left corder of the whole map, (1,1) is the bottom right corner
	 * @param x the horizontal coordinate; a number between 0.0 (inclusive) and 1.0 (exclusive)
	 * @param y the vertical coordinate; a number between 0.0 (inclusive) and 1.0 (exclusive)
	 * @return a fresh object, never null
	 * @throws IllegalArgumentException if any argument is out of the range
	 */
	public static OSMPoint of01Point(double x, double y) {
		MathUtils.checkArgBetweenCO(x, 0.0, 1.0, "OSMPoint x value out of 01 range " + x);
		MathUtils.checkArgBetweenCO(y, 0.0, 1.0, "OSMPoint y value out of 01 range " + y);
		int ix = (int) Math.scalb(x, PIXELS_OF_WORLD_BITS);
		int iy = (int) Math.scalb(y, PIXELS_OF_WORLD_BITS);
		return new OSMPoint(ix, iy);
	}
	
	/** Creates an OSMPoint of fractional coordinates scaled in the range of [0,1] in each dimension.
	 * (0,0) is the top left corder of the whole map, (1,1) is the bottom right corner
	 * @param aPoint a plain point scaled in 0(inclusive) - 1(exclusive) range 
	 * @return a fresh object, never null
	 * @throws IllegalArgumentException if any argument is out of the range
	 */
	public static OSMPoint of01Point(PlainPoint aPoint) {
		return of01Point(aPoint.x, aPoint.y);
	}
	
	/** Creates an OSMPoint of fractional coordinates scaled in the range of [-1, +1] in each dimension.
	 * (0,0) is the center of the whole map
	 * @param x the horizontal coordinate; a number between -1.0 and +1.0 (exclusive)
	 * @param y the vertical coordinate; a number between  -1.0 and +1.0 (exclusive)
	 * @return a fresh object, never null
	 * @throws IllegalArgumentException if any argument is out of the range
	 */
	public static OSMPoint ofCenteredPoint(double x, double y) {
		x = (x + 1.0) / 2.0;
		y = (y + 1.0) / 2.0;
		return of01Point(x, y);
	}
	
	/** Creates an OSMPoint of fractional coordinates scaled in the range of [0,1] in each dimension.
	 * (0,0) is the top left corder of the whole map, (1,1) is the bottom right corner
	 * @param aPoint a plain point scaled in 0(inclusive) - 1(exclusive) range 
	 * @return a fresh object, never null
	 * @throws IllegalArgumentException if any argument is out of the range
	 */
	public static OSMPoint ofCenteredPoint(PlainPoint aPoint) {
		return ofCenteredPoint(aPoint.x, aPoint.y);
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
	
	/** Returns the absolute coordinates of this point within the whole world map in the given scale.
	 * @param aScale
	 * @return
	 */
	public PixelCoordinates getAbsoluteCoordinates(int aScale) {
		final int shift = MAX_SCALE - aScale;
		final int pixelX = x >> shift;
		final int pixelY = y >> shift;
		return PixelCoordinates.of(pixelX, pixelY);
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

	/** Returns the coordinates of this point as a pixel within the tile in the given scale.
	 * @param aScale
	 * @return
	 */
	public PixelCoordinates getCoordinatesRelativeToTile(OSMTile aTile) {
		PixelCoordinates tileCoord = aTile.getAbsoluteCoordinates();
		PixelCoordinates thisCoord = this.getAbsoluteCoordinates(aTile.scale);
		return thisCoord.relativize(tileCoord);
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
	
	@Override
	public String toString() {
		return "OSMPoint ("+ x +","+ y +")";
	}
}
