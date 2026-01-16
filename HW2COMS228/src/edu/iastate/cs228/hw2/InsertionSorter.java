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
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
{
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) 
	{
		// TODO 
		super(pts);
		super.algorithm = ("InsertionSort");
	}	

	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 */
	@Override 
	public void sort()
	{
		// TODO 
		
		int j = 0;
		
		for(int i = 1; i < points.length; i++) {
			Point holder = points[i];
			j = i -1;
			while((j > -1) && (pointComparator.compare(points[j], holder)) == 1) {
				swap(j, j+1);
				
			--j;
			}	
			points[j+1] = holder;
		}
			super.getPoints(points);
			
}
	}		
