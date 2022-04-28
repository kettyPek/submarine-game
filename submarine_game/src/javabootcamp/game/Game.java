package javabootcamp.game;

import java.util.Scanner;

import javabootcamp.boards.Board;
import javabootcamp.submarine.Submarine;

public class Game {
	
	protected Board board;
	protected Submarine submarines;
	protected int gusses = 100;
	protected int points = 1000;
	protected int numberOfHits = 0;
	protected int numberOfMisses = 0;
	
	Scanner scanner = new Scanner(System.in);
	
	public Game() {
		board = new Board();
		submarines = new Submarine(board);
		submarines.placeSubmarinesOnBoard();
	}
	
	public void startdGame() {
		int row;
		int col;
		while(numberOfHits!=submarines.getCellsToHit()) {
			System.out.println("enter row:");
			row = scanner.nextInt();
			System.out.println("entwr column:");
			col = scanner.nextInt();
			board.checkIfHit(row,col);
			displayGameStatus();
		}
		scanner.close();
	}
	
	public void displayBoards() {
		System.out.println("Game Board");
		board.printGameBoard();
		System.out.println("Logic Board");
		board.printLogicBoard();
	}
	
	private void displayGameStatus() {
		System.out.println("Current score: " + points);
		System.out.println("Number of hits: " + numberOfHits);
		System.out.println("Number of misses: "+ numberOfMisses);
		board.printGameBoard();
	}
	

}
