package net.patrykczarnik.map_tools.programs;

import java.awt.image.BufferedImage;
import java.io.File;

import net.patrykczarnik.map_tools.graphic.ImageTools;
import net.patrykczarnik.map_tools.graphic.MapImageCreator;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 */
public class MapFromTiles {
	private static final String DEFAULT_REPOSITORY_PATTERN = "/home/patryk/osm/Polska/tile%02d_%04d_%04d.png";

	public static void main(String[] args) {
		if(args.length < 6) {
			printUsage();
			System.exit(1);
			return;
		}
		try {
			int scale = Integer.parseInt(args[0]);
			int x1 = Integer.parseInt(args[1]);
			int x2 = Integer.parseInt(args[2]);
			int y1 = Integer.parseInt(args[3]);
			int y2 = Integer.parseInt(args[4]);
			File file = new File(args[5]);
			String repositoryPattern = args.length >= 7 ? args[6] : DEFAULT_REPOSITORY_PATTERN;
			
			System.out.println("Processing...");
			MapImageCreator mapImageCreator = MapImageCreator.forRepositoryPattern(repositoryPattern);
			BufferedImage image = mapImageCreator.createImageFromTiles(scale, x1, x2, y1, y2);
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
				+ "java MapFromTiles scale x1 x2 y1 y2 file.png [repository_path]\n"
				+ " * scale - OSM map scale\n"
				+ " * x1 - horizontal starting tile number, inclusive\n"
				+ " * x2 - horizontal ending tile number, exclusive\n"
				+ " * y1 - vertical starting tile number, inclusive\n"
				+ " * y2 - vertical ending tile number, exclusive\n"
				+ " * file.png - output file name (or path)\n"
				+ " * repository_path - pattern for stored tile files, e.g. /osm/tile%02d_%04d_%04d.png\n";
		System.out.print(usage);
	}

}
