package javabootcamp.boards;

public class Board {

	protected int[][] logicBoard;
	protected char[][] gameBoard;
	protected final int LOGIC_BOARD_ROWS = 10;
	protected final int LOGIC_BOARD_COLS = 20;
	protected final int GAME_BOARD_ROWS = LOGIC_BOARD_ROWS + 2;
	protected final int GAME_BOARD_COLS = LOGIC_BOARD_COLS + 2;

	public Board() {
		logicBoard = new int[LOGIC_BOARD_ROWS][LOGIC_BOARD_COLS];
		gameBoard = new char[GAME_BOARD_ROWS][GAME_BOARD_COLS];
		makeGameBoardFrame();	
	}

	public int [][] deepCopyLogicBoard() {
		int [][] copyLogicBoard = new int [LOGIC_BOARD_ROWS][LOGIC_BOARD_COLS];
		for (int i = 0; i < LOGIC_BOARD_ROWS; i++)
			for (int j = 0; j < LOGIC_BOARD_COLS; j++) 
				copyLogicBoard[i][j] = logicBoard[i][j];
		return copyLogicBoard;
	}
	
	public int [][] getLogicBoard(){
		return logicBoard;
	}
	
	public int getLogicBoardRows() {
		return LOGIC_BOARD_ROWS;
	}
	
	public int getLogicBoardCols() {
		return LOGIC_BOARD_COLS;
	}
	
	//
	private void makeGameBoardFrame() {
		for (int i = 0; i < GAME_BOARD_ROWS; i++)
			for (int j = 0; j < GAME_BOARD_COLS; j++) {
				if (i == 0 || i == GAME_BOARD_ROWS - 1)
					gameBoard[i][j] = '*';
				else if (j == 0 || j == GAME_BOARD_COLS - 1)
					gameBoard[i][j] = '*';
				else 
					gameBoard[i][j] = ' ';
			}
	}
	
	public void printGameBoard() {
		for (int i = 0; i < GAME_BOARD_ROWS; i++) {
			for (int j = 0; j < GAME_BOARD_COLS; j++)
				System.out.print(gameBoard[i][j]);
			System.out.println("");
		}
	}
	
	public void printLogicBoard() {
		for (int i = 0; i < LOGIC_BOARD_ROWS; i++) {
			for (int j = 0; j < LOGIC_BOARD_COLS; j++)
				System.out.print(logicBoard[i][j]);
			System.out.println("");
		}
	}


}
