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
	protected String name;
	
	
	public Cell() {
		this.burnTimeCounter = 0;
	}
	
	public int getBurnTime() {
		return burnTimeCounter;
	}
	
	public boolean canCatchFire() {
		return canCatchFire;
	}
	
	public String getName() {
		return name;
	}
	
	
	public abstract void updateState(Cell[] neighbors, int burnTime, double spreadProbability);
	public abstract boolean canSpreadFire();
}