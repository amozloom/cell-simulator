package model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


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
	
	
	public static final int EMPTY_CELL = 1;
	public static final int LIVE_TREE_CELL = 2;
	public static final int BURNING_TREE_CELL = 3;
	public static final int BURNT_TREE_CELL = 4;
	
	private double forestDensity;
    private int initialBurningTrees;
    private int burnTime;
    private double spreadProbability;
    private LiveTreeCell liveTreeCell;
    private HashMap<String,BurningTreeCell> burnMap = new HashMap<String,BurningTreeCell>();
	
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
        cells = new int[numRows][numCols];
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
                        cells[i][j] = LIVE_TREE_CELL;
                    } else {
                        cells[i][j] = EMPTY_CELL;
                    }
            	}
            }
    	}
    }
	
	// Updates one step of the grid
	@Override
    public void updateGrid() {
		ArrayList<String> newBurns = new ArrayList<String>();
        int[][] newCells = new int[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int currentCell = cells[i][j];
                newCells[i][j] = currentCell;
                if (i != 0 && j != 0 && i != numRows - 1 && j != numCols - 1) {
                    updateLiveToBurn(currentCell, newCells, i, j, newBurns);
                    if (!newBurns.contains(i+","+j)) {
                    	updateBurnToBurntDown(newCells, i, j);
                    }
                    newBurns.remove(i+","+j);
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
    		if (cells[row][col]==LIVE_TREE_CELL) {
    			cells[row][col] = BURNING_TREE_CELL;
    			burnMap.put(row+","+col, new BurningTreeCell(burnTime));
    			burningTrees--;
    		}
    		row = random.nextInt(numRows);
            col = random.nextInt(numCols);
    	}
    }
    
    // Updates Live trees to burn if they catch fire
    private void updateLiveToBurn(int cell, int[][] newCells, int i, int j, ArrayList<String> newBurns) {
    	if (cell==LIVE_TREE_CELL) {
    		int[] neighbors = getNeighbors(i, j);
    		liveTreeCell = new LiveTreeCell();
            liveTreeCell.updateState(neighbors, burnTime, spreadProbability);
            if (liveTreeCell.getTimer() == burnTime) {
            	newCells[i][j] = BURNING_TREE_CELL;
            	newBurns.add(i+","+j);
            	burnMap.put(i+","+j, new BurningTreeCell(burnTime));
            }
    	}
    }
    
    // Updates burning trees to burn down if their burn timer expires
    private void updateBurnToBurntDown(int[][] newCells, int i, int j) {
    	BurningTreeCell current = burnMap.get(i+","+j);
    	if (current != null) {
    		int[] neighbors = getNeighbors(i, j);
    		current.updateState(neighbors, i, i);
    		burnMap.put(i+","+j, current);
    		if (current.getTimer() == 0) {
	    		newCells[i][j] = BURNT_TREE_CELL;
	    		burnMap.remove(i+","+j);
    		}
    	}
    }
}