package model;

import javafx.scene.paint.Color;

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


public class BurningTreeCell extends LiveTreeCell{

	public BurningTreeCell(int burnTime) {
		timer = burnTime;
		canChangeStates = false;
	}

	// Deincrement burnTimeCounter by one each step 
	@Override
	public void updateState(int[] neighbors, int burnTime, double spreadProbability) {
		timer--;
	}


}