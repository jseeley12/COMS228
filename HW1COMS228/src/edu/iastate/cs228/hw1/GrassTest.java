package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * 
 * @author Joshua Seeley
 *
 */
public class GrassTest {

	@Test
	public void whoTest() {
		Plain y = new Plain(2);
		Plain x = new Plain(2);
		Grass bob = new Grass(x, 1, 1);
		assertEquals(State.GRASS, bob.who());
	}
	
	
	@Test
	public void rulesTest() throws FileNotFoundException {
		Plain w = new Plain("C:\\Users\\j\\Downloads\\public\\public2-6x6.txt");
		assertEquals(State.GRASS, w.grid[5][0].next(w).who());
	}

}
