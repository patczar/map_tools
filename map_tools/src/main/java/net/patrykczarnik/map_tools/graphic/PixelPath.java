package net.patrykczarnik.map_tools.graphic;

import java.awt.Point;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * A path of points. Typically used to denote a series of pixels.
 */
public class PixelPath {
	private final List<PixelCoordinates> fPixels;

	private PixelPath(List<PixelCoordinates> aPixels) {
		fPixels = aPixels;
	}
	
	/** Creates a new path with no points.
	 * @return a fresh PixelPath object, never null.
	 */
	public static PixelPath empty() {
		return new PixelPath(new LinkedList<>());
	}
	
	/** Creates a new path initialized with the given collection of points. The order of the points originating from the input collection will be preserved.
	 * @param aPixels a collection of points, not null
	 * @return a fresh PixelPath object, never null.
	 */
	public static PixelPath of(Collection<PixelCoordinates> aPixels) {
		return new PixelPath(new LinkedList<>(aPixels));
	}
	
	/** Creates a new path initialized with the given points. If the stream is ordered, the order of the points will be preserved. 
	 * @param aPixels a stream of points, not null. The stream must be finished.
	 * @return a fresh PixelPath object, never null.
	 */
	public static PixelPath of(Stream<PixelCoordinates> aPixels) {
		return new PixelPath(aPixels.collect(Collectors.toList()));
	}
	
	@Override
	public String toString() {
		return fPixels.toString();
	}

	public List<Point> getPoints() {
		return fPixels.stream()
			.map(PixelCoordinates::toAwtPoint)
			.collect(Collectors.toList());
	}

	public PixelBounds getBounds() {
		if(fPixels.isEmpty()) {
			throw new NoSuchElementException("Empty path has no bounds.");
		}
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		
		for(PixelCoordinates p : fPixels) {
			if(p.x < minX)
				minX = p.x;
			if(p.x > maxX)
				maxX = p.x;
			if(p.y < minY)
				minY = p.y;
			if(p.y > maxY)
				maxY = p.y;
		}
		return PixelBounds.of(minX, maxX, minY, maxY);
	}
}
