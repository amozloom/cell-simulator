package model;

/* 
 * 
 * @author Anthony Mozloom
 * 
 * Cell super class that represents the base for each state
 * 
 * 
 * */
 

public abstract class Cell {
	
	protected int burnTimeCounter;
	
	
	public Cell() {
		this.burnTimeCounter = 0;
	}
	
	
	public abstract void updateState(Cell[] neighbors, int burnTime, double spreadProbability);
	public abstract boolean canSpreadFire();
}