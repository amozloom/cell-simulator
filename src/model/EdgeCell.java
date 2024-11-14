package model;

/* 
 * 
 * @author Anthony Mozloom
 * 
 * This class represents an edge cell
 * 
 * These cells do not change
 * 
 * */

public class EdgeCell extends Cell{
	
	public EdgeCell() {
		super();
		canCatchFire = false;
		name ="Edge  ";
	}
	
	// This cell does not change 
	@Override
	public void updateState(Cell[] neighbors, int burnTime, double spreadProbability) {
		
	}
	
	// This cell cannot spread fire
	@Override
	public boolean canSpreadFire() {
		return false;
	}
}
