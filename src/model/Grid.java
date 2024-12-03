package model;

import java.util.Random;

import controller.InitializeGridStatesWildFire;


/* 
 * 
 * @author Anthony Mozloom
 * 
 * This class represents the Grid
 * 
 * In this class the base layout of a Grid is initialized 
 * Edge cells are created/added
 * Getters and helper methods are defined/created 
 * 
 * */

public abstract class Grid {
	
	public static final int EDGE_CELL = 0;
	private final int EDGE_ROWS = 2;
	private final int EDGE_COLS = 2;
	
    protected int[][] cells;
    protected int numRows;
    protected int numCols;

    // Starter Grid User sees before changing any parameters 
    public Grid(int numRows, int numCols) {
    	this.numRows = numRows+EDGE_ROWS;
        this.numCols = numCols+EDGE_COLS;
    }

    // Initializes Grid calling helper methods
    public abstract void initializeGrid();
    // Populates inside of the grid with Live Trees and Empty cells
    protected abstract void populateActiveGrid(Random random);
    // Updates one step of the grid
    public abstract void updateGrid();
    
    // Adds edge cells to the outside of the grid
    protected void addEdgeCells() {
    	for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (i == 0 || j == 0 || i == numRows - 1 || j == numCols - 1) {
                    cells[i][j] = EDGE_CELL;
                }
            }
    	}
    }

    // Gets neighboring cells to update the grid each state
    protected int[] getNeighbors(int row, int col) {
        return new int[]{
            cells[row - 1][col],
            cells[row + 1][col],
            cells[row][col - 1],
            cells[row][col + 1]
        };
    }

    // Returns grid 
    public int[][] getCells() {
        return cells;
    }
    
    // Returns a cell from the grid
    public int getCell(int row, int col) {
    	return cells[row][col];
    }
    
    // Resets grid with current parameters
    public void resetGrid() {
    	initializeGrid();
    }
    
    // Methods to get size of grid 
    public int getNumRows() {
    	return this.numRows;
    }
    public int getNumCols() {
    	return this.numCols;
    }
    
}
