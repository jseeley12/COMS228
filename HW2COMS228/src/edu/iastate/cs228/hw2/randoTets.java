package edu.iastate.cs228.hw2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.Before;

public class randoTets {
	Point[] points;

	@Before
	public void SetupPoints() {
		Random rand = new Random();
		points = new Point[rand.nextInt(41) + 10];
		for (int i = 0; i < points.length; i++) {
			points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
	}

	@Test
	public void InsertionSortTestX() {
		Point.xORy = true;

		InsertionSorter insertionSorter = new InsertionSorter(points);
		insertionSorter.setComparator(0);
		insertionSorter.sort();
		boolean sorted = true;

		for (int i = 1; i < insertionSorter.points.length; i++) {
			
			if(insertionSorter.pointComparator.compare(insertionSorter.points[i-1], insertionSorter.points[i]) > 0) {
				sorted = false;
			}
		}
		assertEquals(true, sorted);

	}

	@Test
	public void SelectionSortTestX() {
		Point.xORy = true;
		SelectionSorter selectionSorter = new SelectionSorter(points);
		selectionSorter.setComparator(0);
		selectionSorter.sort();
		boolean sorted = true;

		for (int i = 1; i < selectionSorter.points.length; i++) {
			if(selectionSorter.pointComparator.compare(selectionSorter.points[i-1], selectionSorter.points[i]) > 0) {
			
				sorted = false;
			}
		}
		assertEquals(true, sorted);
	}

	@Test
	public void SelectionSortTestXs() {
		Point.xORy = true;
		SelectionSorter selectionSorter = new SelectionSorter(points);
		selectionSorter.setComparator(0);
		selectionSorter.sort();
		selectionSorter.getPoints(points);

	}

	@Test
	public void MergeSortTestX() {
		Point.xORy = true;
		MergeSorter mergeSorter = new MergeSorter(points);
		mergeSorter.setComparator(0);
		mergeSorter.sort();
		boolean sorted = true;

		for (int i = 1; i < mergeSorter.points.length; i++) {
			if(mergeSorter.pointComparator.compare(mergeSorter.points[i-1], mergeSorter.points[i]) > 0) {
				sorted = false;
			}
		}
		assertEquals(true, sorted);
	}

	@Test
	public void QuickSortTestX() {
		Point.xORy = true;
		QuickSorter quickSorter = new QuickSorter(points);
		quickSorter.setComparator(0);
		quickSorter.sort();
		boolean sorted = true;

		for (int i = 1; i < quickSorter.points.length; i++) {
			if(quickSorter.pointComparator.compare(quickSorter.points[i-1], quickSorter.points[i]) > 0) {
				sorted = false;

			}
		}
		assertEquals(true, sorted);
	}	
	@Test
	public void QuickSortTestY() {
		Point.xORy = false;
		QuickSorter quickSorter = new QuickSorter(points);
		quickSorter.setComparator(1);
		quickSorter.sort();
		boolean sorted = true;

		for (int i = 1; i < quickSorter.points.length; i++) {
			if (quickSorter.pointComparator.compare(quickSorter.points[i-1], quickSorter.points[i])> 0) {
				sorted = false;
			}
		}
		assertEquals(true, sorted);
	}
	@Test
	public void findMedian() {
		Point.xORy = false;
		Point mid = null;
		Point checkMid;
		QuickSorter quickSorter = new QuickSorter(points);
		quickSorter.setComparator(1);
		quickSorter.sort();
		boolean sorted = true;
		for (int i = 1; i < quickSorter.points.length; i++) {
			if (quickSorter.pointComparator.compare(quickSorter.points[i-1], quickSorter.points[i])> 0) {
				sorted = false;
			}
			
		}
			if(sorted == true) {
				 mid = quickSorter.getMedian();
				
			}
			
			checkMid = quickSorter.points[points.length / 2];
			assertEquals(checkMid, mid);
			
		
	}



}

