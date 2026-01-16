package edu.iastate.cs228.hw2;

import java.io.File;


/**
 * 
 * @author Joshua Seeley


 *
 */

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * This class sorts all the points in an array of 2D points to determine a
 * reference point whose x and y coordinates are respectively the medians of the
 * x and y coordinates of the original points.
 * 
 * It records the employed sorting algorithm as well as the sorting time for
 * comparison.
 *
 */
public class PointScanner {
	private Point[] points;

	private Point medianCoordinatePoint; // point whose x and y coordinates are respectively the medians of
											// the x coordinates and y coordinates of those points in the array
											// points[].
	private Algorithm sortingAlgorithm;

	private File file; // stores file

	protected long scanTime; // execution time in nanoseconds.

	/**
	 * This constructor accepts an array of points and one of the four sorting
	 * algorithms as input. Copy the points into the array points[].
	 * 
	 * @param pts input array of points
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException {
		try {
			if (pts == null || pts.length == 0) {
				throw new IllegalArgumentException();

			}
			points = new Point[pts.length]; // starts empty points array

			sortingAlgorithm = algo;

			for (int i = 0; i < points.length; i++) {
				points[i] = pts[i];
			}

		} catch (IllegalArgumentException e) {
			System.out.println("Input for points was either null or the length was 0");
		}
	}

	/**
	 * This constructor reads points from a file.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException if the input file contains an odd number of
	 *                                integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException {
		// TODO
		sortingAlgorithm = algo;
		int c = 0;
		int i = 0;
		
	ArrayList<Point> p = new ArrayList<>();
		try {
			
			file = new File(inputFileName);
			Scanner scnr = new Scanner(file);
			
			while (scnr.hasNextInt()) {
				int x = scnr.nextInt();
				int y = scnr.nextInt();
				
				Point addP = new Point(x, y);  //creates new points and adds them to a list
				p.add(addP);
				
			}
			points = new Point[p.size()];
			for(i = 0; i < p.size(); i++) {
				points[i] = p.get(i);   //copies points from list to points array
			}
			scnr.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Invalid file Name");
		}

		catch (InputMismatchException e) {
			System.out.println("Your file contains an odd amount of Integers");
		}
	}

	/**
	 * Carry out two rounds of sorting using the algorithm designated by
	 * sortingAlgorithm as follows:
	 * 
	 * a) Sort points[] by the x-coordinate to get the median x-coordinate. b) Sort
	 * points[] again by the y-coordinate to get the median y-coordinate. c)
	 * Construct medianCoordinatePoint using the obtained median x- and
	 * y-coordinates.
	 * 
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter,
	 * InsertionSorter, MergeSorter, or QuickSorter to carry out sorting.
	 * 
	 * @param algo
	 * @return
	 */
	public void scan() {
		// TODO
		AbstractSorter aSorter = null;

		if (this.sortingAlgorithm == Algorithm.InsertionSort) {
			aSorter = new InsertionSorter(points);
		} else if (this.sortingAlgorithm == Algorithm.SelectionSort) {
			aSorter = new SelectionSorter(points);
		} else if (this.sortingAlgorithm == Algorithm.MergeSort) {
			aSorter = new MergeSorter(points);
		} else if (this.sortingAlgorithm == Algorithm.QuickSort) {
			aSorter = new QuickSorter(points);
		}

		aSorter.setComparator(0);
		long start1 = System.nanoTime();
		aSorter.sort();
		long end1 = System.nanoTime() - start1;

		int x = aSorter.getMedian().getX();

		aSorter.setComparator(1);
		long start2 = System.nanoTime();
		aSorter.sort();
		long end2 = System.nanoTime() - start2;
		int y = aSorter.getMedian().getY();

		scanTime = end1 + end2;

		medianCoordinatePoint = new Point(x, y);

	}

	/**
	 * Outputs performance statistics in the format:
	 * 
	 * <sorting algorithm> <size> <time>
	 * 
	 * For instance,
	 * 
	 * selection sort 1000 9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description.
	 */
	public String stats() {
		String s = new String("");
		if (this.sortingAlgorithm == Algorithm.InsertionSort) {
			s = ("InsertionSort" + "   " + points.length + "  " + scanTime);
		} else if (this.sortingAlgorithm == Algorithm.SelectionSort) {
			s = ("SelectionSort" + "   " + points.length + "  " + scanTime);
		} else if (this.sortingAlgorithm == Algorithm.MergeSort) {
			s = ("MergeSort" + "       " + points.length + "  " + scanTime);
		} else if (this.sortingAlgorithm == Algorithm.QuickSort) {
			s = ("QuickSort" + "       " + points.length + "  " + scanTime);
		}

		return s;

	}

	/**
	 * Write MCP after a call to scan(), in the format "MCP: (x, y)" The x and y
	 * coordinates of the point are displayed on the same line with exactly one
	 * blank space in between.
	 */
	@Override
	public String toString() {

		String MCP = new String("");
		MCP = "MCP: " + medianCoordinatePoint.toString();

		return MCP;
	}

	/**
	 * 
	 * This method, called after scanning, writes point data into a file by
	 * outputFileName. The format of data in the file is the same as printed out
	 * from toString(). The file can help you verify the full correctness of a
	 * sorting result and debug the underlying algorithm.
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException {
		
		try {
			FileWriter f = new FileWriter(file);
			if(file != null && file.canWrite()) {
			f.write(toString());
			}
			else {
				f.close();
				return;
			}
			f.close();
		}

		catch (FileNotFoundException e) {
			System.out.println("The file was not found");
		} catch (Exception e) {
			System.out.println("File was invalid");
		}

	}

}
