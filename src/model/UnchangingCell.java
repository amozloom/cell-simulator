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


public class UnchangingCell extends Cell{

	public UnchangingCell() {
		canChangeStates = false;
	}

	@Override
	public int getTimer() {
		return -1;
	}

	@Override
	public boolean canChangeStates() {
		return canChangeStates;
	}

	public void updateState(int[] neighbors, int timer, double probability) {

	}


}