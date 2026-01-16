package edu.iastate.cs228.hw1;

/**
 *  
 * @author Joshua Seeley
 */

/*
 * This class is to be extended by the Badger, Fox, and Rabbit classes. 
 */
public abstract class Animal extends Living implements MyAge
{
	protected int age;// age of the animal
	
	public Animal(Plain p, int r, int c, int age) {
		super(p, r, c, age);
		this.age = age;
		// TODO Auto-generated constructor stub
	}

	

	@Override
	/**
	 * 
	 * @return age of the animal 
	 */
	public int myAge()
	{
		// TODO 
		
		return age; 
	}
}
