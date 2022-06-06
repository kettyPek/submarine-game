package javabootcamp.runner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javabootcamp.menu.Menu;

public class Runner {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		
		Menu menu = new Menu();
		menu.displayMainMenu();
	}
}
