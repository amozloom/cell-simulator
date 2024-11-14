package controller;

public class ControlSimulationStates {
	
	private int cycleCounter;
	private boolean paused;
	
	public ControlSimulationStates() {
		paused = false;
		cycleCounter = 0;
	}
	
	public void step(boolean paused) {
		if(paused != true) {
		doOneCycle(cycleCounter);
		}
		
		
	}
	
	
	public void doOneCycle(double elapsedTime) {
		elapsedTime +=1;
		System.out.println(elapsedTime);
	}
	
	public void pauseCycle() {
		this.paused = !this.paused;
	}
	
	
}
