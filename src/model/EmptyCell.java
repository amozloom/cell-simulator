package model;

import javafx.scene.paint.Color;

/* 
 * 
 * @author Anthony Mozloom
 * 
 * This class represents an empty cell
 * 
 * These cells do not change
 * 
 * */


public class EmptyCell extends EdgeCell {
	
	private Color color = Color.GRAY;
	
	public EmptyCell() {
		super();
		name = "Empty";
	}
	
	@Override
	public Color getCellColor() {
		return color;
	}
	
	
}