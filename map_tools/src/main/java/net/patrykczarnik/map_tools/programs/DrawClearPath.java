package net.patrykczarnik.map_tools.programs;

import java.awt.image.BufferedImage;
import java.io.File;

import net.patrykczarnik.map_tools.geo.GeographicalPath;
import net.patrykczarnik.map_tools.gps.GpxReader;
import net.patrykczarnik.map_tools.graphic.ImageTools;
import net.patrykczarnik.map_tools.graphic.PathImageCreator;
import net.patrykczarnik.map_tools.graphic.PixelPath;
import net.patrykczarnik.map_tools.osm.Convertions;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 */
public class DrawClearPath {
	public static void main(String[] args) {
		if(args.length < 3) {
			printUsage();
			System.exit(1);
			return;
		}
		try {
			int scale = Integer.parseInt(args[0]);
			String gpxFile = args[1];
			File file = new File(args[2]);
			int margin = args.length >= 4 ? Integer.parseInt(args[3]) : 0;
			
			System.out.println("Processing...");
			GeographicalPath geoPath = GpxReader.readGpsPath(gpxFile);
			System.out.println(geoPath);
			PixelPath pixelPath = Convertions.geographicalPathToPixelPath(geoPath, scale);
			System.out.println(pixelPath);
			
			PathImageCreator pathImageCreator = PathImageCreator.withDefaultSettings();
			BufferedImage image = pathImageCreator.drawEmpty(pixelPath, margin);
			ImageTools.write(image, file);
			System.out.println("Wrote the map image to " + file);
		} catch(NumberFormatException e) {
			System.out.println("Incorrect arguments.");
			System.out.println(e);
			System.exit(1);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(2);
		}
	}

	private static void printUsage() {
		final String usage = "Usage:\n"
				+ "java DrawClearTrack scale track.gpx file.png [margin] [repository_path]\n"
				+ " * scale - OSM map scale\n"
				+ " * track.gpx - the file with the GPS track to draw\n"
				+ " * file.png - output file name (or path)\n"
				+ " * margin - number of additional pixel around the area\n";
		System.out.print(usage);
	}

}
