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
 * This class implements the version of the quicksort algorithm presented in the
 * lecture.
 *
 */

public class QuickSorter extends AbstractSorter {

	// Other private instance variables if you need ...

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts input array of integers
	 */
	public QuickSorter(Point[] pts) {
		// TODO
		super(pts);
		super.algorithm = ("QuickSort");
	}

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.
	 * 
	 */
	@Override
	public void sort() {
		quickSortRec(0, points.length - 1);
		super.getPoints(points);
		// TODO
	}

	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first starting index of the subarray
	 * @param last  ending index of the subarray
	 */
	private void quickSortRec(int first, int last) {
		// TODO
		if (first >= last) {
			return;
		}

		int firstIndex = partition(first, last);
		quickSortRec(first, firstIndex - 1);
		quickSortRec(firstIndex + 1, last);
	}

	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last) {
		// TODO
		
		Point pivot = points[last];
		int firstNew = first -1;
		
			for (int j = first; j < last; ++j) {
				if (pointComparator.compare(points[j], pivot) == -1 || 
						pointComparator.compare(points[j], pivot) == 0) {

					
						++firstNew;
						Point temp = points[firstNew];
						points[firstNew] = points[j];
						points[j] = temp;

					}
		}
	
		Point temp = points[firstNew+1];
		points[firstNew+1] = points[last];
		points[last] = temp;
		return firstNew + 1;

	}	
}
