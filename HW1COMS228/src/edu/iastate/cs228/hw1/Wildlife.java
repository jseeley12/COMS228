package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;

import java.util.Scanner; 

/**
 *  
 * @author joshua seeley
 */

/**
 * 
 * The Wildlife class performs a simulation of a grid plain with
 * squares inhabited by badgers, foxes, rabbits, grass, or none. 
 *
 */
public class Wildlife 
{
	/**
	 * Update the new plain from the old plain in one cycle. 
	 * @param pOld  old plain
	 * @param pNew  new plain 
	 */
	public static void updatePlain(Plain pOld, Plain pNew)
	{
		// TODO 
		// 
		// For every life form (i.e., a Living object) in the grid pOld, generate  
		// a Living object in the grid pNew at the corresponding location such that 
		// the former life form changes into the latter life form. 
		// 
		// Employ the method next() of the Living class.		
		for(int i = 0; i < pOld.getWidth(); i++) {
			for(int j = 0; j < pOld.getWidth(); j++) {
				
				pNew.grid[i][j] = pOld.grid[i][j].next(pNew);
			}
		}
	}
	
	/**
	 * Repeatedly generates plains either randomly or from reading files. 
	 * Over each plain, carries out an input number of cycles of evolution. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{	
		Plain even;   				 // the plain after an even number of cycles 
		Plain odd;                   // the plain after an odd number of cycles
		Scanner scnr = new Scanner(System.in);
		int tCount = 1;
		int uIn = 0;
		
		while(uIn != 3) {
			int updateCounter = 0;
			int numCycles = 0;
		System.out.println("Simulation of Wildlife of the Plain");
		System.out.println("keys: 1 (random plain)  2 (file input)  3 (exit)");
		
		System.out.print("Trial " + tCount++ + ": ");
		uIn = scnr.nextInt();
		if(uIn == 1) {
			
		System.out.println("Random plain");
		System.out.print("Enter grid width: ");	
			int width = scnr.nextInt();
			even = new Plain(width);
			odd = new Plain(width);
			System.out.println("Enter the number of Cycles: ");
			numCycles = scnr.nextInt();
			System.out.println();
			System.out.println("Initial Plain:");
			System.out.println();
			even.randomInit();
			System.out.println(even.toString());
			
			while(updateCounter < numCycles ) {
				updatePlain(even, odd);
				updateCounter++;
			
			if(numCycles == updateCounter) {
				break;
			}
			updatePlain(odd, even);
			updateCounter++;
		}
			if(numCycles % 2 == 0) {
			System.out.println("Final Plain: ");
			System.out.println();
			
			System.out.println(even.toString());
			
			numCycles = 0;
			}
			else if(numCycles % 2 == 1) {
				System.out.println("Final Plain: ");
				System.out.println();
				
				System.out.println(odd.toString());
				
				numCycles = 0;
				}	
		}
			else if(uIn == 2) {  //File input
				
				System.out.println("Plain input from a file");
				System.out.print("File name: ");
				String file = "";
				file = scnr.next();
					even = new Plain(file);
					odd = new Plain(file);
					System.out.println("Enter the number of Cycles: ");
					numCycles = scnr.nextInt();
					System.out.println();
					System.out.println("Initial Plain:");
					System.out.println();
					System.out.println(even.toString());
					System.out.println();
					
					while(updateCounter < numCycles ) {
						updatePlain(even, odd);
						updateCounter++;
					
					if(numCycles == updateCounter) {
						break;
					}
					updatePlain(odd, even);
					updateCounter++;
				}
					
					if(numCycles % 2 == 0) {
					System.out.println("Final Plain: ");
					System.out.println();
					
					System.out.print(even.toString());
					
					numCycles = 0;
					}
					else if(numCycles % 2 == 1) {
						System.out.println("Final Plain: ");
						System.out.println();
						
						System.out.println(odd.toString());
						
						numCycles = 0;
				
					}
			}
		} if(uIn == 3) {
				return;
			}
		
		
		scnr.close();
		
	}
}
