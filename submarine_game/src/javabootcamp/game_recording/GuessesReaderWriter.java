package javabootcamp.game_recording;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javabootcamp.guess.Guess;
import javabootcamp.menu.Menu;
import javabootcamp.submarine.Submarine;

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
	
	public static void createFileWithCurrentSubmarines(Submarine submarines)  {
		String fileLocation = "GameFiles\\replayGame" + Menu.gameCounter + ".dat";
		System.out.println("counter " + Menu.gameCounter);
		System.out.println(fileLocation);
		File file = new File(fileLocation);
		try {
			file.createNewFile();
			try (FileOutputStream output = new FileOutputStream(file);
					ObjectOutputStream objetOutput = new ObjectOutputStream(output)) {
				objetOutput.writeObject(submarines);
			}catch(IOException e){
				System.out.println(e);
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}catch(IOException e) {
			System.out.println("could not create file");
		}
	}
}
