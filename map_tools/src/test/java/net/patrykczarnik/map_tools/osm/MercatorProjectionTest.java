package net.patrykczarnik.map_tools.osm;

import static org.assertj.core.api.Assertions.*;

import static org.assertj.core.data.Offset.offset;
import org.junit.Test;

public class MercatorProjectionTest {

	@Test
	public void testProjectLatitude0() {
		final double result = MercatorProjection.projectLatitude(0.0);
		assertThat(result).isCloseTo(0.0, offset(1E-6));
	}

	@Test
	public void testProjectLatitude1() {
		final double result = MercatorProjection.projectLatitude(Math.toRadians(85.0));
		assertThat(result).isCloseTo(1.0, offset(0.1));
	}

	@Test
	public void testProjectLatitudeM1() {
		final double result = MercatorProjection.projectLatitude(Math.toRadians(-85.0));
		assertThat(result).isCloseTo(-1.0, offset(0.1));
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

}
