package javabootcamp.guess;

import java.io.Serializable;

public class Guess implements Serializable{
	
	protected int guessNumber;
	protected int row;
	protected int col;
	
	public Guess(int guessNumber,int row, int col) {
		this.guessNumber = guessNumber;
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}

	@Override
	public String toString() {
		return "Guess [guessNumber=" + guessNumber + ", row=" + row + ", col=" + col + "]";
	}
	
	
	
	
	

}
