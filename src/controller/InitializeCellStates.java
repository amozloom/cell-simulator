package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

//Quincy Oldland

public class InitializeCellStates {
	//Scanner to allow for user inputs
	private Scanner scanner;
	//Cell state variables 
	private double burnTime;
	private double spreadProbability;
	private double forestDensity;
	private double numberOfBurningTrees;
	
	
	public InitializeCellStates() {
		this.burnTime = 1;
		this.spreadProbability = 0.4;
		this.forestDensity = 1;
		this.numberOfBurningTrees = 1;
	}
	
	
	
	//need to make it so user cannot enter a larger number of burning trees 
	 public void getUserInputForCellState() {
	        scanner = new Scanner(System.in);
	        System.out.println("Hello, welcome to our wildfire simulator\n"
	                + "Please start by entering the variable values for your\n"
	                + "desired simulation");

	        // Get burn time
	        this.burnTime = getValidatedDoubleInput("Burn Time:");

	        // Get spread probability
	        this.spreadProbability = getValidatedDoubleInput("Spread Probability:");

	        // Get forest density
	        this.forestDensity = getValidatedDoubleInput("Forest Density:");

	        // Get number of burning trees
	        this.numberOfBurningTrees = getValidatedDoubleInput("Number of Burning Trees:");

	        System.out.println("Test values - Burn Time: " + this.burnTime + ", Spread Probability: " + this.spreadProbability
	                + ", Forest Density: " + this.forestDensity + ", Number of Burning Trees: " + this.numberOfBurningTrees);

	        scanner.close();
	    }

	    private double getValidatedDoubleInput(String prompt) {
	        double input = 0;
	        boolean valid = false;
	        while (!valid) {
	            System.out.println(prompt);
	            try {
	                input = scanner.nextDouble();
	                valid = true;
	            } catch (InputMismatchException e) {
	                System.out.println("Invalid input. Please enter a numeric value.");
	                scanner.next(); // Clear the invalid input
	            }
	        }
	        return input;
	    }
	    
	    
	    
	    public double getBurnTime() {
	        return burnTime;
	    }

	    public double getSpreadProbability() {
	        return spreadProbability;
	    }

	    public double getForestDensity() {
	        return forestDensity;
	    }

	    public double getNumberOfBurningTrees() {
	        return numberOfBurningTrees;
	    }
}
