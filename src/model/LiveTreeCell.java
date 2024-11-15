package model;

import javafx.scene.paint.Color;

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
	
	private Color color = Color.GREEN;
	
	public LiveTreeCell() {
		super();
		canCatchFire = true;
		name = "Tree  ";
	}
	
	// Initialize burnTimeCounter if tree catches on fire 
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
	
	@Override
	public Color getCellColor() {
		return color;
	}
}