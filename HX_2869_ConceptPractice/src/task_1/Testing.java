package task_1;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class Testing {
	A a = new A();
	@Test
	public void testAdd() {
		assertEquals(8,a.add(5,3));
	}
	@Test
	public void testSub() {
		assertEquals(14,a.sub(20,5));
	}
}
