package net.patrykczarnik.map_tools.geo;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * A path is an ordered sequence of points (GeographicalCoordinates).
 * For example, a GPS track without altitude or speed information nor any metadata can be represented as a GeographicalPath.
 */
public class GeographicalPath implements Iterable<GeographicalCoordinates> {
	private final LinkedList<GeographicalCoordinates> fPoints;
	
	/** Creates an empty path. */
	protected GeographicalPath() {
		fPoints = new LinkedList<>();
	}
	
	/** Creates an object with the given list of points (the same instance is used). */
	private GeographicalPath(LinkedList<GeographicalCoordinates> aPoints) {
		fPoints = aPoints;
	}
	
	/** Creates a path with the given points.
	 * @param aPoints a collection of points which the created path will initially contain;
	 * 	this collection is not modified and the reference in not remembered, so it can be safely used afterwards 
	 * @return a fresh GeographicalPath object
	 */
	public static GeographicalPath ofPoints(Collection<GeographicalCoordinates> aPoints) {
		return new GeographicalPath(new LinkedList<>(aPoints));
	}

	/** Creates a path with the same list of points.
	 * @param aPath an existing GeographicalPath object
	 * @return a fresh, distinct GeographicalPath object with the same list points initially; further modifications on this object (including its list of points) do not affect the origin
	 */
	public static GeographicalPath of(GeographicalPath aPath) {
		return ofPoints(aPath.getPoints());
	}
	
	/**
	 * @return an unmodifiable view of the list of points
	 */
	public List<GeographicalCoordinates> getPoints() {
		return Collections.unmodifiableList(fPoints);
	}
	
	public int getSize() {
		return fPoints.size();
	}
	
	/**
	 * @return the first point of this path; never null
	 * @throws NoSuchElementException if this path is empty
	 */
	public GeographicalCoordinates getStart() throws NoSuchElementException {
		return fPoints.getFirst();
	}

	/**
	 * @return the last point of this path; never null
	 * @throws NoSuchElementException if this path is empty
	 */
	public GeographicalCoordinates getEnd() throws NoSuchElementException {
		return fPoints.getLast();
	}

	/** Computes the average point of this path.
	 * The arithmetic average is computed basing on degree measure, each point in the path is counted with the same weight.
	 * In case of empty path a point of (NaN,NaN) is returned.
	 * The cost of this method is linear wrt the size of the path.
	 */
	public GeographicalCoordinates getAverage() {
		double sumLat = 0.0, sumLon = 0.0;
		final int count = fPoints.size();
		for(GeographicalCoordinates point : fPoints) {
			sumLat += point.getLatitude();
			sumLon += point.getLongitude();
		}
		return GeographicalCoordinates.ofDegrees(sumLat / count, sumLon / count);
	}
	
	/** Adds a point at the end of this path.
	 * @param aPoint
	 */
	public void addPoint(GeographicalCoordinates aPoint) {
		fPoints.add(aPoint);
	}
	
	/** Adds points at the end of this path.
	 * @param aPoints a collections of points; the order presented by this collection will be preserved
	 */
	public void addPoints(Collection<GeographicalCoordinates> aPoints) {
		fPoints.addAll(aPoints);
	}
	
	@Override
	public String toString() {
		return fPoints.toString();
	}

	/** Returns a read-only iterator over the list of points.
	 * @see java.lang.Iterable#iterator()
	 */
	public ListIterator<GeographicalCoordinates> iterator() {
		return this.getPoints().listIterator();
	}
}
