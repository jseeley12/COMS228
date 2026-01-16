package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * 
 * @author Joshua Seeley
 *
 * Junit class testing the class
 * -Living
 */
public class LivingTest {

	@Test
	public void censustest() throws FileNotFoundException {
		int population[] = new int[Living.NUM_LIFE_FORMS];
		Plain w = new Plain("C:\\Users\\j\\Downloads\\public\\public2-6x6.txt");
		w.grid[3][5].census(population);
		assertEquals(State.EMPTY, w.grid[3][5].who());
	}
}
