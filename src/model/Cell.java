package model;

import javafx.scene.paint.Color;

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
	protected String name; // Testing purpose only
	
	protected Color color;
	
	
	public Cell() {
		this.burnTimeCounter = 0;
	}
	
	// Gets burn time of a cell 
	public int getBurnTime() {
		return burnTimeCounter;
	}
	
	// Determines if a cell can catch fire or not
	public boolean canCatchFire() {
		return canCatchFire;
	}
	
	// Testing method 
	public String getName() {
		return name;
	}
	
	
	public abstract void updateState(Cell[] neighbors, int burnTime, double spreadProbability);
	public abstract boolean canSpreadFire();
	public abstract Color getCellColor();
}