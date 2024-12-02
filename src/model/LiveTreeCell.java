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


public class LiveTreeCell extends ChangingCell {

	public LiveTreeCell() {
		super();
		timer = 0;
	}

	// Initialize burnTimeCounter if tree catches on fire 
	@Override
	public void updateState(int[] neighbors, int burnTime, double spreadProbability) {
		for (int neighbor : neighbors) {
			if (neighbor == FireWorldGrid.BURNING_TREE_CELL && Math.random() < spreadProbability) {
				timer = burnTime;
			}
		}
	}
	
	

}