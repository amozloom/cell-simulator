package model;


import java.util.Random;


//import controller.InitializeCellStates;


/* 
 * 
 * @author Anthony Mozloom
 * 
 * This class represents the Grid for the Wild Fire Simulation
 * 
 * In this class the Grid is initialized with its parameters
 * Cells are updated based on their neighbors and burn time
 * 
 * */

public class FireWorldGrid extends Grid {
	
	public static final int EDGE_CELL = 0;
	public static final int EMPTY_CELL = 1;
	public static final int LIVE_TREE_CELL = 2;
	public static final int BURNING_TREE_CELL = 3;
	public static final int BURNT_TREE_CELL = 4;
	
	private double forestDensity;
    private int initialBurningTrees;
    private int burnTime;
    private double spreadProbability;
	
	public FireWorldGrid(int numRows, int numCols) {
		super(numRows, numCols);
		this.forestDensity = 1;
        this.initialBurningTrees = 1;
        this.burnTime = 1;
        this.spreadProbability = 0.4;
        initializeGrid();
	}
	
	public FireWorldGrid(int numRows, int numCols, int burnTime, double spreadProbability, double forestDensity, int initialBurningTrees) {
        super(numRows, numCols);
        this.forestDensity = forestDensity;
        this.initialBurningTrees = initialBurningTrees;
        this.burnTime = burnTime;
        this.spreadProbability = spreadProbability;
        initializeGrid();
    }
	
	// Initializes Grid calling helper methods
	@Override
    public void initializeGrid() {
        cells = new Cell[numRows][numCols];
        Random random = new Random();
        
        addEdgeCells();
        populateActiveGrid(random);
        addBurningTrees(random);
    }
	
	@Override
	 protected void populateActiveGrid(Random random) {
    	for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
            	if (i != 0 && j != 0 && i != numRows - 1 && j != numCols - 1) {
            		if (random.nextDouble() < forestDensity) {
                        cells[i][j] = new LiveTreeCell();
                    } else {
                        cells[i][j] = new UnchangingCell();
                    }
            	}
            }
    	}
    }
	
	// Updates one step of the grid
	@Override
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
	
	// Sets one of the created trees to start as burning
    private void addBurningTrees(Random random) {
    	int row = random.nextInt(numRows);
    	int col = random.nextInt(numCols);
    	int burningTrees = initialBurningTrees;
    	while (burningTrees > 0) {
    		if (cells[row][col].canChangeStates()) {
    			cells[row][col] = new BurningTreeCell(burnTime);
    			burningTrees--;
    		}
    		row = random.nextInt(numRows);
            col = random.nextInt(numCols);
    	}
    }
    
    // Updates Live trees to burn if they catch fire
    private void updateLiveToBurn(Cell cell, Cell[][] newCells, int i, int j) {
    	if (cell.getTimer() == burnTime) {
    		newCells[i][j] = new BurningTreeCell(burnTime);
    	}
    }
    
    // Updates burning trees to burn down if their burn timer expires
    private void updateBurnToBurntDown(Cell cell, Cell[][] newCells, int i, int j) {
    	if (cell.getTimer() == 0 && !cell.canChangeStates()) {
    		newCells[i][j] = new UnchangingCell();
    	}
    }
}