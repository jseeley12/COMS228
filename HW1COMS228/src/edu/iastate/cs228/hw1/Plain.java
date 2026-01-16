package edu.iastate.cs228.hw1;

/**
 *  
 * @author Joshua Seeley

 *
 */

import java.io.File; 

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner; 
import java.util.Random; 

/**
 * 
 * The plain is represented as a square grid of size width x width. 
 *
 */
public class Plain 
{
	private int width; // grid size: width X width 
	
	public Living[][] grid; 
	
	/**
	 *  Default constructor reads from a file 
	 */
	public Plain(String inputFileName) throws FileNotFoundException
	{		
  
		File inputFile = new File(inputFileName);
		Scanner scnr = new Scanner(inputFile);
		String type = "";
		
		File inputFileFindWidth = new File(inputFileName);
		Scanner scnrFindWidth = new Scanner(inputFile);
		String findWidth = scnrFindWidth.nextLine();
		Scanner scnr2 = new Scanner(findWidth);
		while(scnr2.hasNext()) {
					width++;
					scnr2.next();
		}
		scnr2.close();
		scnrFindWidth.close();
		
		grid = new Living[width][width];
			
			for(int i = 0; i < width; i++) {
				
				for(int j = 0; j < width; j++) {	
					
					type = scnr.next();
					int age = 0; 
					
					
					if(type.charAt(0) == 'F') {
						//finds age of each for animals
						 String numAge = Character.toString(type.charAt(1));
						 age = Integer.parseInt(numAge);
					 grid[i][j] = new Fox(this, i, j, age);
					
					}
					else if(type.charAt(0) == 'E') {
						 grid[i][j] = new Empty(this, i, j);
						 
						}
					else if(type.charAt(0) == 'B') {
						 String numAge = Character.toString(type.charAt(1));
						 age = Integer.parseInt(numAge);
						 grid[i][j] = new Badger(this, i, j, age);
						 
						}
					else if(type.charAt(0) == 'G') {
						 grid[i][j] = new Grass(this, i, j);
						 
						}
					else if(type.charAt(0) == 'R') {
						 String numAge = Character.toString(type.charAt(1));
						 age = Integer.parseInt(numAge);
						 grid[i][j] = new Rabbit(this, i, j, age); 
						}
					
				}
			}
			
		scnr.close();
	}
	
	/**
	 * Constructor that builds a w x w grid without initializing it. 
	 * @param width  the grid 
	 */
	public Plain(int w)
	{
		// TODO
		width = w;
	grid = new Living[w][w];
	}
	
	
	public int getWidth()
	{
		// TODO  
		return width;  // to be modified 
	}
	
	/**
	 * Initialize the plain by randomly assigning to every square of the grid  
	 * one of BADGER, FOX, RABBIT, GRASS, or EMPTY.  
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit()
	{
		Random generator = new Random(); 
		 
		// TODO 
		
		for(int i = 0; i < width; i++) {
			
			for(int j = 0; j < width; j++) {
				
				int Random = generator.nextInt(5);
				
				if(Random == 0) {
				 grid[i][j] = new Badger(this, i, j, 0);
				 
				}
				else if(Random == 1) {
					 grid[i][j] = new Empty(this, i, j);
					 
					}
				else if(Random == 2) {
					 grid[i][j] = new Fox(this, i, j, 0);
					 
					}
				else if(Random == 3) {
					 grid[i][j] = new Grass(this, i, j);
					 
					}
				else if(Random == 4) {
					 grid[i][j] = new Rabbit(this, i, j, 0); 
					}
			}
		}
	}
	
	
	/**
	 * Output the plain grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.  
	 */
	public String toString()
	{
		String s = "";
		for(int i = 0; i < width; ++i) {

			for(int j = 0; j < width; j++) {
				int age = grid[i][j].age;
				if(grid[i][j].who() == State.GRASS) {
					s+= "G" + "  "; 
				}
				else if(grid[i][j].who() == State.EMPTY) {
					s+= "E" + "  ";
				}
				else if(grid[i][j].who() == State.FOX) {
						
					s+= "F" + age + " ";
				}
				else if(grid[i][j].who() == State.RABBIT) {
					s+= "R" + age + " ";
				}
				else if(grid[i][j].who() == State.BADGER) {
					s+= "B" + age + " ";
				}
			
				
		}
		s += "\n";
		}
		return s;
		
	}
	

	/**
	 * Write the plain grid to an output file.  Also useful for saving a randomly 
	 * generated plain for debugging purpose. 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException
	{
		// TODO 
		// 
		// 1. Open the file. 
		// 
		// 2. Write to the file. The five life forms are represented by characters 
		//    B, E, F, G, R. Leave one blank space in between. Examples are given in
		//    the project description. 
		// 
		// 3. Close the file. 
		try {
		File given = new File(outputFileName);
		
		FileWriter yes = new FileWriter(given);
		
		
		for(int i = 0; i < width; ++i) {

			for(int j = 0; j < width; j++) {
				int age = grid[i][j].age;
				if(grid[i][j].who() == State.GRASS) {
					yes.write("G" + "  "); 
				}
				else if(grid[i][j].who() == State.EMPTY) {
					yes.write("E" + "  ");
				}
				else if(grid[i][j].who() == State.FOX) {
						
					yes.write("F" + age + " ");
				}
				else if(grid[i][j].who() == State.RABBIT) {
					yes.write("R" + age + " ");
				}
				else if(grid[i][j].who() == State.BADGER) {
					yes.write("B" + age + " ");
				}
			
				
		}
			yes.write("\n");
		
	}			
		yes.close();
		}
		catch(Exception e) {
			System.out.print("File does not work");
		}
			 
}
}
