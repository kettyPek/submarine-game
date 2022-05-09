package javabootcamp.menu;

import javabootcamp.game.Game;
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
	
	public void displayMainMenu() {
		System.out.println("Please select:");
		System.out.println("1 - play new game");
		System.out.println("2 - replay a game");
		enterModeByUserSeLection();
	}
	
	private void enterModeByUserSeLection() {
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
		}	
	}
	
	private void displayReplayMenu() {
		System.out.println("Please select game to replay:");
		int selection = GameScanner.scanner.nextInt();
		String fileToReplay = "GameFiles/gameNumber" + selection + ".dat";
		//replay game function
	}
	
	

}
