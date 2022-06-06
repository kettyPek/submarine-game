package javabootcamp.submarine;

import java.io.Serializable;

import javabootcamp.boards.Board;

public class Submarine implements Serializable{
	
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
		return celssToHit;
	}
 
	/**
	 * Places -SUBMARINES_AMOUNT- submarines on board
	 */
	public void placeSubmarinesOnBoard() {
		while(placedSubmarines<SUBMARINES_AMOUNT) {
			placeOneSubmarineOnBoard();
			deepCopyTempLogicBoard();
		}
	}
	
	/**
	 * Places randomly one submarine on the board
	 */
	private void placeOneSubmarineOnBoard(){
		
		int submarineSize = (int)(Math.random()*(MAX_CELLS))+1;
		int col = (int)(Math.random()*board.getLogicBoardCols());
		int row = (int)(Math.random()*board.getLogicBoardRows());
		int [] nextIndex; 
		int filledCells = 0;
		for (int i=1; i<=submarineSize; i++) {
			if(submarineIsPlaceable(row,col)) { 
				tempLogicBoard[row][col] = 1;
				filledCells++;
			}
			nextIndex = newIndexOnPerimeter(row,col);
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
	
	/**
	 * Checks if the submarine can be placed on a given coordinate
	 * @param row 
	 * @param col
	 * @return
	 */
	private boolean submarineIsPlaceable(int row, int col) {
		if(row<0 || row>=board.getLogicBoardRows() || col<0 || col>=board.getLogicBoardCols())
			return false;
		if(board.getLogicBoard()[row][col]==1)
			return false;
		if(tempLogicBoard[row][col]==1)
			return false;
		if(!perimeterCellIsClear(row,col))
			return false;
		return true;	
	}
	
	/**
	 * Checks if the perimeter of given cell is clear/free
	 * @param row
	 * @param col
	 * @return
	 */
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
	
	/**
	 * Returns a random index on the perimeter of given cell
	 * @param row
	 * @param col
	 * @return
	 */
	private int [] newIndexOnPerimeter(int row, int col) {
		
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
	
	/**
	 * Empties the logic board
	 */
	private void emptyTempLogicBoard() {
		for(int i=0; i<tempLogicBoard.length; i++)
			for(int j=0; j<tempLogicBoard[0].length; j++)
				tempLogicBoard[i][j] = 0;
	}
	
	/**
	 * Makes a deep copy of logic tempLogicBoard to logicBaord
	 */
	public  void deepCopyTempLogicBoard() {
		for (int i = 0; i < board.getLogicBoard().length; i++)
			for (int j = 0; j < board.getLogicBoard()[0].length; j++) {
				if(tempLogicBoard[i][j]==1)
					board.getLogicBoard()[i][j] = tempLogicBoard[i][j];;
			}
	}
	
	/**
	 * Checks of the s
	 * submarine can be place in given cell
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean canBePlacedInCell(int row,int col) {
		if(row>=0 && row<board.getLogicBoardRows() && col>=0 && col<board.getLogicBoardRows())
	 		if(board.getLogicBoard()[row][col]==1)
				return false;
		return true;
	}
}
