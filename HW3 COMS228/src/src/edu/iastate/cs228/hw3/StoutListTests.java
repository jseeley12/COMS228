package src.edu.iastate.cs228.hw3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import java.util.AbstractSequentialList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.w3c.dom.Node;

public class StoutListTests {

	

	@Test
	public void pdfExample() {
		
		StoutList<String> n = new StoutList<String>();
		n.add("A");
		n.add("C");
		n.add("D");
		n.add("E");
		n.remove(1);
		assertEquals(3, n.size());
	}	
	@Test
	public void addremove() {
	StoutList<String> n = new StoutList<String>();
	n.add("A");
		n.add("B");
		
		n.add("C");
		n.add("D");
		n.add("E");
		n.add("F");
		n.add("G");
		n.add("H");
		n.remove(1);
		assertEquals("[(A, C, D, -), (E, F, G, H)]", n.toStringInternal());
	
	}
	@Test
	public void checkAddWithIndicie() {
		StoutList<String> n = new StoutList<String>();
		n.add("A");
		n.add("B");
		
		n.add("C");
		n.add("D");
		n.add("E");
		n.add("F");
		n.remove(1);
		n.add(3, "L");
		assertEquals("[(A, C, D, L), (E, F, -, -)]", n.toStringInternal());
		
		
	}
	@Test 
	public void checkNext() {
		StoutList<String> n = new StoutList<String>();
		n.add("A");
		n.add("B");
		
		
	}

}
