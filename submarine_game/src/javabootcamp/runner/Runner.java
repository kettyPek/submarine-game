package javabootcamp.runner;


import javabootcamp.game.Game;
import javabootcamp.player.Player;

public class Runner {

	public static void main(String[] args) {
		
		Player player1 = new Player("ketty","email@mailcom",0546050505);
		Game game = new Game(player1);
		game.startdGame();
		game.replayMoves();
	}
}
