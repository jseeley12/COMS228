package edu.iastate.cs228.hw2;




import java.io.File;



/**
 *  
 * @author Joshua Seeley

 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;

import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{	
		int trial = 1;
		Scanner scnr = new Scanner(System.in);
		PointScanner[] scanners = new PointScanner[4];
		System.out.println("Peformances of Four Sorting Algorithms in Point Scanning");
	int n = 0;
	System.out.println("1(Random)   2(File)   3(Exit):");
		while(n != 3) {
			
			System.out.print("Trial " + trial++ + ": ");
			n = scnr.nextInt();
		
		if(n == 1) {
			Random generator = new Random();
			System.out.println("Enter number of random points");
			int numPoints = scnr.nextInt();
			System.out.println();
			System.out.println("Algorithm       Size  Time (ns)");
			System.out.println("-----------------------------------");
			PointScanner Iscan = new PointScanner(generateRandomPoints(numPoints, generator), Algorithm.InsertionSort);
			PointScanner Sscan = new PointScanner(generateRandomPoints(numPoints, generator), Algorithm.SelectionSort);
			PointScanner Mscan = new PointScanner(generateRandomPoints(numPoints, generator), Algorithm.MergeSort);
			PointScanner Qscan = new PointScanner(generateRandomPoints(numPoints, generator), Algorithm.QuickSort);
			
			scanners[0] = Iscan;
			scanners[1] = Sscan; 
			scanners[2] = Mscan;
			scanners[3] = Qscan;
	
		} 
		else if(n==2) {
			System.out.println("Points from a file");
			System.out.print("Enter your filename: ");
			String f = scnr.next();
			System.out.println("Algorithm       Size  Time (ns)");
			System.out.println("-----------------------------------");
			PointScanner Iscan = new PointScanner(f, Algorithm.InsertionSort);
			PointScanner Sscan = new PointScanner(f, Algorithm.SelectionSort);
			PointScanner Mscan = new PointScanner(f, Algorithm.MergeSort);
			PointScanner Qscan = new PointScanner(f, Algorithm.QuickSort);
			
			scanners[0] = Iscan;
			scanners[1] = Sscan; 
			scanners[2] = Mscan;
			scanners[3] = Qscan;
		}
		else if(n == 3){
			scnr.close();
			return;
		} else {
			System.out.println("Enter either 1 for Random Points, 2 for file points, or 3 to exit!");
		}
		for(int i = 0; i < scanners.length; i++ ) {
			scanners[i].scan();
			System.out.println(scanners[i].stats());
		}
		System.out.println("-----------------------------------");
		System.out.println();
		
		}
		scnr.close();
		
		// 
		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round, 
		// set up scanning as follows: 
		// 
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		// 
		//    b) Reassigns to the array scanners[] (declared below) the references to four new 
		//       PointScanner objects, which are created using four different values  
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort. 
		// 
		// 	
	
		
		// For each input of points, do the following. 
		// 
		//     a) Initialize the array scanners[].  
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan() 
		//        method in the PointScanner class.  
		//
		//     c) After all four scans are done for the input, print out the statistics table from
		//		  section 2.
		//
		// A sample scenario is given in Section 2 of the project description. 
		
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] × [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		if(numPts < 1) {
			throw new IllegalArgumentException();
		}
		Point[] rPoints = new Point[numPts];
		try {
			
			int randx = rand.nextInt(101) - 50;
			int randy = rand.nextInt(101) - 50;
			for(int i = 0; i < numPts; i++) {
				Point p = new Point(randx, randy);
				rPoints[i] = p; 
			}
		}
		catch(IllegalArgumentException e ) {
			System.out.println("numPts was less than 1. numPts must be >= 1");
		}
		
		
		return rPoints; 
		 
	}
	
}
