package javabootcamp.game;

import javabootcamp.boards.Board;
import javabootcamp.submarine.Submarine;

public class Game {
	
	protected Board board;
	protected Submarine submarines;
	
	public Game() {
		board = new Board();
		submarines = new Submarine(board);
		submarines.placeSubmarinesOnBoard();
	}
	
	public void displayBoards() {
		System.out.println("Game Board");
		board.printGameBoard();
		System.out.println("Logic Board");
		board.printLogicBoard();
	}
	
	

}
