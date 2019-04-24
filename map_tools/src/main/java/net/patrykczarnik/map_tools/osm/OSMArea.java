package net.patrykczarnik.map_tools.osm;

public class OSMArea {
	private final int fMinX, fMaxX, fMinY, fMaxY;

	private OSMArea(int aMinX, int aMaxX, int aMinY, int aMaxY) {
		fMinX = aMinX;
		fMaxX = aMaxX;
		fMinY = aMinY;
		fMaxY = aMaxY;
	}
	
	public static OSMArea of(int aMinX, int aMaxX, int aMinY, int aMaxY) {
		return new OSMArea(aMinX, aMaxX, aMinY, aMaxY);
	}

	public int getMinX() {
		return fMinX;
	}

	public int getMaxX() {
		return fMaxX;
	}

	public int getMinY() {
		return fMinY;
	}

	public int getMaxY() {
		return fMaxY;
	}
	
	public int getWidth() {
		return fMaxX - fMinX;
	}

	public int getHeight() {
		return fMaxY - fMinY;
	}
	
	public OSMPoint getLTCorner() {
		return OSMPoint.of(fMinX, fMinY);
	}
	
	public OSMPoint getRBCorner() {
		return OSMPoint.of(fMaxX, fMaxY);
	}
	
	public OSMTileSet getTileSet(int aScale) {
		OSMTile ltTile = getLTCorner().getTile(aScale);
		OSMTile rbTile = getRBCorner().getTile(aScale);
		return OSMTileSet.of(ltTile, rbTile);
	}
	
	@Override
	public String toString() {
		return "OSMPath.Bounds [x: " + fMinX + "↔" + fMaxX + ", y:" + fMinY + "↔" + fMaxY + "]";
	}

}