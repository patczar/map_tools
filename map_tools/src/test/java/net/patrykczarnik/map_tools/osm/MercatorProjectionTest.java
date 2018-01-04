package net.patrykczarnik.map_tools.osm;

import static org.assertj.core.api.Assertions.*;

import static org.assertj.core.data.Offset.offset;
import org.junit.Test;

import net.patrykczarnik.map_tools.geo.GeographicalCoordinates;

public class MercatorProjectionTest {

	@Test
	public void testLatitudeRange() {
		assertThat(MercatorProjection.LATITUDE_RANGE_DEGREES).isCloseTo(85.05113, offset(1E-4));
	}
	
	@Test
	public void testProjectLatitude0() {
		final double result = MercatorProjection.projectLatitude(0.0);
		assertThat(result).isCloseTo(0.0, offset(1E-6));
	}

	@Test
	public void testProjectLatitude1() {
		final double result = MercatorProjection.projectLatitude(MercatorProjection.LATITUDE_RANGE_RADIANS);
		assertThat(result).isCloseTo(1.0, offset(1E-6));
	}

	@Test
	public void testProjectLatitudeM1() {
		final double result = MercatorProjection.projectLatitude(-MercatorProjection.LATITUDE_RANGE_RADIANS);
		assertThat(result).isCloseTo(-1.0, offset(1E-6));
	}

	@Test
	public void testProjectLongitude0() {
		final double result = MercatorProjection.projectLongitude(0.0);
		assertThat(result).isZero();
	}

	@Test
	public void testProjectLongitude90() {
		final double result = MercatorProjection.projectLongitude(Math.toRadians(90.0));
		assertThat(result).isCloseTo(0.5, offset(1E-3));
	}

	@Test
	public void testProjectPoint0() {
		final GeographicalCoordinates coords = GeographicalCoordinates.ofDegrees(0.0, 0.0);
		final PlainPoint point = MercatorProjection.projectPoint01(coords);
		assertThat(point).isNotNull();
		assertThat(point.x).isCloseTo(0.0, offset(1E-6));
		assertThat(point.y).isCloseTo(0.0, offset(1E-6));
	}

	@Test
	public void testProjectPoint1() {
		final GeographicalCoordinates coords = GeographicalCoordinates.ofDegrees(-85.0, -45.0);
		final PlainPoint point = MercatorProjection.projectPoint01(coords);
		assertThat(point).isNotNull();
		assertThat(point.x).isCloseTo(-0.25, offset(1E-6));
		assertThat(point.y).isGreaterThan(-1.0)
			.isCloseTo(-1.0, offset(1E-2));
	}

	@Test
	public void testProjectPointExtreme() {
		final GeographicalCoordinates coords = GeographicalCoordinates.ofDegrees(MercatorProjection.LATITUDE_RANGE_DEGREES, 180.0);
		final PlainPoint point = MercatorProjection.projectPoint01(coords);
		assertThat(point).isNotNull();
		assertThat(point.x).isCloseTo(1.0, offset(1E-6));
		assertThat(point.y).isLessThanOrEqualTo(1.0)
			.isCloseTo(1.0, offset(1E-9));
	}
}
