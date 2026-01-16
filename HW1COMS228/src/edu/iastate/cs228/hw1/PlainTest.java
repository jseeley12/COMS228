package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * 
 * @author joshua Seeley
 *
 */
public class PlainTest {

	@Test
	public void testbasicwidth() {
		Plain p = new Plain(3);
		assertEquals(3, p.getWidth());
	}
	@Test
	public void testgetWidth() {
		Plain p = new Plain(72);
		int width = p.getWidth();
		assertEquals(72, width);
	}
	@Test
	public void testRandomGrid() {
		Plain p = new Plain(5);
		Plain y = new Plain(5);
		p.randomInit();
		y = p;
		assertEquals(p, y);
	}
	@Test 
	public void testString() throws FileNotFoundException {
		Plain w = new Plain("C:\\Users\\j\\Downloads\\public\\public2-6x6.txt");
		String yes = w.toString();
		assertEquals("F0 E  E  F0 E  E  \n"
					+"B0 F0 B0 R0 G  R0 \n"
					+"R0 E  R0 B0 B0 G  \n"
					+"B0 E  E  R0 F0 E  \n"
					+"B0 E  E  G  E  R0 \n"
					+"G  G  E  B0 R0 E  \n", yes);
	}
	@Test
	public void testInputFile() throws FileNotFoundException {
		Plain w = new Plain("C:\\Users\\j\\Downloads\\public\\public2-6x6.txt");
		Plain y = new Plain(6);
		Wildlife.updatePlain(w, y);
		assertNotEquals(y, w); //plain should not equal due to it being updated to next cycle
	}
}
