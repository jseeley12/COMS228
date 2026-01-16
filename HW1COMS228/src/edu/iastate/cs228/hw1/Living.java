package edu.iastate.cs228.hw1;

/**
 *  
 * @author Joshua Seeley
 */

/**
 * 
 * Living refers to the life form occupying a square in a plain grid. It is a 
 * superclass of Empty, Grass, and Animal, the latter of which is in turn a superclass
 * of Badger, Fox, and Rabbit. Living has two abstract methods awaiting implementation. 
 *
 */
public abstract class Living 
{
	
	protected Plain plain; // the plain in which the life form resides
	protected int row;     // location of the square on which 
	protected int column;  // the life form resides
	protected int age;
	// constants to be used as indices. 
	protected static final int BADGER = 0; 
	protected static final int EMPTY = 1; 
	protected static final int FOX = 2; 
	protected static final int GRASS = 3; 
	protected static final int RABBIT = 4; 
	
	public static final int NUM_LIFE_FORMS = 5; 
	
	/**
	 * Abstract constructor for Grass and Empty the non-living things
	 * Contains no parameters for age
	 * @param p plain
	 * @param r row
	 * @param c column
	 */
	public Living(Plain p, int r, int c) {
		plain = p;
		row = r;
		column = c;
	}
	/**
	 * Abstract constructor for the living things including,
	 * Badgers, Foxes, and Rabbits
	 * @param p plain
	 * @param r row
	 * @param c column
	 * @param a age
	 */
	public Living (Plain p, int r, int c, int a) {
		plain = p;
		row = r;
		column = c;
		age = a;
	}
	
	// life expectancies 
	public static final int BADGER_MAX_AGE = 4; 
	public static final int FOX_MAX_AGE = 6; 
	public static final int RABBIT_MAX_AGE = 3; 
	public static final int[] population = new int[NUM_LIFE_FORMS];
	/**
	 * Censuses all life forms in the 3 X 3 neighborhood in a plain. 
	 * @param population  counts of all life forms
	 */
	protected void census(int population[ ])
	{		
	
		population[BADGER] = 0; 
		population[EMPTY] = 0; 
		population[FOX] = 0; 
		population[GRASS] = 0; 
		population[RABBIT] = 0; 
		
		int r2 = Math.min(row+1, plain.grid.length-1);
		int c2 = Math.min(column+1, plain.grid.length-1);
		
		for(int i = Math.max(row-1, 0); i <= r2; ++i) {
			for(int j = Math.max(column-1, 0); j <= c2; ++j) {
				if(plain.grid[i][j].who() == State.BADGER) {
				population[BADGER] += 1;
				}
				if(plain.grid[i][j].who() == State.EMPTY) {
				population[EMPTY] += 1;
				}
				if(plain.grid[i][j].who() == State.FOX) {
				population[FOX] += 1;
				}
				if(plain.grid[i][j].who() == State.GRASS) {
				population[GRASS] += 1;
				}
				if(plain.grid[i][j].who() == State.RABBIT) {
				population[RABBIT] += 1;
				}
		}
	}
	}

	/**
	 * Gets the identity of the life form on the square.
	 * @return State
	 */
	public abstract State who();
	 
	
	/**
	 * Determines the life form on the square in the next cycle.
	 * @param  pNew  plain of the next cycle
	 * @return Living 
	 */
	public abstract Living next(Plain pNew); 
	    

}
