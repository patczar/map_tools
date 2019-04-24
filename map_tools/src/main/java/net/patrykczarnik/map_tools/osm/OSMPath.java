package net.patrykczarnik.map_tools.osm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import net.patrykczarnik.map_tools.geo.GeographicalPath;

public class OSMPath implements Iterable<OSMPoint> {
	private final List<OSMPoint> fPoints;

	private OSMPath(List<OSMPoint> aPoints) {
		fPoints = aPoints;
	}
	
	public static OSMPath empty() {
		return new OSMPath(new ArrayList<>());
	}
	
	public static OSMPath ofPoints(Collection<? extends OSMPoint> aPoints) {
		return new OSMPath(new ArrayList<>(aPoints));
	}

	public static OSMPath of(GeographicalPath aGeoPath) {
		OSMPath result = OSMPath.empty();
		result.append(aGeoPath.stream()
				.map(MercatorProjection::projectPoint01)
				.map(OSMPoint::ofCenteredPoint));
		return result;
	}

	public List<OSMPoint> getPoints() {
		return Collections.unmodifiableList(fPoints);
	}

	public Iterator<OSMPoint> iterator() {
		return this.getPoints().iterator();
	}
	
	public Stream<OSMPoint> stream() {
		return this.getPoints().stream();
	}
	
	public OSMPath append(OSMPoint aPoint) {
		fPoints.add(aPoint);
		return this;
	}
	
	public OSMPath append(Collection<? extends OSMPoint> aPoints) {
		fPoints.addAll(aPoints);
		return this;
	}
	
	public OSMPath append(Stream<? extends OSMPoint> aPoints) {
		aPoints.forEach(this::append);
		return this;
	}
	
	public OSMPoint getAverage() {
		if(fPoints.isEmpty()) {
			throw new NoSuchElementException("Empty path has no average.");
		}
		long sumx=0, sumy=0;
		final int count = fPoints.size();
		for (OSMPoint p : fPoints) {
			sumx += p.x;
			sumy += p.y;
		}
		int x = (int)(sumx / count);
		int y = (int)(sumy / count);
		return OSMPoint.of(x, y);
	}
	
	/** Returns the area in which the path is strictly inscribed.
	 */
	public OSMArea getContainingArea() {
		if(fPoints.isEmpty()) {
			throw new NoSuchElementException("Empty path has no bounds.");
		}
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		
		for(OSMPoint p : fPoints) {
			if(p.x < minX)
				minX = p.x;
			if(p.x > maxX)
				maxX = p.x;
			if(p.y < minY)
				minY = p.y;
			if(p.y > maxY)
				maxY = p.y;
		}
		return OSMArea.of(minX, maxX, minY, maxY);
	}

}
