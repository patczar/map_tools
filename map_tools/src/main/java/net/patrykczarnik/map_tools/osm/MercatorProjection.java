package net.patrykczarnik.map_tools.osm;

import net.patrykczarnik.map_tools.geo.GeographicalCoordinates;
import net.patrykczarnik.map_tools.utils.MathUtils;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * This utility class contains functions implementing the <a href="http://en.wikipedia.org/wiki/Mercator_projection">Mercator projection</a> in its restricted "web" variant.
 */
public final class MercatorProjection {
	/** This class is not to be instantiated. */
	private MercatorProjection() {
	}
	
	/** Maximum allowed absolute value of latitude so that the resulting projection fits within [-1, +1] interval. */
	public static final double LATITUDE_RANGE_RADIANS =	Math.nextDown(Math.atan(Math.sinh(Math.PI)));

	/** Maximum allowed absolute value of latitude so that the resulting projection fits within [-1, +1] interval. */
	public static final double LATITUDE_RANGE_DEGREES = Math.nextDown(Math.toDegrees(LATITUDE_RANGE_RADIANS));
	
	/** Converts the latitude expressed in radians to the vertical coordinate in Mercator projection.
	 * 
	 * The result is scaled so that 0 denotes the equator, positive values denotes the north semisphere and negative values the south semisphere.
	 * The value of 1 is placed at {@link #LATITUDE_RANGE_RADIANS}.
	 * 
	 * @param aLatitudeRad latitude in radians, from -Π/2 to +Π/2
	 * @return the vertical coordinate in Mercator projection, between -INF and +INF (extreme values for the poles)
	 */
	public static double projectLatitude(double aLatitudeRad) {
		return Math.log(Math.tan(Math.PI/4.0 + aLatitudeRad/2.0)) / Math.PI;
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
	
	/** Converts a point on sphere given in geographical coordinates into a plain point
	 * without any additional checking.
	 * 
	 * In the result the point (0,0) denotes the (0,0) geographical coordinates, valid longitudes are mapped to [-1, +1] interval,
	 * and latitudes are mapped so that the proportions are preserved, which gives the value 1.0 for {@link #LATITUDE_RANGE_DEGREES} and ±INF for poles.
	 * 
	 * @param aCoords valid geographical coordinates, not null
	 * @return a fresh PlainPoint, never null
	 */
	public static PlainPoint projectPoint(GeographicalCoordinates aCoords) {
		return new PlainPoint(projectLongitude(aCoords.getLongitudeRadians()),
					projectLatitude(aCoords.getLatitudeRadians()));
	}

	/** Converts a point on sphere given in geographical coordinates into a plain point
	 * checking that the result fits within [-1, +1] interval.
	 * 
	 * @param aCoords valid geographical coordinates, not null
	 * @return a fresh PlainPoint with both coordinates within [-1, +1] interval, never null
	 * @throws IllegalArgumentException if input coordinates exceed the allowed interval,
	 * 		in particular when the absolute value of latitude is greater than {@link #LATITUDE_RANGE_DEGREES} (about 85°)
	 */
	public static PlainPoint projectPoint01(GeographicalCoordinates aCoords) throws IllegalArgumentException {
		MathUtils.checkArgBetween(aCoords.getLatitude(), -LATITUDE_RANGE_DEGREES, LATITUDE_RANGE_DEGREES,
					"Latitude value " + aCoords.getLatitude() + " exceeds the allowed interval for map projection");
		MathUtils.checkArgBetween(aCoords.getLongitude(), -180.0, 180.0,
				"Invalid longitude value " + aCoords.getLongitude());
		return projectPoint(aCoords);
	}
}
