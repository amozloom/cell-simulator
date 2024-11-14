package model;

import java.util.Random;


/* 
 * 
 * @author Anthony Mozloom
 * 
 * This class represents the Grid
 * 
 * In this class the Grid is initialized with its parameters
 * Cells are updated based on their neighbors and burn time
 * 
 * */

public class Grid {
	
	private final int EDGE_ROWS = 2;
	private final int EDGE_COLS = 2;
	
    private Cell[][] cells;
    private int numRows;
    private int numCols;
    private double forestDensity;
    private int initialBurningTrees;
    private int burnTime;
    private double spreadProbability;

    public Grid(int numRows, int numCols) {
    	this.numRows = numRows+EDGE_ROWS;
        this.numCols = numCols+EDGE_COLS;
        this.forestDensity = 1;
        this.initialBurningTrees = 1;
        this.burnTime = 1;
        this.spreadProbability = 0.4;
        initializeGrid();
    }
    
    public Grid(int numRows, int numCols, int burnTime, double spreadProbability, double forestDensity, int initialBurningTrees) {
        this.numRows = numRows+EDGE_ROWS;
        this.numCols = numCols+EDGE_COLS;
        this.forestDensity = forestDensity;
        this.initialBurningTrees = initialBurningTrees;
        this.burnTime = burnTime;
        this.spreadProbability = spreadProbability;
        initializeGrid();
    }

    private void initializeGrid() {
        cells = new Cell[numRows][numCols];
        Random random = new Random();
        
        addEdgeCells();
        populateActiveGrid(random);
        addBurningTrees(random);
    }
    
    private void addEdgeCells() {
    	for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (i == 0 || j == 0 || i == numRows - 1 || j == numCols - 1) {
                    cells[i][j] = new EdgeCell();
                }
            }
    	}
    }
    
    private void populateActiveGrid(Random random) {
    	for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
            	if (i != 0 && j != 0 && i != numRows - 1 && j != numCols - 1) {
            		if (random.nextDouble() < forestDensity) {
                        cells[i][j] = new LiveTreeCell();
                    } else {
                        cells[i][j] = new EmptyCell();
                    }
            	}
            }
    	}
    }
    
    private void addBurningTrees(Random random) {
    	int row = random.nextInt(numRows);;
    	int col = random.nextInt(numCols);
    	int burningTrees = initialBurningTrees;
    	
    	while (burningTrees > 0) {
    		if (cells[row][col].canCatchFire) {
    			cells[row][col] = new BurningTreeCell(burnTime);
    			burningTrees--;
    		}
    		row = random.nextInt(numRows);
            col = random.nextInt(numCols);
    	}
    }

    public void updateGrid() {
        Cell[][] newCells = new Cell[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                Cell currentCell = cells[i][j];
                newCells[i][j] = currentCell;
                
                if (i != 0 && j != 0 && i != numRows - 1 && j != numCols - 1) {
                	Cell[] neighbors = getNeighbors(i, j);
                    currentCell.updateState(neighbors, burnTime, spreadProbability);
                    updateLiveToBurn(currentCell, newCells, i, j);
                    updateBurnToBurntDown(currentCell, newCells, i, j);
                }
            }
        }
        cells = newCells;
    }
    
    private void updateLiveToBurn(Cell cell, Cell[][] newCells, int i, int j) {
    	if (cell.getBurnTime() > 0 && cell.canCatchFire) {
    		newCells[i][j] = new BurningTreeCell(burnTime);
    	}
    }
    
    private void updateBurnToBurntDown(Cell cell, Cell[][] newCells, int i, int j) {
    	if (cell.getBurnTime() == 0 && cell.canSpreadFire()) {
    		newCells[i][j] = new BurntDownCell();
    	}
    }

    private Cell[] getNeighbors(int row, int col) {
        return new Cell[]{
            cells[row - 1][col],
            cells[row + 1][col],
            cells[row][col - 1],
            cells[row][col + 1]
        };
    }

    public Cell[][] getCells() {
        return cells;
    }
    
    public void resetGrid() {
    	initializeGrid();
    }
}
