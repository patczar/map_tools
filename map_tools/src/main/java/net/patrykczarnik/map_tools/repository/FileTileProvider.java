package net.patrykczarnik.map_tools.repository;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.patrykczarnik.map_tools.exn.FileProcessingException;
import net.patrykczarnik.map_tools.exn.NoSuchTileException;
import net.patrykczarnik.map_tools.graphic.ImageTools;
import net.patrykczarnik.map_tools.osm.OSMTile;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 */
public class FileTileProvider implements ITileProvider {
	private String pathPattern;
	
	private FileTileProvider(String aPathPattern) {
		this.pathPattern = aPathPattern;
	}

	public static FileTileProvider withPathPattern(String aPathPattern) {
		return new FileTileProvider(aPathPattern);
	}

	public BufferedImage getImageForTile(OSMTile aTile) throws NoSuchTileException, FileProcessingException {
		final String path = String.format(pathPattern, aTile.scale, aTile.x, aTile.y);
		
		try {
			return ImageTools.read(path);
		} catch (FileNotFoundException e) {
			throw new NoSuchTileException("Tile " + aTile + " cannot be found", e);
		} catch (IOException e) {
			throw new FileProcessingException("Tile " + aTile + " cannot be read", e);			
		}
	}

}
