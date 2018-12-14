package net.patrykczarnik.map_tools.osm;

import java.util.stream.Stream;

import net.patrykczarnik.map_tools.geo.GeographicalCoordinates;
import net.patrykczarnik.map_tools.geo.GeographicalPath;
import net.patrykczarnik.map_tools.graphic.PixelCoordinates;
import net.patrykczarnik.map_tools.graphic.PixelPath;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * Utility class implementing convertions between various types of points/paths.
 * In particular convertions which are indirect or from other reasons should not be defined individually for particular objects.
 */
public final class Convertions {
	/** This class is not to be instantiated. */
	private Convertions() {
	}

	/** Converts points specified with geographical coordinates into coordinates of pixels in a whole world map in the given scale. Stream version.
	 * @param aStream the source of points
	 * @param aScale the scale of the target map
	 * @return
	 */
	public static Stream<PixelCoordinates> geographicalCoordinatesToPixelPointsStream(
			final Stream<GeographicalCoordinates> aStream,
			final int aScale) {
		return aStream
			.map(MercatorProjection::projectPoint01)
			.map(OSMPoint::ofCenteredPoint)
			.map(p -> p.getAbsoluteCoordinates(aScale));
	}

	/** Converts points specified with geographical coordinates into coordinates of pixels in a whole world map in the given scale. Complete objects version.
	 * @param aStream the source of points
	 * @param aScale the scale of the target map
	 * @return
	 */
	public static PixelPath geographicalPathToPixelPath(
			final GeographicalPath aGeoPath,
			final int aScale) {
		Stream<PixelCoordinates> pixelStream = geographicalCoordinatesToPixelPointsStream(aGeoPath.stream(), aScale);
		return PixelPath.of(pixelStream);
	}
}
