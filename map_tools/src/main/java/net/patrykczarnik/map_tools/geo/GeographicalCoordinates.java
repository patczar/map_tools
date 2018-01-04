package net.patrykczarnik.map_tools.geo;

import net.patrykczarnik.map_tools.utils.MathUtils;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * The geographical coordinates of a point on Earth (without altitude).
 * 
 * This class is immutable.
 */
public class GeographicalCoordinates {
	private final double fLatitude;
	private final double fLongitude;
	
	/** The default constructor.
	 *  Coordinates are set to (0,0).
	 */
	public GeographicalCoordinates() {
		this(0.0, 0.0);
	}

	/** Creates an object with the given coordinates.
	 * @param aLatitude the latitude in degrees; should be between -90 and 90
	 * @param aLongitude the longitude in degrees; should be between -180 and 180 
	 */
	protected GeographicalCoordinates(double aLatitude, double aLongitude) {
		fLatitude = aLatitude;
		fLongitude = aLongitude;
	}
	
	/** Creates an object with the given coordinates.
	 * @param aLatitude the latitude in degrees; must be between -90 and 90
	 * @param aLongitude the longitude in degrees; the value will be converted into [-180, 180) interval
	 * @throws IllegalArgumentException when aLatitude is out of the proper interval
	 */
	public static GeographicalCoordinates ofDegrees(double aLatitude, double aLongitude) throws IllegalArgumentException {
		MathUtils.checkArgBetween(aLatitude, -90.0, 90.0, "Invalid latitude: " + aLatitude);
		aLongitude = normalizeLongitude(aLongitude);
		return new GeographicalCoordinates(aLatitude, aLongitude);
	}
	
	/** Creates an object from coordinates given in radians.
	 * @param aLatitude the latitude in radians; must be between -Π/2 and +Π/2
	 * @param aLongitude the longitude in radians; the value will be converted into the standard interval
	 * @throws IllegalArgumentException when aLatitude is out of the proper interval
	 */
	public static GeographicalCoordinates ofRadians(double aLatitude, double aLongitude) throws IllegalArgumentException {
		return ofDegrees(Math.toDegrees(aLatitude), Math.toDegrees(aLongitude));
	}
	
	/**
	 * @return the latitude in degrees
	 */
	public final double getLatitude() {
		return fLatitude;
	}

	/**
	 * @return the longitude in degrees
	 */
	public final double getLongitude() {
		return fLongitude;
	}
	
	/**
	 * @return the latitude in radians
	 */
	public final double getLatitudeRadians() {
		return Math.toRadians(fLatitude);
	}

	/**
	 * @return the longitude in radians
	 */
	public final double getLongitudeRadians() {
		return Math.toRadians(fLongitude);
	}
	
	@Override
	public String toString() {
		return "[" + fLatitude + "," + fLongitude + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(fLatitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(fLongitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeographicalCoordinates other = (GeographicalCoordinates) obj;
		if (Double.doubleToLongBits(fLatitude) != Double.doubleToLongBits(other.fLatitude))
			return false;
		if (Double.doubleToLongBits(fLongitude) != Double.doubleToLongBits(other.fLongitude))
			return false;
		return true;
	}

	/** Normalize the given value to the standard longitude interval: from -180.0 to +180.0 (inclusive on both sides? - to think on that...).
	 * @param aLongitude any value in degrees
	 * @return the normalized value
	 */
	public static double normalizeLongitude(double aLongitude) {
		if(aLongitude >= -180.0 && aLongitude <= 180.0) {
			return aLongitude;
		}
		return (aLongitude + 180.0) % 360.0 - 180.0;
	}
}
