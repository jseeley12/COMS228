package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * 
 * @author joshua seeley
 *
 */
public class RabbitTest {

	@Test
	public void whoTest() {
		Plain y = new Plain(2);
		Plain x = new Plain(2);
		Rabbit bob = new Rabbit(x, 1, 1, 0);
		assertEquals(State.RABBIT, bob.who());
	}
	
	
	@Test
	public void rulesTest1() throws FileNotFoundException {
		Plain w = new Plain("C:\\Users\\j\\Downloads\\public\\public2-6x6.txt");
		assertEquals(State.EMPTY, w.grid[3][0].next(w).who());
	}

}
