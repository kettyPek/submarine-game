package javabootcamp.menu;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javabootcamp.game.Game;
import javabootcamp.game_recording.GuessesReaderWriter;
import javabootcamp.player.Player;
import javabootcamp.utils.GameScanner;

public class Menu {
	
	public static boolean gameOn ;
	public static boolean replayMode ;
	public static int gameCounter;
	
	static {
		gameOn = false;
		replayMode = false;
		gameCounter = 0;
	}
	
	public void displayMainMenu() throws IOException, InterruptedException, ExecutionException {
		System.out.println("Please select:");
		System.out.println("1 - play new game");
		System.out.println("2 - replay a game");
		enterModeByUserSeLection();
	}
	
	private void enterModeByUserSeLection() throws IOException, InterruptedException, ExecutionException {
		int selection = GameScanner.scanner.nextInt();
		switch(selection){
		case 1:
			gameOn = true;
			replayMode = false;
			gameCounter++;
			Player player1 = new Player("ketty","email@mailcom",0546050505);
			Game game = new Game(player1);
			game.startdGame();
			break;
		case 2:
			gameOn = false;
			replayMode = true;
			displayReplayMenu();
			break;
		case 3:
			GuessesReaderWriter.readRecoerdFromFILE(1);
			break;
		}	
	}
	
	private void displayReplayMenu() {
		File recordFolder = new File("GameFiles");
		int recordsAmount = recordFolder.list().length;
		System.out.println("Please select game to replay : [0," +(recordsAmount-1) +"]");
		int selection = GameScanner.scanner.nextInt();
		String fileToReplay = "GameFiles/gameNumber" + selection + ".dat";
	}
	
	
}
