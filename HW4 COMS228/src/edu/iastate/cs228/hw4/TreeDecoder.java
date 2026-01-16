package edu.iastate.cs228.hw4;

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
/**
 * 
 * @author Josh Seeley
 * 
 * This class decodes the tree that was made in the class MsgTree to figure out 
 * what the message the tree is storing from the input file
 *
 */

public class TreeDecoder extends MsgTree{
	
	public TreeDecoder(String encodingString) {
		super(encodingString);
		
	}
	/**
	 * input file
	 */
	private static String fileN;
	/**
	 * static char idx for the recursion solution
	 */
	private static int staticCharIdx;
	/**
	 * total count of characters in output
	 */
	private static int totalCount;
	/**
	 * Format for decimal output for statistics
	 */
	private static final DecimalFormat d = new DecimalFormat("0.00");
	/**
	 * Main method
	 * @param args
	 * @throws FileNotFoundException
	 */
	
	public static void main(String args[]) throws FileNotFoundException {
		
		Scanner scnr = new Scanner(System.in);
		String message = "";
		System.out.println("Please enter filename to Decode: ");
		 fileN = scnr.next();
		
			System.out.print(output(message));
	
			scnr.close();
			System.out.println();
			System.out.println();
			System.out.println("STATISTICS: ");
			stats();
			
	
	}
	
	/**
	 * Copies and reads the file information and adds the information to the arraylist
	 * 
	 * @param fileN input file from main method
	 * @return Arraylist of binary tree code
	 * @throws FileNotFoundException 
	 */
	public static ArrayList<String> read(String fileN) throws FileNotFoundException {
		
		ArrayList<String> o = new ArrayList<String>();
		
		
			File f = new File(fileN);
			Scanner fscan = new Scanner(f);
			
			while(fscan.hasNext()) {
				
				o.add(fscan.nextLine());
			}
			fscan.close();
		
		
		
		return o;
	}
	
	/**
	 * This method calls to print methods to get ready to output to console. 
	 * Calls to printCodes method and decode methods for final output.
	 * 
	 * 
	 * @param message empty string to hold coded message for output
	 * @return string 
	 * @throws FileNotFoundException 
	 */
	
	public static String output(String message) throws FileNotFoundException {
		ArrayList<String> in = read(fileN);
		for(int i = 0; i < in.size(); i++) {
			message += in.get(i) + '\n';
			
			}
				message += in.get(in.size() - 2);
			
				
				MsgTree finished = new MsgTree(message);
			
				System.out.println("character  code");
				System.out.println("---------------------");
				finished.printCodes(finished, "");
				
				int yes = in.get(in.size() - 1).length();
				System.out.println("MESSAGE:");
				while(staticCharIdx < yes) {
					decode(finished, in.get(in.size() - 1));
					totalCount++;
				
			}
				return "";
	
		
		
		
	}
	
	/**
	 * Decodes the tree and prints it to the console
	 * @param codes the message tree codes
	 * @param msg The message decoded from the code
	 */
	public static void decode(MsgTree codes, String msg) {
		
		while(codes.left != null && codes.right != null) {
			if(msg.charAt(staticCharIdx) == '0') {
				
				codes = codes.left;
			}
			if(msg.charAt(staticCharIdx) == '1') {
				codes = codes.right;
			}
			
			staticCharIdx += 1;	
			
		}
		
		if(codes.payload == '\n') {
			System.out.print("\n");
			
		}else {
			System.out.print(codes.payload);
		}
	}
	/**
	 * Calculates statistics and accesor method for printing.
	 * @throws FileNotFoundException
	 */
	public static void stats() throws FileNotFoundException {
		
		int uncompressedBits = totalChars * 16;
		double avgBits = bits/totalChars;
	
		
		double savings = (1 - avgBits / uncompressedBits)*100;
		
		System.out.println("Avg bits/char:   " + avgBits);
		System.out.println("Total characters:   " + totalCount);
		System.out.println("Space Savings:    " + d.format(savings) + "%");
		
	}
}


