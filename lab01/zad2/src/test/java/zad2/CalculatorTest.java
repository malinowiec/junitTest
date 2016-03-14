package zad2;

import org.junit.Test;

import static org.junit.Assert.*;


public class CalculatorTest {

	Calculator calc = new Calculator();
	 double delta = 0.0001;
	
	@Test
	public void checkAdding() throws Exception {
		assertEquals(5, calc.add(2, 3), delta);
		assertEquals(10, calc.add(9, 1), delta);
		
		assertEquals(2.1, calc.add(1, 1.1), delta);
		
	}
	
	@Test
	public void checkSubtr() throws Exception {
		assertEquals(1, calc.sub(3, 2), delta);
		assertEquals(-3, calc.sub(11, 14), delta);
		
		assertEquals(-10.5, calc.sub(11, 21.5), delta);

	}
	
	@Test
	public void checkMulti() throws Exception {
		assertEquals(10, calc.multi(5, 2), delta);
		assertEquals(-3, calc.multi(-3, 1), delta);
		
		assertEquals(101.1, calc.multi(10.11, 10), delta);
	}
	
	@Test
	public void checkDiv() throws Exception {
		assertEquals(2, calc.div(4, 2), delta);
		assertEquals(-1, calc.div(-2, 2), delta);

		assertEquals(33.3333, calc.div(100, 3), delta);
	}
	
	@Test
	  public void testAssertTrue() throws Exception {
	    assertTrue(calc.greater(4, 3));
	    assertFalse(calc.greater(3, 4));
	  }
	
	
}

