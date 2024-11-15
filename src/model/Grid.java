package model;

import java.util.Random;

import controller.InitializeGridStates;


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

    // Starter Grid User sees before changing any parameters 
    public Grid(int numRows, int numCols) {
    	this.numRows = numRows+EDGE_ROWS;
        this.numCols = numCols+EDGE_COLS;
        this.forestDensity = 1;
        this.initialBurningTrees = 1;
        this.burnTime = 1;
        this.spreadProbability = 0.4;
        initializeGrid();
    }
    
    // Grid that can update each parameter
    public Grid(int numRows, int numCols, int burnTime, double spreadProbability, double forestDensity, int initialBurningTrees) {
        this.numRows = numRows+EDGE_ROWS;
        this.numCols = numCols+EDGE_COLS;
        this.forestDensity = forestDensity;
        this.initialBurningTrees = initialBurningTrees;
        this.burnTime = burnTime;
        this.spreadProbability = spreadProbability;
        initializeGrid();
    }

    // Initializes Grid calling helper methods
    public void initializeGrid() {
        cells = new Cell[numRows][numCols];
        Random random = new Random();
        
        addEdgeCells();
        populateActiveGrid(random);
        addBurningTrees(random);
    }
    
    // Adds edge cells to the outside of the grid
    private void addEdgeCells() {
    	for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (i == 0 || j == 0 || i == numRows - 1 || j == numCols - 1) {
                    cells[i][j] = new EdgeCell();
                }
            }
    	}
    }
    
    // Populates inside of the grid with Live Trees and Empty cells
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
    
    // Sets one of the created trees to start as burning
    private void addBurningTrees(Random random) {
    	int row = random.nextInt(numRows);
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

    // Updates one step of the grid
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
    
    // Updates Live trees to burn if they catch fire
    private void updateLiveToBurn(Cell cell, Cell[][] newCells, int i, int j) {
    	if (cell.getBurnTime() > 0 && cell.canCatchFire) {
    		newCells[i][j] = new BurningTreeCell(burnTime);
    	}
    }
    
    // Updates burning trees to burn down if their burn timer expires
    private void updateBurnToBurntDown(Cell cell, Cell[][] newCells, int i, int j) {
    	if (cell.getBurnTime() == 0 && cell.canSpreadFire()) {
    		newCells[i][j] = new BurntDownCell();
    	}
    }

    // Gets neighboring cells to update the grid each state
    private Cell[] getNeighbors(int row, int col) {
        return new Cell[]{
            cells[row - 1][col],
            cells[row + 1][col],
            cells[row][col - 1],
            cells[row][col + 1]
        };
    }

    // Returns grid 
    public Cell[][] getCells() {
        return cells;
    }
    
    // Returns a cell from the grid
    public Cell getCell(int row, int col) {
    	return cells[row][col];
    }
    
    // Resets grid with current parameters
    public void resetGrid() {
    	initializeGrid();
    }
    
    // Prints grid for testing
    public void printGrid() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print(cells[i][j].getClass().getSimpleName() + " ");
            }
            System.out.println();
        }
        System.out.println(); // Extra line for readability between updates
    }
    
    // Methods to get size of grid 
    public int getNumRows() {
    	return this.numRows;
    }
    public int getNumCols() {
    	return this.numCols;
    }
    
}
