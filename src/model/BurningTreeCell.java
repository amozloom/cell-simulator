package model;

/* 
 * 
 * @author Anthony Mozloom
 * 
 * This class represents the Burning Tree cell
 * 
 * These cells are on fire and have a chance 
 * to catch other Live Tree Cells on fire
 * 
 * */


public class BurningTreeCell extends Cell{
	
	public BurningTreeCell(int burnTime) {
		burnTimeCounter = burnTime;
	}
	
	@Override
	public void updateState(Cell[] neighbors, int burnTime, double spreadProbability) {
		burnTimeCounter--;
	}
	
	// This cell is on fire and can catch other cells on fire
	@Override
	public boolean canSpreadFire() {
		return true;
	}
}