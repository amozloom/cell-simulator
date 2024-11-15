package controller;

import model.Grid;
import view.ColorCells;


//simple controller class to manipulate grids behavior
public class ControlSimulationStates {
	
	private int cycleCounter;
	private boolean paused;
	private ColorCells colorCells = new ColorCells();
	
	//set the control simulation to paused as default
	public ControlSimulationStates() {
		paused = false;
		cycleCounter = 0;
	}
	
	//on every step call the update grid method, update grid deploys the fires behavior
	public void step(Grid grid) {
		if(paused != true) {
			grid.updateGrid();
		}
	}
	
	//pause the simulation
	public void pauseCycle() {
		this.paused = !this.paused;
	}
	
	
}
