package jUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestAdd {
	@Test
	public void testAdd() {
		assertEquals(8,jUnitClass.add(3,5));
	}
}
