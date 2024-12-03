package controller;

import java.util.HashMap;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Cell;
import model.Grid;

/* 
 * 
 * @author  Quincy Oldland
 * 
 * This class is used to create the cells and their coloring for wildfire
 * 
 * */


public class ColorCellsForWildfire extends ColorCells {
   
	
	public static final int EMPTY_CELL = 1;
	public static final int LIVE_TREE_CELL = 2;
	public static final int BURNING_TREE_CELL = 3;
	public static final int BURNT_TREE_CELL = 4;
	
	
	
	public ColorCellsForWildfire() {
		super();
	}
    
   
    @Override
    public HashMap<Integer, Color> createCellColorMap() {
    	cellColors = new HashMap<>();
    	cellColors.put(EDGE_CELL,Color.BLACK);
    	cellColors.put(EMPTY_CELL,Color.BROWN);
    	cellColors.put(LIVE_TREE_CELL, Color.GREEN);
    	cellColors.put(BURNING_TREE_CELL, Color.RED);
    	cellColors.put(BURNT_TREE_CELL,Color.YELLOW);
    	return cellColors;
    	
    }
    	
     
       
 }
    
    


