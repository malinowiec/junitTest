package zad1b;

import static org.junit.Assert.*;

import org.junit.Test;


public class CalculatorTest {

	Calculator calc = new Calculator();
	
	@Test
	public void checkAdding() {
		assertEquals(5, calc.add(2, 3));
		System.out.println("Adding ");
	}
	
	@Test
	public void checkSubtr() {
		assertEquals(1, calc.sub(3, 2));
		System.out.println("Substr ");
	}
	
	@Test
	public void checkMulti() {
		assertEquals(10, calc.multi(5, 2));
		System.out.println("Multi ");
	}
	
	@Test(expected = ArithmeticException.class)
	public void checkDiv() {
		assertEquals(2, calc.div(4, 0));
	}
	
	@Test
	  public void testAssertTrue() {
	    assertTrue(calc.greater(4, 3));
	    System.out.println("Greater ");
	  }
	
	
}
