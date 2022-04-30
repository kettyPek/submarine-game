package javabootcamp.game;

import java.util.Scanner;

import javabootcamp.boards.Board;
import javabootcamp.exeptions.OutOfBoardException;
import javabootcamp.exeptions.OutOfTargetsException;
import javabootcamp.menu.Menu;
import javabootcamp.submarine.Submarine;

public class Game {
	
	protected Board board;
	protected Submarine submarines;
	protected int gusses;
	protected int points;
	protected int numberOfHits;
	protected int numberOfMisses;
	protected int consecutiveGuesses;
	protected int row;
	protected int col;
	
	Scanner scanner = new Scanner(System.in);
	
	public Game() {
		board = new Board();
		submarines = new Submarine(board);
		submarines.placeSubmarinesOnBoard();
		gusses = 100;
		points = 1000;
		numberOfHits = 0;
		numberOfMisses = 0;
		consecutiveGuesses = 0;
	}
	
	/**
	 * Starts the game
	 */
	public void startdGame() {
		board.printLogicBoard();
		while(gusses>0) {	
			System.out.println("Enter row:");
			row = getUsersInput(board.getLogicBoardRows());
			if(Menu.gameOn) {
				System.out.println("Enter collumn");
				col = getUsersInput(board.getLogicBoardCols());
			}
			if(Menu.gameOn) {
				try {
					tryCoordinates();
					displayGameStatus();
				}catch(OutOfTargetsException e){
					System.out.println(e.getMessage());
					Menu.gameOn = false;
				}	
			}
			else {
				System.out.println("you decided to quit");
				break;	
			}
		}
		scanner.close();
	}
	
	/**
	 * Displays games game and logic boards
	 */
	public void displayBoards() {
		System.out.println("Game Board");
		board.printGameBoard();
		System.out.println("Logic Board");
		board.printLogicBoard();
	}
	
	/**
	 * Displays game board, score, hits and misses
	 */
	private void displayGameStatus() {
		System.out.println("Current score: " + points);
		System.out.println("Number of hits: " + numberOfHits);
		System.out.println("Number of misses: "+ numberOfMisses);
		board.printGameBoard();
		board.printLogicBoard();
	}
	
	private void checkPlayersGuess() {
		if(board.guessIsHit(row,col))
				updateHit();
		else
			updateMiss();
		gusses--;
	}
	
	private void updateHit() {
		if(consecutiveGuesses>1)
			points += 1000;
		else
			points += 200;
		numberOfHits++;	
	}
	
	private void updateMiss() {
		consecutiveGuesses = 0;
		numberOfMisses++;
		points -= 10;
	}
	
	private int getUsersInput( int boardDimention) {
		String inputAsString = scanner.next();
		int scannedInput = -1;
		int valueToReturn = -1;
		while(true) {
			try {
				scannedInput = Integer.parseInt(inputAsString);
				valueToReturn = defineRowValue(scannedInput, boardDimention);
				break;
			} catch (NumberFormatException e) {
				if(inputAsString.equals("q")) {
					Menu.gameOn = false;
					break;
				}
				System.out.println("invalid input, try again");
				inputAsString = scanner.next();
			}catch(OutOfBoardException e) {
				System.out.println("given value is out out of board dimentions, enter values in the range [0,"+(boardDimention-1)+"]");
				inputAsString = scanner.next();
			}
		}
		return valueToReturn;
	}
	
	private int defineRowValue(int coordinate, int boardDimention) throws OutOfBoardException{
		if(coordinate<0 || coordinate>=boardDimention)
			throw new OutOfBoardException();
		return coordinate;
	}
	
	private void tryCoordinates() throws OutOfTargetsException {
		if(numberOfHits>=submarines.getCellsToHit())
			throw new OutOfTargetsException();
		checkPlayersGuess();	
	}
	
	

}
