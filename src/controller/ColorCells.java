package controller;

import java.util.HashMap;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Grid;

/* 
 * 
 * @author Quincy Oldland
 * 
 * super class for coloring the different grid cells
 * 
 * */

public abstract class ColorCells {

	//values that will be shared across all of the grids
	protected static final int EDGE_CELL = 0;
	protected final Color CELL_OUTLINE = Color.BLACK;
	//map to store the integer value of the cell and the color
	protected HashMap<Integer, Color> cellColors;
	private final int CELL_SIZE = 40;
	private Group root;
	
	public ColorCells() {
		cellColors = createCellColorMap();
	}
    
    public Group createRootForDisplay(int width, int height, int numCols, int numRows) {
        root = new Group();
        return root;
    }
    
    
    //refreshes the display on each call of step coloring the cells
    public void updateDisplay(Grid grid) {
        root.getChildren().clear(); // clear existing cells to refresh the display

        for (int row = 0; row < grid.getNumRows(); row++) {
            for (int col = 0; col < grid.getNumCols(); col++) {
                int cellNumber = grid.getCell(row, col);
                Rectangle cell = colorOneCell(cellNumber, row, col);
                root.getChildren().add(cell);
            }
        }
    }
    
    //colors a singular cell with its desired colr based on its number 
    public Rectangle colorOneCell(int colorNum, int row, int col ) {
    	Rectangle rect = new Rectangle(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    	rect.setFill(cellColors.get(colorNum));
    	rect.setStroke(CELL_OUTLINE);       
    	rect.setStrokeWidth(0.5);   
    	return rect;

    }
	
    //this map will store the integer value of the cell and its color
    public abstract HashMap<Integer, Color> createCellColorMap();
}
