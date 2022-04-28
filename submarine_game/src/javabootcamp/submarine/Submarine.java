package javabootcamp.submarine;

import javabootcamp.boards.Board;

public class Submarine {
	
	protected final int MIN_CELLS = 1;
	protected final int MAX_CELLS = 4;
	protected final int SUBMARINES_AMOUNT = 5;
	protected Board board;
	protected int [][] tempLogicBoard;
	protected int placedSubmarines;
	protected int celssToHit ;
	
	public Submarine(Board board) {
		this.board = board;
		tempLogicBoard = board.deepCopyLogicBoard();	
		placedSubmarines = 0;
		celssToHit = 0;
	}
	
	public int getCellsToHit() {
		return SUBMARINES_AMOUNT;
	}
 
	public void placeSubmarinesOnBoard() {
		while(placedSubmarines<SUBMARINES_AMOUNT) {
			placeOneSubmarineOnBoard();
			deepTempLogicBoard();
			board.printLogicBoard();
		}
	}
	
	private void placeOneSubmarineOnBoard(){
		
		int submarineSize = (int)(Math.random()*(MAX_CELLS))+1;
		int col = (int)(Math.random()*board.getLogicBoardCols());
		int row = (int)(Math.random()*board.getLogicBoardRows());
		System.out.println("Submarine size : " + submarineSize);
		System.out.println("["+row+" , "+col+"]");
		int [] nextIndex;
		int filledCells = 0;
		for (int i=1; i<=submarineSize; i++) {
			if(submarineIsPlaceable(row,col)) { 
				tempLogicBoard[row][col] = 1;
				filledCells++;
			}
			nextIndex = newIndex(row,col);
			row = nextIndex[0];
			col = nextIndex[1];
		}
		if(filledCells!=submarineSize)
			emptyTempLogicBoard();
		else {
			placedSubmarines++;
			celssToHit += submarineSize;
		}
	}
	
	private boolean submarineIsPlaceable(int row, int col) {
		if(row<0 || row>=board.getLogicBoardRows() || col<0 || col>=board.getLogicBoardCols())
			return false;
		if(board.getLogicBoard()[row][col]==1)
			return false;
		if(!perimeterCellIsClear(row,col))
			return false;
		return true;	
	}
	
	private boolean perimeterCellIsClear(int row, int col) {
		
		if(!canBePlacedInCell(row-1,col))
			return false;
		if(!canBePlacedInCell(row+1,col))
			return false;
		if(!canBePlacedInCell(row,col-1))
			return false;
		if(!canBePlacedInCell(row,col+1))
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
	
	private void emptyTempLogicBoard() {
		for(int i=0; i<tempLogicBoard.length; i++)
			for(int j=0; j<tempLogicBoard.length; j++)
				tempLogicBoard[i][j] = 0;
	}
	
	public  void deepTempLogicBoard() {
		for (int i = 0; i < board.getLogicBoard().length; i++)
			for (int j = 0; j < board.getLogicBoard()[0].length; j++) {
				if(tempLogicBoard[i][j]==1)
					board.getLogicBoard()[i][j] = tempLogicBoard[i][j];;
			}
	}
	
	private boolean canBePlacedInCell(int row,int col) {
		if(row<0 || row>=board.getLogicBoardRows() || col<0 || col>=board.getLogicBoardRows())
			return false;
		if(board.getLogicBoard()[row][col]==1)
			return false;
		return true;
	}
}
