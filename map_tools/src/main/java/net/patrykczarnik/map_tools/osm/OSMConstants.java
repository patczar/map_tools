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
	
	
	/** Maximum scale of the map.
	 * 
	 * Note that this is not the limit of the real map engine (different maps have different scales), but rather the limit of this library.
	 */
	public static final int MAX_SCALE = 30;

	/** The number of pixels a tile consists of, in each dimension. */
	static final int PIXELS_OF_TILE = 256;

	/** The number of pixels a tile consists of horizontally.
	    To ease a possible change in future, the other code should refer to this constant rather than PIXELS_OF_TILE. */
	public static final int PIXELS_OF_TILE_X = PIXELS_OF_TILE;

	/** The number of pixels a tile consists of vertically.
    To ease a possible change in future, the other code should refer to this constant rather than PIXELS_OF_TILE. */
	public static final int PIXELS_OF_TILE_Y = PIXELS_OF_TILE;
	
	
}
