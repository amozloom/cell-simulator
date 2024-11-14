package model;

/* 
 * 
 * @author Anthony Mozloom
 * 
 * This class represents the Live Tree cell
 * 
 * These cells are not on fire but have a 
 * chance to catch on fire
 * 
 * */


public class LiveTreeCell extends Cell {
	
	public LiveTreeCell() {
		super();
		canCatchFire = true;
		name = "Tree  ";
	}
	
	@Override
	public void updateState(Cell[] neighbors, int burnTime, double spreadProbability) {
		
		for (Cell neighbor : neighbors) {
			if (neighbor instanceof BurningTreeCell && Math.random() < spreadProbability) {
				burnTimeCounter = burnTime;
			}
		}
	}
	
	// This cell cannot currently spread fire
	@Override
	public boolean canSpreadFire() {
		return false;
	}
}