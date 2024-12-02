package model;
/* 
 * 
 * @author Anthony Mozloom
 * 
 * Words
 * 
 * 
 * */


public class FishCell extends ChangingCell {
	
	public FishCell(int breedTime) {
		timer = breedTime;
		canChangeStates = false;
	}
	
	@Override
	public void updateState(Cell[] neighbors, int timer, double probability) {
		//this.timer--;
	}
}