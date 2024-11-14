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
	protected boolean canCatchFire;
	
	
	public Cell() {
		this.burnTimeCounter = 0;
	}
	
	public int getBurnTime() {
		return burnTimeCounter;
	}
	
	
	public abstract void updateState(Cell[] neighbors, int burnTime, double spreadProbability);
	public abstract boolean canSpreadFire();
}