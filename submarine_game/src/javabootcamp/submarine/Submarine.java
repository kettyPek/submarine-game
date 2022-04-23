package javabootcamp.submarine;

import javabootcamp.boards.Board;

public class Submarine {
	
	protected final int MIN_CELLS = 1;
	protected final int MAX_CELLS = 4;
	protected final int SUBMARINES_AMOUNT = 2;
	protected Board board;
	protected int [][] logicBoard;
	protected int [][] tempLogicBoard;
	protected int placedSubmarines;
	
	public Submarine(Board board) {
		this.board = board;
		logicBoard = board.getLogicBoard();
		tempLogicBoard = board.deepCopyLogicBoard();	
		placedSubmarines = 0;
	}
	
 
	public void placeSubmarinesOnBoard() {
		while(placedSubmarines<SUBMARINES_AMOUNT) {
			placeOneSubmarineOnBoard();	
			board.printLogicBoard();
		}
	}
	
	private void placeOneSubmarineOnBoard(){
		
		int cellsNumber = (int)(Math.random()*(MAX_CELLS))+1;
		int row = (int)(Math.random()*(board.getLogicBoardCols()+1));
		int col = (int)(Math.random()*(board.getLogicBoardRows()+1));
		System.out.println("Cells number : "+cellsNumber);
		System.out.println(row+" , "+col);
		int [] nextIndex;
		for (int i=1; i<=cellsNumber; i++) {
			if(submarineIsPlaceable(row,col)) { 
				tempLogicBoard[row][col] = 1;
				placedSubmarines++;
			}
			nextIndex = newIndex(row,col);
			row = nextIndex[0];
			col = nextIndex[1];
		}
		deepCopyTempLogicBoard();
	}
	
	private boolean submarineIsPlaceable(int row, int col) {
		if(row<0 || row>board.getLogicBoardRows() || col<0 || col>board.getLogicBoardCols())
			return false;
		if(logicBoard[row][col]==1)
			return false;
		if(!perimeterCellIsClear(row,col))
			return false;
		return true;	
	}
	
	private boolean perimeterCellIsClear(int row, int col) {
		
		if(row-1>=0 && tempLogicBoard[row-1][col]==1)
			return false;
		if(row+1<board.getLogicBoardRows() && logicBoard[row+1][col]==1)
			return false;
		if(col-1>=0 && logicBoard[row][col-1]==1)
			return false;
		if(col+1<board.getLogicBoardCols() && logicBoard[row][col+1]==1)
			return false;
		return true;
	}
	
	private int [] newIndex(int row, int col) {
		
		int [] index_row_col = {row,col};
		int moveToRow = (int)(Math.random()*3)-1;
		int moveToCol;
		if(moveToRow!=0) {
			index_row_col[0] = moveToRow+row;
		}
		else
		{
			moveToCol = (int)(Math.random()*3)-1;
			index_row_col[1] = moveToCol+col;
		}
		return index_row_col;	
	}
	
	private void deepCopyTempLogicBoard(){
		for (int i=0; i<board.getLogicBoardRows(); i++)
			for(int j=0; j<board.getLogicBoardCols(); j++)
				logicBoard[i][j] = tempLogicBoard[i][j];
	}
}
