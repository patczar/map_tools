package net.patrykczarnik.map_tools.osm;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class OSMPointTest {
	private final OSMPoint point0 = OSMPoint.of(0, 0);
	private final OSMPoint point1 = OSMPoint.of(1023, 2048);
	private final OSMPoint point2 = OSMPoint.of(0x3FFF_FFFF, 0x1000_0000);

	@Test
	public void testGetTile_0_0() {
		final OSMTile tile = point0.getTile(0);
		assertThat(tile).isNotNull();
		assertThat(tile.x).isEqualTo(0);
		assertThat(tile.y).isEqualTo(0);
	}

	@Test
	public void testGetTile_0_5() {
		final OSMTile tile = point0.getTile(5);
		assertThat(tile).isNotNull();
		assertThat(tile.x).isEqualTo(0);
		assertThat(tile.y).isEqualTo(0);
	}

	@Test
	public void testGetTile_1_0() {
		final OSMTile tile = point1.getTile(0);
		assertThat(tile).isNotNull();
		assertThat(tile.x).isEqualTo(0);
		assertThat(tile.y).isEqualTo(0);
	}

	@Test
	public void testGetTile_1_5() {
		final OSMTile tile = point1.getTile(5);
		assertThat(tile).isNotNull();
		assertThat(tile.x).isEqualTo(0);
		assertThat(tile.y).isEqualTo(0);
	}

	@Test
	public void testGetTile_1_22() {
		final OSMTile tile = point1.getTile(22);
		assertThat(tile).isNotNull();
		assertThat(tile.x).isEqualTo(1);
		assertThat(tile.y).isEqualTo(4);
	}

	@Test
	public void testGetTile_2_0() {
		final OSMTile tile = point2.getTile(0);
		assertThat(tile).isNotNull();
		assertThat(tile.x).isEqualTo(0);
		assertThat(tile.y).isEqualTo(0);
	}


	@Test
	public void testGetTile_2_2() {
		final OSMTile tile = point2.getTile(2);
		assertThat(tile).isNotNull();
		assertThat(tile.x).isEqualTo(1);
		assertThat(tile.y).isEqualTo(0);
	}

	@Test
	public void testGetTile_2_4() {
		final OSMTile tile = point2.getTile(4);
		assertThat(tile).isNotNull();
		assertThat(tile.x).isEqualTo(7);
		assertThat(tile.y).isEqualTo(2);
	}
}
