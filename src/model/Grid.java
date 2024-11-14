package model;

import java.util.Random;


/* 
 * 
 * @author Anthony Mozloom
 * 
 * This class represents the 
 * 
 * 
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

    public Grid(int numRows, int numCols, double forestDensity, int initialBurningTrees) {
        this.numRows = numRows+EDGE_ROWS;
        this.numCols = numCols+EDGE_COLS;
        this.forestDensity = forestDensity;
        this.initialBurningTrees = initialBurningTrees;
        cells = new Cell[numRows+EDGE_ROWS][numCols+EDGE_COLS];
        //initializeGrid();
    }

    private void initializeGrid() {
        cells = new Cell[numRows+EDGE_ROWS][numCols+EDGE_COLS];
        Random random = new Random();

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (i == 0 || j == 0 || i == numRows - 1 || j == numCols - 1) {
                    cells[i][j] = new EdgeCell();
                } else {
                    if (random.nextDouble() < forestDensity) {
                        cells[i][j] = new LiveTreeCell();
                    } else {
                        cells[i][j] = new EmptyCell();
                    }
                }
            }
        }

        for (int n = 0; n < initialBurningTrees; n++) {
            int row, col;
            do {
                row = 1 + random.nextInt(numRows - 2);
                col = 1 + random.nextInt(numCols - 2);
            } while (!(cells[row][col] instanceof LiveTreeCell));
            cells[row][col] = new BurningTreeCell(1);
        }
    }
    
    public void addEdgeCells() {
    	
    	for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (i == 0 || j == 0 || i == numRows - 1 || j == numCols - 1) {
                    cells[i][j] = new EdgeCell();
                }
            }
    	}
    }
    
    public void populateActiveGrid(Random random) {
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
    
    

    public void updateGrid(int burnTime, double spreadProbability) {
        Cell[][] newCells = new Cell[numRows][numCols];

        for (int i = 1; i < numRows - 1; i++) {
            for (int j = 1; j < numCols - 1; j++) {
                Cell currentCell = cells[i][j];
                Cell[] neighbors = getNeighbors(i, j);
                
                currentCell.updateState(neighbors, burnTime, spreadProbability);

                if (currentCell instanceof LiveTreeCell && currentCell.getBurnTime() > 0) {
                    newCells[i][j] = new BurningTreeCell(burnTime);
                } else if (currentCell instanceof BurningTreeCell && currentCell.getBurnTime() <= 0) {
                    newCells[i][j] = new BurntDownCell();
                } else {
                    newCells[i][j] = currentCell;
                }
            }
        }

        cells = newCells;
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
}
