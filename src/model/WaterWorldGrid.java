package model;
/* 
 * 
 * @author Anthony Mozloom
 * 
 * Words
 * 
 * 
 * */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WaterWorldGrid extends Grid{
	public static final int EDGE_CELL = 0;
	public static final int WATER_CELL = 1;
	public static final int FISH_CELL = 2;
	public static final int SHARK_CELL = 3;
	
	private double fishChance;
	private double sharkChance;
	private int fishBreedTime;
	private int sharkBreedTime;
	private int sharkStarveTime;
	private HashMap<SharkCell, int[]> sharkList = new HashMap<>();
	
	public WaterWorldGrid(int numRows, int numCols) {
		super(numRows, numCols);
		fishChance = 0.4;
		sharkChance = 0.1;
		fishBreedTime = 1;
		sharkBreedTime = 20;
		sharkStarveTime = 5;
		initializeGrid();
		}
	
	// Initializes Grid calling helper methods
	@Override
    public void initializeGrid() {
        cells = new Cell[numRows][numCols];
        Random random = new Random();
        
        addEdgeCells();
        populateActiveGrid(random);
    }
	
	@Override 
	protected void populateActiveGrid(Random random) {
		for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
            	if (i != 0 && j != 0 && i != numRows - 1 && j != numCols - 1) {
            		double rand = random.nextDouble();
                    if (rand < fishChance) {
                        cells[i][j] = new FishCell(fishBreedTime);
//                    } else if (rand < fishChance + sharkChance) {
//                    	SharkCell shark = new SharkCell(sharkBreedTime, sharkStarveTime);
//                        cells[i][j] = shark;
//                        sharkList.put(shark, new int[]{i,j});
                    } else {
                        cells[i][j] = new UnchangingCell();
                    }
            	}
            }
		}
	}
	
	@Override
	public void updateGrid() {
		Cell[][] newCells = new Cell[numRows][numCols];
		//updateSharks(newCells);
		updateStates(newCells);
		cells = newCells;
	}
	
	private void updateStates(Cell[][] newGrid) {
		for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
            	Cell currentCell = cells[i][j];
            	newGrid[i][j] = currentCell;
            	if (i != 0 && j != 0 && i != numRows - 1 && j != numCols - 1) {
            		if (cells[i][j].getTimer() >= 0 && !cells[i][j].canChangeStates()) { //Fish
            			System.out.println("Found fish at " + i + " " + j);
            			cells[i][j].updateState(getNeighbors(i, j), fishBreedTime, 0.0);
            			updateFish(newGrid, cells[i][j], i, j);
            		}
            	}
            }
		}
	}
	
	private void updateSharks(Cell[][] newGrid) {
		HashMap<SharkCell, int[]> newSharkList = new HashMap<>();
		for (Map.Entry<SharkCell, int[]> entry : sharkList.entrySet()) {
	        SharkCell shark = entry.getKey();
	        int[] position = entry.getValue();
	        int row = position[0];
	        int col = position[1];

	        // Update the shark's state
	        shark.updateState(getNeighbors(row, col), sharkBreedTime, 0.0);

	        // If the shark moves, update its position in the map
	        if (shark.getStarveTime() == sharkStarveTime) {
	        	sharkEat(newSharkList, shark, row, col);
	        } else {
		        int[] movePosition = getWaterSpace(cells, row, col);
		        if (movePosition[0] != 0) {
		        	newGrid[movePosition[0]][movePosition[1]] = shark; // Move shark in grid
		        	newGrid[row][col] = new UnchangingCell(); // Clear old position
		            newSharkList.put(shark, movePosition); // Update position in map
		        }
	        }
	        if (shark.getTimer() == sharkBreedTime) {
	        	int[] kidPosition = getWaterSpace(cells, row, col);
	        	if (kidPosition[0] != 0) {
	        		SharkCell kid = new SharkCell(sharkBreedTime, sharkStarveTime);
	        		newGrid[kidPosition[0]][kidPosition[1]] = kid;
	        		newSharkList.put(shark, kidPosition);
	        	}
	        }
	    }
		sharkList = newSharkList;
	}
	
	private void sharkEat(HashMap<SharkCell, int[]> sharkList, SharkCell shark, int row, int col) {
		int[] foodPosition = findFish(row, col);
        if (foodPosition[0] != 0) {
            cells[foodPosition[0]][foodPosition[1]] = shark; // Move shark in grid
            cells[row][col] = new UnchangingCell(); // Clear old position
            sharkList.put(shark, foodPosition);
        }
	}
	
	private int[] findFish(int i, int j) {
		ArrayList<int[]> spaces = new ArrayList<>();
	    Random random = new Random();
	    if (i > 1 && cells[i-1][j].getTimer() >= 0 && !cells[i-1][j].canChangeStates()) { // North
	        spaces.add(new int[]{i-1, j});
	    } if (i < cells.length-2 && cells[i-1][j].getTimer() >= 0 && !cells[i-1][j].canChangeStates()) { // South
	        spaces.add(new int[]{i+1, j});
	    } if (j > 1 && cells[i-1][j].getTimer() >= 0 && !cells[i-1][j].canChangeStates()) { // West
	        spaces.add(new int[]{i, j-1});
	    } if (j < cells[0].length-2 && cells[i-1][j].getTimer() >= 0 && !cells[i-1][j].canChangeStates()) { // East
	        spaces.add(new int[]{i, j+1});
	    }
	    if (!spaces.isEmpty()) {
	        int rand = random.nextInt(spaces.size());
	        return spaces.get(rand);
	    }
	    return new int[]{0};
	}
	
	private int[] getWaterSpace(Cell[][] grid, int i, int j) {
	    ArrayList<int[]> spaces = new ArrayList<>();
	    Random random = new Random();
	    if (i > 1 && grid[i-1][j].getTimer() < 0) { // North
	        spaces.add(new int[]{i-1, j});
	    } if (i < grid.length-2 && grid[i+1][j].getTimer() < 0) { // South
	        spaces.add(new int[]{i+1, j});
	    } if (j > 1 && grid[i][j-1].getTimer() < 0) { // West
	        spaces.add(new int[]{i, j-1});
	    } if (j < grid[0].length-2 && grid[i][j+1].getTimer() < 0) { // East
	        spaces.add(new int[]{i, j+1});
	    }
	    if (!spaces.isEmpty()) {
	        int rand = random.nextInt(spaces.size());
	        return spaces.get(rand);
	    }
	    return new int[]{0};
	}
	private void updateFish(Cell[][] grid, Cell cell, int i, int j) {
		if (cell.getTimer() >= 0 && !cell.canChangeStates()) {
//			if (cell.getTimer() == 0) {
//				breedFish(grid, i, j);
//				System.out.println("Time for a new fish");
//			}
			moveFish(grid, cell, i, j);
		}
	}
	private void moveFish(Cell[][] grid, Cell cell, int i, int j) {
		int[] waterSpaces = getWaterSpace(cells, i, j);
		if (waterSpaces[0] != 0) {
			grid[waterSpaces[0]][waterSpaces[1]] = cell;
			grid[i][j] = new UnchangingCell();
			System.out.println("Fish moved " + waterSpaces[0] + " " +waterSpaces[1]);
			System.out.println("Water placed " + i + " " +j);
		}
	}
	private void breedFish(Cell[][] grid, int i, int j) {
		int[] waterSpaces = getWaterSpace(cells, i, j);
		if (waterSpaces[0] != 0) {
			grid[waterSpaces[0]][waterSpaces[1]] = new FishCell(fishBreedTime);
			System.out.println("Fish added " + waterSpaces[0] + " " + waterSpaces[1]);
		}
		grid[i][j] = new FishCell(fishBreedTime);
	}
	
}