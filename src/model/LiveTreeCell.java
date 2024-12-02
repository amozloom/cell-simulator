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


public class LiveTreeCell extends ChangingCell {
	
	public LiveTreeCell() {
		super();
		timer = 0;
	}
	
	// Initialize burnTimeCounter if tree catches on fire 
	@Override
	public void updateState(Cell[] neighbors, int burnTime, double spreadProbability) {
		for (Cell neighbor : neighbors) {
			if (neighbor instanceof BurningTreeCell && Math.random() < spreadProbability) {
				timer = burnTime;
			}
		}
	}
	
}