package javabootcamp.game_recording;

import javabootcamp.guess.Guess;
import javabootcamp.player.Player;
import javabootcamp.submarine.Submarine;

public class GameRecord {
	
	private Player player;
	private Submarine submarine;
	private Guess [] guesses;
	
	
	public GameRecord(Player player, Submarine submarine, Guess[] guesses) {
		this.player = player;
		this.submarine = submarine;
		this.guesses = guesses;
	}


	public Player getPlayer() {
		return player;
	}


	public Submarine getSubmarine() {
		return submarine;
	}


	public Guess[] getGuesses() {
		return guesses;
	}
	

}
