package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;
import java.util.InputMismatchException;

/**
 *  
 * @author Joshua Seeley


 *
 */

/**
 * 
 * This class implements the mergesort algorithm.
 *
 */

public class MergeSorter extends AbstractSorter {

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts input array of integers
	 */
	public MergeSorter(Point[] pts) {
		// TODO
		super(pts);
		super.algorithm = ("MergeSort");
	}

	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter.
	 * 
	 */
	@Override
	public void sort() {
		// TODO
		mergeSortRec(points);
		super.getPoints(points);
	}

	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of
	 * points. One way is to make copies of the two halves of pts[], recursively
	 * call mergeSort on them, and merge the two sorted subarrays into pts[].
	 * 
	 * @param pts point array
	 */
	private void mergeSortRec(Point[] pts) {
		int j = pts.length;

		if (j < 2) {
			return;
		} else {
			int half = j / 2;
			Point[] left = new Point[half];
			Point[] right = new Point[j - half];
			int i = 0;
			for (int y = 0; y < left.length; y++) {
				left[y] = pts[i];
				++i;
			}
			for (int x = 0; x < right.length; x++) {
				right[x] = pts[i];
				++i;
			}
			mergeSortRec(left);
			mergeSortRec(right);
			mergeSort(pts, left, right);
		}
	}

	/**
	 * This method compares the points of the left point and right point with
	 * points.
	 * 
	 * @mergeSortRec calls to this method.
	 * 
	 * @param points
	 * @param left
	 * @param right
	 */
	private void mergeSort(Point[] points, Point[] left, Point[] right) {

		int i = 0;
		int j = 0;
		int x = 0;

		while (i < left.length && j < right.length) {

			if (pointComparator.compare(left[i], right[j]) == -1 || pointComparator.compare(left[i], right[j]) == 0) { 
				// compares left and right
				points[x] = left[i];				
				i++;
				x++;
			} else {
				points[x] = right[j];
				j++;
				x++;
			}
		}
		while (j < right.length) {
			points[x] = right[j];
			x++;
			j++;
		}
		while (i < left.length) {
			points[x] = left[i];
			i++;
			x++;
		}
	}
}
