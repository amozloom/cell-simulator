package controller;

import java.util.HashMap;

import javafx.scene.paint.Color;
/* 
 * 
 * @author  Quincy Oldland
 * 
 * This class is used to create the cells and their coloring for water world
 * 
 * */

public class ColorCellsForWaterWorld extends ColorCells{
	
		//Each cell cna be empty water (Blue), have a Fish (Green), or have a Shark (Yellow).
		
		public static final int EMPTY_WATER = 1;
		public static final int FISH = 2;
		public static final int SHARK = 3;
		
		public ColorCellsForWaterWorld() {
			super();
		}
	    
	   //populate this map with the water world colors
	    @Override
	    public HashMap<Integer, Color> createCellColorMap() {
	    	cellColors = new HashMap<>();
	    	cellColors.put(EMPTY_WATER,Color.BLUE);
	    	cellColors.put(FISH,Color.GREEN);
	    	cellColors.put(SHARK,Color.YELLOW);
	    	return cellColors;
	    	
	    }
	
}
