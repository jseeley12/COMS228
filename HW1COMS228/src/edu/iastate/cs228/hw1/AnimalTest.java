package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Joshua Seeley
 */
public class AnimalTest {

	public void AnimalTest(){
		
		
	}
	@Test
	public void AgeTest() {
		
		Plain x = new Plain(2);
		Fox bob = new Fox(x, 1, 1, 2);
		assertEquals(2, bob.myAge());
	}
	
}
