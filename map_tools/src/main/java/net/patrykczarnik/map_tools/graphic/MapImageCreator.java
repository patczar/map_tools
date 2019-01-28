package net.patrykczarnik.map_tools.graphic;

import static net.patrykczarnik.map_tools.osm.OSMConstants.PIXELS_OF_TILE;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import net.patrykczarnik.map_tools.exn.FileProcessingException;
import net.patrykczarnik.map_tools.exn.NoSuchTileException;
import net.patrykczarnik.map_tools.osm.OSMPoint;
import net.patrykczarnik.map_tools.osm.OSMTile;
import net.patrykczarnik.map_tools.repository.FileTileProvider;
import net.patrykczarnik.map_tools.repository.ITileProvider;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * Object of this class is able to create map images.
 */
public class MapImageCreator {
	private final ITileProvider fTileProvider;

	private MapImageCreator(ITileProvider aTileProvider) {
		fTileProvider = aTileProvider;
	}

	public static MapImageCreator forRepositoryPattern(String aRepositoryPattern) {
		ITileProvider tileProvider = FileTileProvider.withPathPattern(aRepositoryPattern);
		return new MapImageCreator(tileProvider);
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
	
	/** Generates map of the specified area.
	 *  Equivalent to {@link MapImageCreator#createImageForCorners(int, OSMPoint, OSMPoint, int)} with <code>aMargin</code> equal 0. 
	 */
	public BufferedImage createImageForCorners(int aScale, OSMPoint aLeftTop, OSMPoint aRightBottom) throws NoSuchTileException, FileProcessingException {
		OSMTile ltTile = aLeftTop.getTile(aScale);
		OSMTile rbTile = aRightBottom.getTile(aScale);
		PixelCoordinates ltOffset = aLeftTop.getCoordinatesRelativeToTile(ltTile);
		PixelCoordinates rbOffset = aRightBottom.getCoordinatesRelativeToTile(ltTile);
		
		BufferedImage image = createImageFromTiles(aScale, ltTile.x, rbTile.x+1, ltTile.y, rbTile.y+1);
		image = image.getSubimage(ltOffset.x, ltOffset.y, rbOffset.x - ltOffset.x, rbOffset.y - ltOffset.y);
		return image;
	}

	/** Generates map of the specified area.
	 * This version accepts the coordinates as OSMPoint, that is as pixels of the virtual whole world map in the highest scale.
	 * It also allows to specify an additional margin around the area.
	 * 
	 * @param aScale the scale of the requested map
	 * @param aLeftTop the left-top corner (included in whe result)
	 * @param aRightBottom the right-bottom corner (excluded from the map)
	 * @param aMargin margin around the requested area specified in pixels of the result image
	 * @return an image representing the requested map fragment
	 * @throws NoSuchTileException
	 * @throws FileProcessingException
	 */
	public BufferedImage createImageForCorners(int aScale, OSMPoint aLeftTop, OSMPoint aRightBottom, int aMargin) throws NoSuchTileException, FileProcessingException {
		return createImageForCorners(aScale,
				aLeftTop.movedInScale(-aMargin, -aMargin, aScale),
				aRightBottom.movedInScale(aMargin, aMargin, aScale));
	}
	
}
