package edu.iastate.cs228.hw4;


/**
 * @author Josh Seeley
 * 
 * This class contains the tree from the file
 */
public class MsgTree {

	public char payload;
	public MsgTree left;
	public MsgTree right;
	
	/**
	 * Can use a static char idx to the tree string for recursive 
	 * solution, but it is not strictly necessary
	*/
	private static int staticCharIdx = 0;
	/**
	 * total bits encoding the characters
	 */
	public static int bits;
	/**
	 * total single characters that are encoded
	 */
	public static int totalChars;
	
	/**
	 * Constructor building the tree from a string
	 * @param encodingString the string inputed to build the tree from given values in this string
	 */
	public MsgTree(String encodingString){
		
		payload = encodingString.charAt(staticCharIdx);
		staticCharIdx +=1;
		left = new MsgTree(encodingString.charAt(staticCharIdx));
		
		if(left.payload == '^') {
			left = new MsgTree(encodingString);
			
		}
		staticCharIdx += 1;
		right = new MsgTree(encodingString.charAt(staticCharIdx));
		
		if(right.payload == '^') {
			right = new MsgTree(encodingString);
		}
		
	}
	
	
	/**
	 * Constructor for a single node with null children
	 * 
	 * @param payloadChar a char from msgTree with no children or "null"
	 */
	public MsgTree(char payloadChar){
		
		this.payload = payloadChar;
	} 
	
	
	/**
	 * method to print characters and their binary codes
	 * @param root the root of the tree
	 * @param code the string of code given from the input file
	 */
	public static void printCodes(MsgTree root, String code){
		
		if(root == null) {
			
			return; 
		}
		
		if(root.payload != '^') {
			if(root.payload == '\n') {
				
			System.out.println("   \\n" + "      " + code);
				bits+=code.length();
				totalChars++;
		}
			else {
				System.out.println("   " + root.payload + "       " + code);
				bits+=code.length();
				totalChars++;
			}
		}
		printCodes(root.left, code + "0");
		printCodes(root.right, code + "1");
	}
}
	
	
