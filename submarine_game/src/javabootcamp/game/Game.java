package javabootcamp.game;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javabootcamp.boards.Board;
import javabootcamp.exeptions.OutOfBoardException;
import javabootcamp.exeptions.OutOfTargetsException;
import javabootcamp.game_recording.GameRecord;
import javabootcamp.game_recording.GuessesReaderWriter;
import javabootcamp.guess.Guess;
import javabootcamp.menu.Menu;
import javabootcamp.player.Player;
import javabootcamp.submarine.Submarine;
import javabootcamp.utils.GameScanner;

public class Game {
	
	protected Board board;
	protected Submarine submarines;
	protected int leftGuesses;
	protected int points;
	protected int numberOfHits;
	protected int numberOfMisses;
	protected int consecutiveGuesses;
	protected int row;
	protected int col;
	protected Guess [] guesses;
	protected Player player;
	
	
	public Game(Player player) {
		board = new Board();
		submarines = new Submarine(board);
		submarines.placeSubmarinesOnBoard();
		leftGuesses = 100;
		points = 1000;
		numberOfHits = 0;
		numberOfMisses = 0;
		consecutiveGuesses = 0;
		guesses = new Guess[leftGuesses];
		this.player = player;
	}
	
	/**
	 * Starts the game
	 * @throws IOException 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public void startdGame() throws IOException, InterruptedException, ExecutionException {
		GuessesReaderWriter.createFileWithCurrentSubmarines(submarines);
		board.printLogicBoard();
		while(leftGuesses>0) {	
			System.out.println("Enter row:");
			row = getUsersInput(board.getLogicBoardRows());
			if(Menu.gameOn) {
				System.out.println("Enter collumn");
				col = getUsersInput(board.getLogicBoardCols());
				addGuessToGuessArray();
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
		
		GuessesReaderWriter.createRecordFile(new GameRecord(player,submarines,guesses));
		GuessesReaderWriter.EnterGuesses(guesses);
		
		GameScanner.scanner.close();
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
		leftGuesses--;
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
		String inputAsString = GameScanner.scanner.next();
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
				inputAsString = GameScanner.scanner.next();
			}catch(OutOfBoardException e) {
				System.out.println("given value is out out of board dimentions, enter values in the range [0,"+(boardDimention-1)+"]");
				inputAsString = GameScanner.scanner.next();
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
	
	private void addGuessToGuessArray() {
		guesses[100-leftGuesses] = new Guess(100-leftGuesses+1,row,col);
		System.out.println(guesses[100-leftGuesses]);
	}
	
	public void replayMoves() {
		Guess [] guessesToReplay = GuessesReaderWriter.ReadGuesses();
		
		int index = 0;
		System.out.println(guessesToReplay[index]);
		while(guessesToReplay[index]!=null) {
			row = guessesToReplay[index].getRow();
			col = guessesToReplay[index].getCol();
			displayGameStatus();
			index++;
			try {
				tryCoordinates();
				TimeUnit.SECONDS.sleep(5);
			} catch (OutOfTargetsException e) {
				System.out.println(e);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
			
		}
		
	}
	
	

}
