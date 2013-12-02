package it.polimi.tictactoe.exceptions;

public class IllegalCoordinatesException extends Exception {

	private static final long serialVersionUID = -7982710754822080014L;

	public IllegalCoordinatesException(String message) {
		super(message);
	}
	
	public IllegalCoordinatesException(){} 
}
