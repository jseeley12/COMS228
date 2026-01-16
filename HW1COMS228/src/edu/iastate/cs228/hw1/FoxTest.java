package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * 
 * @author Joshua Seeley
 *
 */
public class FoxTest {

	@Test
	public void whoTest() {
		
		Plain x = new Plain(2);
		Fox bob = new Fox(x, 1, 1, 0);
		assertEquals(State.FOX, bob.who());
	}
	
	
	@Test
	public void rulesTest1() throws FileNotFoundException {
		Plain w = new Plain("C:\\Users\\j\\Downloads\\public\\public2-6x6.txt");
		assertEquals(State.EMPTY, w.grid[0][0].next(w).who());
	}

}
