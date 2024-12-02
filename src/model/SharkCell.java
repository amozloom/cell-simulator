package model;
/* 
 * 
 * @author Anthony Mozloom
 * 
 * Words
 * 
 * 
 * */

import javafx.scene.paint.Color;

public class SharkCell extends ChangingCell {
	
	private int starveTime;
	private int savedStarveTime;
	private boolean alive;
	
	public SharkCell(int breedTime, int starveTime) {
		timer = breedTime;
		this.starveTime = starveTime;
		savedStarveTime = starveTime;
		alive = true;
	}
	
	@Override
	public void updateState(Cell[] neighbors, int timer, double probability) {
		starveTime--;
		this.timer--;
		for (Cell cell : neighbors) {
			if (!cell.canChangeStates() && cell.getTimer() >= 0) {
				starveTime = savedStarveTime;
			} else if (starveTime == 0) {
				alive = false;
			}
		}
		if (this.timer == 0) {
			this.timer = timer;
		}
	}
	
	public boolean aliveStatus() {
		return alive;
	}
	public int getStarveTime() {
		return starveTime;
	}
	
	public Color getColor() {
        return Color.GRAY;
    }
}