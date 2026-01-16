package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;

import java.lang.NumberFormatException;
import java.time.chrono.MinguoChronology;
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;




/**
 *  
 * @author Joshua Seeley


 *
 */

/**
 * 
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		super.algorithm = ("SelectionSort");
	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		for(int i = 0; i < points.length-1; i++) {
			Point m = points[i];
			int sIndex = i;
			for(int j = i+1; j < points.length; j++) {
				
				if(pointComparator.compare(points[j], m) == -1) {
				m = points[j]; 	
				sIndex = j;
					
				}
			}
			swap(i, sIndex);
			
		}
		super.getPoints(points);
	
	}	
	
		
}
