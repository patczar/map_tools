package net.patrykczarnik.map_tools.osm;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * This utility class contains functions implementing the <a href="http://en.wikipedia.org/wiki/Mercator_projection">Mercator projection</a> in its restricted "web" variant.
 */
public final class MercatorProjection {
	/** This class is not to be instantiated. */
	private MercatorProjection() {
	}
	
	/** Converts the latitude expressed in radians to the vertical coordinate in Mercator projection.
	 * 
	 * The result is scaled so that 0 denotes the equator, positive values denotes the north semisphere and negative values the south semisphere.
	 * The value of 1 is placed at 85.05113°.
	 * 
	 * @param aLatitudeRad latitude in radians, from -Π/2 to +Π/2
	 * @return the vertical coordinate in Mercator projection, between -INF and +INF (extreme values for the poles)
	 */
	public static double projectLatitude(double aLatitudeRad) {
		return Math.log(Math.tan(Math.PI/4.0 + aLatitudeRad/2.0));
	}
	
	/** Converts the longitude expressed in radians to the horizontal coordinate in Mercator projection.
	 * 
	 * The result is scaled so that 0 denotes the meridian zero (Greenwich), positive values denotes the east semisphere and negative values the west semisphere.
	 * The value of 1 corresponds to half the length of the equator, so values between -1 and 1 are resulting from valid longitudes.
	 * 
	 * @param aLongitudeRad longitude in radians, from -Π to +Π
	 * @return the horizontal coordinate in Mercator projection; for valid arguments the result is between -1 and 1
	 */
	public static double projectLongitude(double aLongitudeRad) {
		return aLongitudeRad / Math.PI;
	}
}
