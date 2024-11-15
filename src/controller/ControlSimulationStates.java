package controller;

import model.Grid;
import view.ColorCells;

public class ControlSimulationStates {
	
	private int cycleCounter;
	private boolean paused;
	private ColorCells colorCells = new ColorCells();
	
	public ControlSimulationStates() {
		//temporarily keep paused as false
		paused = false;
		cycleCounter = 0;
	}
	
	public void step(Grid grid) {
		if(paused != true) {
			grid.updateGrid();
		}
	}
	
	
	public void pauseCycle() {
		this.paused = !this.paused;
	}
	
	
}
