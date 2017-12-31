package net.patrykczarnik.map_tools.gps;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.Collections;
import net.patrykczarnik.map_tools.exn.FileProcessingException;
import net.patrykczarnik.map_tools.geo.GeographicalCoordinates;
import net.patrykczarnik.map_tools.geo.GeographicalPath;
import net.patrykczarnik.map_tools.utils.XMLSupport;


/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * A utility class with support for GPX format.
 */
public class GpxReader {
	/** This class is not to be instantited. */
	private GpxReader() {
	}
	
	/** Reads a GPS path from a series of .gpx files and returns the result as a single GeographicalPath.
	 * @param aGpxFiles a collection of .gpx files provided as names/paths
	 * @return a fresh GeographicalPath object with points read from all the files
	 * @throws FileProcessingException in case of IO or XML errors
	 */
	public static GeographicalPath readGpsPath(Iterable<String> aGpxFiles) throws FileProcessingException {
		try {
			final GeographicalPath gpsPath = GeographicalPath.empty();
			final PathReadingHandler handler = new PathReadingHandler(gpsPath);
			for(String gpxFile : aGpxFiles) {
				XMLSupport.doSaxParsing(gpxFile, handler);
			}
			return gpsPath;
		} catch (Exception e) {
			throw new FileProcessingException("Error while reading GPX file", e);
		}
	}

	/** Reads a GPS path from a .gpx file and returns the result as a GeographicalPath.
	 * @param aGpxFile the name (/path) of the file to be read
	 * @return a fresh GeographicalPath object with points read from the file
	 * @throws FileProcessingException in case of IO or XML errors
	 */
	public static GeographicalPath readGpsPath(String aGpxFile) throws FileProcessingException {
		return readGpsPath(Collections.singleton(aGpxFile));
	}

	private static class PathReadingHandler extends DefaultHandler {
		private static final String GPX_NS = "http://www.topografix.com/GPX/1/1";
		private static final String GPX_POINT_NAME = "trkpt";
		private static final String LAT_ATTR_NAME = "lat";
		private static final String LON_ATTR_NAME = "lon";
		private final GeographicalPath fGeographicalPath;

		public PathReadingHandler(GeographicalPath aGpsPath) {
			this.fGeographicalPath = aGpsPath;
		}

		public final GeographicalPath getGeographicalPathPath() {
			return this.fGeographicalPath;
		}

		@Override
		public void startElement(final String aUri, final String aLocalName, final String aQName,
				final Attributes aAttributes) {
			if(GPX_NS.equals(aUri) && GPX_POINT_NAME.equals(aLocalName)) {
				final double lat = Double.parseDouble(aAttributes.getValue(LAT_ATTR_NAME));
				final double lon = Double.parseDouble(aAttributes.getValue(LON_ATTR_NAME));
				final GeographicalCoordinates point = GeographicalCoordinates.ofDegrees(lat, lon);
				this.fGeographicalPath.addPoint(point);
			}
		}
	}
}
