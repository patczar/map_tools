package net.patrykczarnik.map_tools.repository;

import java.awt.image.BufferedImage;

import net.patrykczarnik.map_tools.exn.FileProcessingException;
import net.patrykczarnik.map_tools.exn.NoSuchTileException;
import net.patrykczarnik.map_tools.osm.OSMTile;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 */
public interface ITileProvider {

	public BufferedImage getImageForTile(OSMTile aTile) throws NoSuchTileException, FileProcessingException;
	
}
