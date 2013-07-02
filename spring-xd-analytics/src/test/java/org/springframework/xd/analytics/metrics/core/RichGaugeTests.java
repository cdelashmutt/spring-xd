package org.springframework.xd.analytics.metrics.core;

import static org.junit.Assert.*;

import java.util.HashSet;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

/**
 * @author Luke Taylor
 */
public class RichGaugeTests {
	private static double D = 1.0E-5;

	@Test
	public void valueMeanMinAndMaxAreCorrect() throws Exception {
		RichGauge g = new RichGauge("blah", 1.0);
		assertEquals(1.0, g.getValue(), D);
		assertEquals(1.0, g.getMax(), D);
		assertEquals(1.0, g.getMin(), D);
		assertEquals(1.0, g.getAverage(), D);

		g.set(1.5);
		assertEquals(1.25, g.getAverage(), D);
		g.set(0.5);

		assertEquals(3, g.getCount());
		assertEquals(0.5, g.getValue(), D);
		assertEquals(1.5, g.getMax(), D);
		assertEquals(0.5, g.getMin(), D);
		assertEquals(1.0, g.getAverage(), D);
	}

	@Test
	public void resetWorks() throws Exception {
		RichGauge g = new RichGauge("blah", 99.999);
		g.set(199.997);

		g.reset();
		assertEquals(0.0, g.getMax(), D);
		assertEquals(0.0, g.getMin(), D);
		assertEquals(0.0, g.getAverage(), D);
		assertEquals(0.0, g.getValue(), D);
		assertEquals(0, g.getCount());
	}

	// Data from http://www.itl.nist.gov/div898/handbook/pmc/section4/pmc431.htm
	@Test
	public void testExponentialMovingAverage() throws Exception {
		RichGauge g = new RichGauge("blah");
		g.setAlpha(0.1);
		g.set(71.0);
		assertEquals(71.0, g.getAverage(), D);
		g.set(70.0);
		assertEquals(71.0, g.getAverage(), D);
		g.set(69.0);
		assertEquals(70.9, g.getAverage(), D);
		g.set(68.0);
		assertEquals(70.71, g.getAverage(), D);

	}

	@Test
	public void equalsContract() {
		EqualsVerifier.forClass(RichGauge.class).suppress(Warning.NULL_FIELDS).verify();
	}

	@Test
	public void equalsAndHashcodeWorkForSetStorage() throws Exception {
		RichGauge g = new RichGauge("myGauge", 9.82);
		HashSet<RichGauge> set = new HashSet<RichGauge>();
		set.add(g);
		g.set(99.9);
		assertTrue(set.contains(g));
	}
}
