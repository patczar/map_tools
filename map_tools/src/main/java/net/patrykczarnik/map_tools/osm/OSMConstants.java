package net.patrykczarnik.map_tools.osm;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * This class defines limits and other constants related to the OSM-like map engines supported by this library.
 */
public final class OSMConstants {
	/** This class is not to be instantiated. */
	private OSMConstants() {
	}
	
	/** The number of bits for pixel coordinates in the maximum supported scale. */
	static final int PIXELS_OF_WORLD_BITS = 31;

	/** The number of bits for pixel coordinates in the maximum supported scale. */
	static final long PIXELS_OF_WORLD = 1L << PIXELS_OF_WORLD_BITS;

	/** The number of bits which store the pixel coordinate within a tile. */
	static final int PIXELS_OF_TILE_BITS = 8;

	/** The number of pixels a tile consists of, in each dimension. */
	static final int PIXELS_OF_TILE = 1 << PIXELS_OF_TILE_BITS; // 256

	/** Maximum scale of the map.
	 * 
	 * Note that this is not the limit of the real map engine (different maps have different scales), but rather the limit of this library.
	 * 
	 * Implementation note: the number is chosen so that MAX_SCALE + PIXELS_OF_TILE_BITS = 31, to leverage the size of int.
	 * The real maximum scale of openstreetmap.org is 19.
	 */
	public static final int MAX_SCALE = 31 - PIXELS_OF_TILE_BITS; // 23
}
