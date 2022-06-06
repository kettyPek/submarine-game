package javabootcamp.game_recording;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.google.gson.Gson;

import javabootcamp.guess.Guess;
import javabootcamp.menu.Menu;
import javabootcamp.submarine.Submarine;

public class GuessesReaderWriter {

	public static void EnterGuesses(Guess[] guesses) {

		try (FileOutputStream output = new FileOutputStream("GameFiles/geussesRecord.dat");
				ObjectOutputStream objetOutput = new ObjectOutputStream(output)) {
			for (Guess guess : guesses)
				objetOutput.writeObject(guess);
		} catch (IOException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static Guess[] ReadGuesses() {
		Guess[] guesses = new Guess[100];
		int i = 0;
		try (FileInputStream file = new FileInputStream("GameFiles/geussesRecord.dat");
				ObjectInputStream objStream = new ObjectInputStream(file);) {
			do {
				guesses[i] = (Guess) objStream.readObject();
				i++;
			} while (guesses[i] != null);
		} catch (IOException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return guesses;
	}

	public static void createFileWithCurrentSubmarines(Submarine submarines) {
		String fileLocation = "GameFiles\\replayGame" + Menu.gameCounter + ".dat";
		System.out.println("counter " + Menu.gameCounter);
		System.out.println(fileLocation);
		File file = new File(fileLocation);
		try {
			file.createNewFile();
			try (FileOutputStream output = new FileOutputStream(file);
					ObjectOutputStream objetOutput = new ObjectOutputStream(output)) {
				objetOutput.writeObject(submarines);
			} catch (IOException e) {
				System.out.println(e);
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (IOException e) {
			System.out.println("could not create file");
		}
	}

	public static GameRecord readRecoerdFromFILE(int recordNum) throws IOException {
		GameRecord record = null;

		Path path = Paths.get("GameFiles\\replayGame" + recordNum + ".dat");

		AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

		ByteBuffer buffer = ByteBuffer.allocate((int) fileChannel.size());

		Future<Integer> operation = fileChannel.read(buffer, 0);

		while (!operation.isDone())
			;

		buffer.flip();
		byte[] data = new byte[buffer.limit()];
		buffer.get(data);
		System.out.println(new String(data));
		buffer.clear();

		return record;
	}

	public static void createRecordFile(GameRecord gameRecord)
			throws IOException, InterruptedException, ExecutionException {

		Path path = Paths.get("GameFiles\\replayGame" + Menu.gameCounter + ".dat");

		AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

		ByteBuffer buffer = ByteBuffer.allocate((int) fileChannel.size());
		long position = 0;

		Gson gson = new Gson();
		String data = gson.toJson(gameRecord, GameRecord.class);

		buffer.put(data.getBytes());
		buffer.clear();

		Future<Integer> numBytes = fileChannel.read(buffer, 0);
		while (!numBytes.isDone())
			;

		System.out.println("Write done");

//		String fileLocation = "GameFiles\\replayGame" + Menu.gameCounter + ".dat";
//		
//		File file = new File(fileLocation);
//		try {
//			file.createNewFile();
//			try (FileOutputStream output = new FileOutputStream(file);
//					ObjectOutputStream objetOutput = new ObjectOutputStream(output)) {
//				objetOutput.writeObject(gameRecord);
//			}catch(IOException e){
//				System.out.println(e);
//			}
//			catch(Exception e) {
//				System.out.println(e);
//			}
//		}catch(IOException e) {
//			System.out.println("could not create file");
//		}
	}
}
