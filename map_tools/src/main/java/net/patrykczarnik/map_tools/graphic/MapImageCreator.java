package net.patrykczarnik.map_tools.graphic;

import static net.patrykczarnik.map_tools.osm.OSMConstants.PIXELS_OF_TILE;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import net.patrykczarnik.map_tools.exn.FileProcessingException;
import net.patrykczarnik.map_tools.exn.NoSuchTileException;
import net.patrykczarnik.map_tools.osm.OSMTile;
import net.patrykczarnik.map_tools.repository.FileTileProvider;
import net.patrykczarnik.map_tools.repository.ITileProvider;

public class MapImageCreator {
	private final String fRepositoryPattern;
	private final ITileProvider fTileProvider;

	private MapImageCreator(String aRepositoryPattern, ITileProvider aTileProvider) {
		fRepositoryPattern = aRepositoryPattern;
		fTileProvider = aTileProvider;
	}

	public static MapImageCreator forRepositoryPattern(String aRepositoryPattern) {
		ITileProvider tileProvider = FileTileProvider.withPathPattern(aRepositoryPattern);
		return new MapImageCreator(aRepositoryPattern, tileProvider);
	}

	public BufferedImage createImageFromTiles(int aScale, int x1, int x2, int y1, int y2) throws NoSuchTileException, FileProcessingException {
		final int width = PIXELS_OF_TILE * (x2 - x1);
		final int height = PIXELS_OF_TILE * (y2 - y1);
		final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		final Graphics2D g = image.createGraphics();
		for(int x = x1; x < x2; x++) {
			for(int y = y1; y < y2; y++) {
				System.out.printf("%4d %4d\n", x, y);
				final OSMTile tile = OSMTile.ofCoordinates(aScale, x, y);
				final BufferedImage tileImage = fTileProvider.getImageForTile(tile);
				if(! g.drawImage(tileImage, PIXELS_OF_TILE * (x - x1), PIXELS_OF_TILE * (y - y1), null)) {
					throw new FileProcessingException("drawImage returned FALSE for " + x + " " + y);
				}
			}
		}
		return image;
	}

}
