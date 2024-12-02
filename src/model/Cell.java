package model;

import javafx.scene.paint.Color;

/* 
 * 
 * @author Anthony Mozloom
 * 
 * Cell super class that represents the base for each state
 * 
 * 
 * */
 

public abstract class Cell {
	
	protected boolean canChangeStates;
	
	public abstract boolean canChangeStates();
	public abstract int getTimer();
	public abstract void updateState(int[] neighbors, int timer, double probability);
	
}