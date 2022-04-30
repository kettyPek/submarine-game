package javabootcamp.exeptions;

public class OutOfBoardException extends Exception{
	
	private String coordinate ;
	
	public OutOfBoardException() {
		super();
		this.coordinate = "Coordinate is out of board";

	}

}
