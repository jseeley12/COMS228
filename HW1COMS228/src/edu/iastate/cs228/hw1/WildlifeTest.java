package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * 
 * @author joshua seeley
 *
 */
public class WildlifeTest {

	@Test
	public void testingUpdatePlain() throws FileNotFoundException {
		Plain w = new Plain("C:\\Users\\j\\Downloads\\public\\public2-6x6.txt");
		Plain y = new Plain(6);
		Wildlife.updatePlain(w, y);
		assertEquals("E  F0 F0 E  R0 G  \n"
				   + "F0 E  E  F0 G  F0 \n"
				   + "E  R0 E  E  B1 G  \n"
				   + "E  R0 R0 F0 B0 G  \n"
				   + "E  B0 G  G  R0 E  \n"
				   + "G  G  G  B1 R1 R0 \n", y.toString());
	}
}
