package javabootcamp.game_recording;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javabootcamp.guess.Guess;

public class GuessesReaderWriter {
	
	public static void EnterGuesses(Guess [] guesses){
		
		try (FileOutputStream output = new FileOutputStream("GameFiles/geussesRecord.dat");
				ObjectOutputStream objetOutput = new ObjectOutputStream(output)) {
			for(Guess guess: guesses) 
				objetOutput.writeObject(guess);
		}catch(IOException e){
			System.out.println(e);
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}
	
	public static Guess [] ReadGuesses(){
		Guess [] guesses = new Guess [100];
		int i =0;
		try (FileInputStream file = new FileInputStream("GameFiles/geussesRecord.dat");
				ObjectInputStream objStream = new ObjectInputStream(file);) {
			do {
				guesses[i] = (Guess)objStream.readObject();
				i++;
			}while(guesses[i]!=null);
		}catch(IOException e){
			System.out.println(e);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return guesses;
	}

}
