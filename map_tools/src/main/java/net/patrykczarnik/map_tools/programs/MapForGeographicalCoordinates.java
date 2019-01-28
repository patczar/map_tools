package net.patrykczarnik.map_tools.programs;

import java.awt.image.BufferedImage;
import java.io.File;

import net.patrykczarnik.map_tools.geo.GeographicalCoordinates;
import net.patrykczarnik.map_tools.graphic.ImageTools;
import net.patrykczarnik.map_tools.graphic.MapImageCreator;
import net.patrykczarnik.map_tools.osm.MercatorProjection;
import net.patrykczarnik.map_tools.osm.OSMPoint;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 */
public class MapForGeographicalCoordinates {
	private static final String DEFAULT_REPOSITORY_PATTERN = "/home/patryk/osm/Polska/tile%02d_%04d_%04d.png";

	public static void main(String[] args) {
		if(args.length < 6) {
			printUsage();
			System.exit(1);
			return;
		}
		try {
			int scale = Integer.parseInt(args[0]);
			double north = Double.parseDouble(args[1]);
			double south = Double.parseDouble(args[2]);
			double west = Double.parseDouble(args[3]);
			double east = Double.parseDouble(args[4]);
			File file = new File(args[5]);
			int margin = args.length >= 7 ? Integer.parseInt(args[6]) : 0;
			String repositoryPattern = args.length >= 8 ? args[7] : DEFAULT_REPOSITORY_PATTERN;
			
			System.out.println("Processing...");
			GeographicalCoordinates geoLT = GeographicalCoordinates.ofDegrees(north, west);
			GeographicalCoordinates geoRB = GeographicalCoordinates.ofDegrees(south, east);
			OSMPoint pointLT = OSMPoint.ofCenteredPoint(MercatorProjection.projectPoint01(geoLT));
			OSMPoint pointRB = OSMPoint.ofCenteredPoint(MercatorProjection.projectPoint01(geoRB)).nextDiagonally();
			
			MapImageCreator mapImageCreator = MapImageCreator.forRepositoryPattern(repositoryPattern);
			BufferedImage image = mapImageCreator.createImageForCorners(scale, pointLT, pointRB, margin);
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
				+ "java MapFromTiles scale north south west east file.png [margin] [repository_path]\n"
				+ " * scale - OSM map scale\n"
				+ " * north - latitude of the north border of the map\n"
				+ " * south - latitude of the south border of the map\n"
				+ " * west - longitude of the west border of the map\n"
				+ " * east - longitude of the east border of the map\n"
				+ " * file.png - output file name (or path)\n"
				+ " * margin - number of additional pixel around the area\n"
				+ " * repository_path - pattern for stored tile files, e.g. /osm/tile%02d_%04d_%04d.png\n";
		System.out.print(usage);
	}

}
