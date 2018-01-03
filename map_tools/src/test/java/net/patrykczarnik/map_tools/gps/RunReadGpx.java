package net.patrykczarnik.map_tools.gps;

import net.patrykczarnik.map_tools.exn.FileProcessingException;
import net.patrykczarnik.map_tools.geo.GeographicalPath;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * A test program that reads a GPX track.
 */
public class RunReadGpx {
	private static final String FILE = "examples/track1.gpx";

	public static void main(String[] args) {
		System.out.println("Starting...");
		try {
			GeographicalPath path = GpxReader.readGpsPath(FILE);
			System.out.println("Succesfully read path of size: " + path.getSize());
			System.out.println(path);
		} catch(FileProcessingException e) {
			System.out.println("Error during reading: " + e);
			e.printStackTrace();
		}
	}
}
