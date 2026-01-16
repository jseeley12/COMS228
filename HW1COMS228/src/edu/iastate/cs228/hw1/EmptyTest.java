package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * 
 * @author Joshua Seeley
 *
 */
public class EmptyTest {

	@Test
	public void whoTest() {
		Plain y = new Plain(2);
		Plain x = new Plain(2);
		Empty bob = new Empty(x, 1, 1);
		assertEquals(State.EMPTY, bob.who());
	}
	
	
	@Test
	public void rulesTest1() throws FileNotFoundException {
		Plain w = new Plain("C:\\Users\\j\\Downloads\\public\\public2-6x6.txt");
		assertEquals(State.BADGER, w.grid[4][1].next(w).who());
	}

}
