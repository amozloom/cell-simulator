package model;

import javafx.scene.paint.Color;

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
	
	private Color color = Color.BLACK;
	
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
	
	@Override
	public Color getCellColor() {
		return color;
	}
}
