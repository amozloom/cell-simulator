package controller;

import java.util.InputMismatchException;
import java.util.Scanner;



public class InitializeGridStatesWaterWorld {
	
	private Scanner scanner;
	
	private double chanceOfFish;
	private double chanceOfShark;
	private double chanceOfWater;
	private int sharkBreedTime;
	private int fishBreedTime;
	private int starveTime;
	
	public InitializeGridStatesWaterWorld() {
		this.chanceOfFish = 0.7;
		this.chanceOfShark = 0.1;
		this.chanceOfWater = 0.2;
		this.sharkBreedTime = 20;
		this.fishBreedTime = 1;
		this.starveTime = 5;
	}
	
	 public void getUserInputForCellState() {
	        scanner = new Scanner(System.in);
	        System.out.println("Hello, welcome to our water-world simulator\n"
	                + "Please start by entering the variable values for your\n"
	                + "desired simulation");
	        
	        try {
	        	 System.out.println("Enter the chance of fish:");
	        	 this.chanceOfFish = scanner.nextDouble();
	        	
	        	 System.out.println("Enter the chance of sharks:");
	        	 this.chanceOfShark = scanner.nextDouble();
	        	 
	        	 System.out.println("Enter the chance of water:");
	        	 this.chanceOfWater = scanner.nextDouble();
	        	 
	        	 System.out.println("Enter the shark breed time (in simulation steps):");
	             this.sharkBreedTime = scanner.nextInt();

	             System.out.println("Enter the fish breed time (in simulation steps):");
	             this.fishBreedTime = scanner.nextInt();

	             System.out.println("Enter the starve time for sharks (in simulation steps):");
	             this.starveTime = scanner.nextInt();
	        
	 		}catch(InputMismatchException e){
		     	System.out.println("Invalid input. Please enter a numeric value.");
		         scanner.next();
		     }
	 }
	 
	 public double getChanceOfFish() {
		    return chanceOfFish;
		}

		public double getChanceOfShark() {
		    return chanceOfShark;
		}

		public double getChanceOfWater() {
		    return chanceOfWater;
		}

		public int getSharkBreedTime() {
		    return sharkBreedTime;
		}

		public int getFishBreedTime() {
		    return fishBreedTime;
		}

		public int getStarveTime() {
		    return starveTime;
		}


}




