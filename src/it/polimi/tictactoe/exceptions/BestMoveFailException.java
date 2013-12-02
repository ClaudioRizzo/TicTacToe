package it.polimi.tictactoe.exceptions;

public class BestMoveFailException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BestMoveFailException() {
		super("[CRITICAL] Alpha Beta went wrong!");
	}

}
