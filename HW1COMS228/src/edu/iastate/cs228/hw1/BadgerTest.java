package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Joshua Seeley
 *
 */
public class BadgerTest {
	//Plain w = new Plain("C:\\Users\\j\\OneDrive\\Documents\\public1-3x3.txt");
	
	@Test
	public void whoTest() {
		Plain y = new Plain(2);
		Plain x = new Plain(2);
		Badger bob = new Badger(x, 1, 1, 0);
		assertEquals(State.BADGER, bob.who());
	}
	@Test
	public void rulesTest() {
		Plain y = new Plain(2);
		Plain x = new Plain(2);
		x.randomInit();
		Fox bob = new Fox(x, 0, 0, 0);
		
		Badger frank = new Badger(x, 0, 1, 4);
		Badger chad = new Badger(x, 1, 0, 0);
		Fox lad = new Fox(x, 1, 1, 0);
		x.grid[0][0] = bob;
		x.grid[0][1] = frank;
		x.grid[1][0] = chad;
		x.grid[1][1] = lad;
		Wildlife.updatePlain(x, x);
		assertEquals(State.EMPTY, x.grid[0][1].who());
		
	}
	@Test
	public void rulesTest2() throws FileNotFoundException {
		Plain w = new Plain("C:\\Users\\j\\OneDrive\\Documents\\3x3-fun.txt");
		assertEquals(State.BADGER, w.grid[0][2].next(w).who());
	}

}
