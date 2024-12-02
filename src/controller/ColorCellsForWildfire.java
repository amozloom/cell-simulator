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
 * This class is used to create the rectangle box for the cell, and color it accordingly
 * 
 * */


public class ColorCellsForWildfire {
    private final int CELL_SIZE = 40;
    private Group root;
    
    public static final int EDGE_CELL = 0;
	public static final int EMPTY_CELL = 1;
	public static final int LIVE_TREE_CELL = 2;
	public static final int BURNING_TREE_CELL = 3;
	public static final int BURNT_TREE_CELL = 4;
	
	private final Color CELL_OUTLINE = Color.BLACK;
	
	
	private HashMap<Integer, Color> cellColors;
	
	public ColorCellsForWildfire() {
		cellColors = createCellColorMap();
	}
    
    public Group createRootForDisplay(int width, int height, int numCols, int numRows) {
        root = new Group();
        return root;
    }
    
    public HashMap<Integer, Color> createCellColorMap() {
    	cellColors = new HashMap<>();
    	cellColors.put(EDGE_CELL,Color.BLACK);
    	cellColors.put(EMPTY_CELL,Color.BROWN);
    	cellColors.put(LIVE_TREE_CELL, Color.GREEN);
    	cellColors.put(BURNING_TREE_CELL, Color.RED);
    	cellColors.put(BURNT_TREE_CELL,Color.YELLOW);
    	return cellColors;
    	
    }

    public void updateDisplay(Grid grid) {
        root.getChildren().clear(); // Clear existing cells to refresh the display

        for (int row = 0; row < grid.getNumRows(); row++) {
            for (int col = 0; col < grid.getNumCols(); col++) {
                int cellNumber = grid.getCell(row, col);
                Rectangle cell = colorOneCell(cellNumber, row, col);
                root.getChildren().add(cell);
            }
        }
    }
    
    public Rectangle colorOneCell(int colorNum, int row, int col ) {
    	Rectangle rect = new Rectangle(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    	rect.setFill(cellColors.get(colorNum));
    	rect.setStroke(CELL_OUTLINE);       
    	rect.setStrokeWidth(0.5);   
    	return rect;

    }
    	
     
       
 }
    
    


