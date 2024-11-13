package model;

/* 
 * 
 * @author Anthony Mozloom
 * 
 * This class represents the edge cells
 *  These cells do not change
 * 
 * */

public class EdgeCell extends Cell{
	
	public EdgeCell() {
		super();
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
