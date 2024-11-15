package model;

import javafx.scene.paint.Color;

/* 
 * 
 * @author Anthony Mozloom
 * 
 * This class represents the Burnt-Down cell
 * 
 * These cells do not change
 * 
 * */


public class BurntDownCell extends EdgeCell {
	
	private Color color = Color.YELLOW;
	
	public BurntDownCell() {
		super();
		name = "Burnt ";
	}
	
	@Override
	public Color getCellColor() {
		return color;
	}
	
	
}