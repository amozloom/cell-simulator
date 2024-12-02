package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

//Quincy Oldland

public class InitializeGridStates {
	//Scanner to allow for user inputs
	private Scanner scanner;
	//Cell state variables 
	private int burnTime;
	private double spreadProbability;
	private double forestDensity;
	private int numberOfBurningTrees;
	
	private int numRows;
	private int numCols;
	
	
	public InitializeGridStates() {
		this.burnTime = 1;
		this.spreadProbability = 0.4;
		this.forestDensity = 1;
		this.numberOfBurningTrees = 1;
		this.numRows = 8;
		this.numCols = 3;
	}
	
	
	
	//need to make it so user cannot enter a larger number of burning trees 
	 public void getUserInputForCellState() {
	        scanner = new Scanner(System.in);
	        System.out.println("Hello, welcome to our wildfire simulator\n"
	                + "Please start by entering the variable values for your\n"
	                + "desired simulation");
	        
	        try {
	        //Get height
	        System.out.println("Grid Rows:");
	        this.numRows = scanner.nextInt();
	        
	        //Get width
	        System.out.println("Grid Columns:");
	        this.numCols = scanner.nextInt();
	        
	        
	        // Get burn time
	        System.out.println("Burn Time:");
	        this.burnTime = scanner.nextInt();

	        // Get spread probability
	        System.out.println("Spread Probability:");
	        this.spreadProbability = scanner.nextDouble();

	        // Get forest density
	        System.out.println("Forest Density:");
	        this.forestDensity = scanner.nextDouble();

	        // Get number of burning trees
	        System.out.println("Number of Burning Trees:");
	        this.numberOfBurningTrees = scanner.nextInt();
	        
	        }catch(InputMismatchException e){
	        	System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next();
	        }
	        

	        System.out.println("Test values - Burn Time: " + this.burnTime + ", Spread Probability: " + this.spreadProbability
	                + ", Forest Density: " + this.forestDensity + ", Number of Burning Trees: " + this.numberOfBurningTrees);

	        scanner.close();
	    }

	    
	    
	    
	    public int getBurnTime() {
	        return burnTime;
	    }

	    public double getSpreadProbability() {
	        return spreadProbability;
	    }

	    public double getForestDensity() {
	        return forestDensity;
	    }

	    public int getNumberOfBurningTrees() {
	        return numberOfBurningTrees;
	    }
	    
	    public int getNumberOfColumns() {
	    	return numCols;
	    }
	    
	    public int getNumberOfRows() {
	    	return numRows;
	    }
}
