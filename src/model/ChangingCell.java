package model;


/* 
 * 
 * @author Anthony Mozloom
 * 
 * Cell super class that represents the base for each state
 * 
 * 
 * */
 

public abstract class ChangingCell extends Cell {
	
	protected boolean canChangeStates;
	protected int timer;
	
	public ChangingCell() {
		canChangeStates = true;
	}
	
	// Gets burn time of a cell 
	@Override
	public int getTimer() {
		return timer;
	}
	
	@Override
	public boolean canChangeStates() {
		return canChangeStates;
	}
	
	public abstract void updateState(int[] neighbors, int timer, double probability);
}