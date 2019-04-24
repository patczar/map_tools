package net.patrykczarnik.map_tools.osm;

import java.util.ArrayList;
import java.util.List;

import net.patrykczarnik.map_tools.graphic.PixelCoordinates;
import net.patrykczarnik.map_tools.graphic.PixelPath;

public class OSMTileSet {
	private final int fScale;
	private final int fMinX, fMaxX, fMinY, fMaxY;
	
	private OSMTileSet(int aScale, int aMinX, int aMaxX, int aMinY, int aMaxY) {
		fScale = aScale;
		fMinX = aMinX;
		fMaxX = aMaxX;
		fMinY = aMinY;
		fMaxY = aMaxY;
	}

	public static OSMTileSet of(OSMTile aLtTile, OSMTile aRbTile) {
		return new OSMTileSet(aLtTile.scale, aLtTile.x, aRbTile.x, aLtTile.y, aRbTile.y);
	}
	
	public OSMTile getLT_Tile() {
		return OSMTile.ofCoordinates(fScale, fMinX, fMinY);
	}
	
	public OSMTile getRB_Tile() {
		return OSMTile.ofCoordinates(fScale, fMaxX, fMaxY);
	}
	
	public PixelPath pixelPathFromOSMPath(OSMPath aPath) {
		final OSMTile lt_Tile = getLT_Tile();
		final List<OSMPoint> points = aPath.getPoints();
		final ArrayList<PixelCoordinates> list = new ArrayList<>(points.size());
		for (OSMPoint p : points) {
			list.add(p.getCoordinatesRelativeToTile(lt_Tile));
		}
		return PixelPath.of(list);
	}
	
	public OSMArea getOSMArea() {
		final PixelCoordinates ltCoordinates = getLT_Tile().getAbsolutePixelCoordinates();
		final PixelCoordinates rbCoordinates = getRB_Tile().getAbsolutePixelCoordinates();
		return OSMArea.of(ltCoordinates.x, rbCoordinates.x, ltCoordinates.y, rbCoordinates.y);
	}

}
