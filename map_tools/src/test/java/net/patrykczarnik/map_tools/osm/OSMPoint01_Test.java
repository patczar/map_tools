package net.patrykczarnik.map_tools.osm;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import net.patrykczarnik.map_tools.graphic.PixelCoordinates;

public class OSMPoint01_Test {

	@Test
	public void testof01_0() {
		final OSMPoint point = OSMPoint.of01Point(0.0, 0.0);		
		final OSMTile tile0 = point.getTile(0);
		final PixelCoordinates px0 = point.getCoordinatesWithinTile(0);
		assertThat(tile0.x).isEqualTo(0);
		assertThat(tile0.y).isEqualTo(0);
		assertThat(px0.x).isEqualTo(0);
		assertThat(px0.y).isEqualTo(0);

		final OSMTile tile3 = point.getTile(3);
		final PixelCoordinates px3 = point.getCoordinatesWithinTile(3);
		assertThat(tile3.x).isEqualTo(0);
		assertThat(tile3.y).isEqualTo(0);
		assertThat(px3.x).isEqualTo(0);
		assertThat(px3.y).isEqualTo(0);
	}

	@Test
	public void testof01_05() {
		final OSMPoint point = OSMPoint.of01Point(0.5, 0.25);		
		final OSMTile tile0 = point.getTile(0);
		final PixelCoordinates px0 = point.getCoordinatesWithinTile(0);
		assertThat(tile0.x).isEqualTo(0);
		assertThat(tile0.y).isEqualTo(0);
		assertThat(px0.x).isEqualTo(128);
		assertThat(px0.y).isEqualTo(64);

		final OSMTile tile1 = point.getTile(1);
		final PixelCoordinates px1 = point.getCoordinatesWithinTile(1);
		assertThat(tile1.x).isEqualTo(1);
		assertThat(tile1.y).isEqualTo(0);
		assertThat(px1.x).isEqualTo(0);
		assertThat(px1.y).isEqualTo(128);

		final OSMTile tile2 = point.getTile(2);
		final PixelCoordinates px2 = point.getCoordinatesWithinTile(2);
		assertThat(tile2.x).isEqualTo(2);
		assertThat(tile2.y).isEqualTo(1);
		assertThat(px2.x).isEqualTo(0);
		assertThat(px2.y).isEqualTo(0);
	}
	
	@Test
	public void testofCenteredPoint() {
		final OSMPoint point = OSMPoint.ofCenteredPoint(0.0, 0.0);
		assertThat(point).isNotNull();
		final OSMTile tile1 = point.getTile(1);
		final PixelCoordinates px1 = point.getCoordinatesWithinTile(1);
		assertThat(tile1.x).isEqualTo(1);
		assertThat(tile1.y).isEqualTo(1);
		assertThat(px1.x).isEqualTo(0);
		assertThat(px1.y).isEqualTo(0);
	}
}
