package it.polimi.tictactoe.logic.model;

import it.polimi.tictactoe.exceptions.IllegalCoordinatesException;
import it.polimi.tictactoe.logic.common.Coordinates;
import it.polimi.tictactoe.logic.common.ResultEnum;
import it.polimi.tictactoe.logic.common.SignEnum;

import java.util.HashMap;
import java.util.Map;


public class Model {
	
	private Map<Coordinates, SignEnum> table;
	
	public static int ROW = 3;
	public static int COLUMN = 3;
	
	public Model() {
		
		this.table = new HashMap<Coordinates, SignEnum>();
		initialize();
			
		
	}
	
	public ResultEnum isOver(SignEnum last) {
		
		if(isTris(last)) {
			return ResultEnum.T;
		}
		else if(isFull()) {
			return ResultEnum.D;
		}
		else {
			return ResultEnum.C;
		}
		
	}
	
	public void insert(SignEnum s, Coordinates c) throws IllegalCoordinatesException {
		
		checkBounds(c);
		checkBusy(c);
		this.table.put(c, s);
		
	}
	
	public SignEnum getCell(Coordinates c) {
		return this.table.get(c);
	}
	
	private boolean isFull() {
		
		if(this.table.containsValue(SignEnum.N))
			return false;
		else
			return true;

	}
	
	private boolean isTris(SignEnum s) {
		
		int counter=0;
		//controlla se tris nelle righe
		for(int i=0; i < Model.ROW ; i++) {
			for(int j=0; j < Model.COLUMN; j++) {
				Coordinates currentC = new Coordinates(i, j);
				
				if(!this.table.get(currentC).equals(s)) {
					counter = 0;
					break;
				}
				else {
					counter++;
				}
			}
			if(counter == 3) {
				return true;
			}
			
		}
		
		//controllo colonne
		for(int i=0; i < Model.COLUMN ; i++) {
			for(int j=0; j < Model.ROW; j++) {
				Coordinates currentC = new Coordinates(j, i);
				
				if(!this.table.get(currentC).equals(s)) {
					counter = 0;
					break;
				}
				else {
					counter++;
				}
			}
			if(counter == 3) {
				return true;
			}
			
		}
		
		//controllo diagonale principale
		for(int k=0; k<Model.ROW; k++) {
			Coordinates currentC = new Coordinates(k, k);
			
			if(!this.table.get(currentC).equals(s)) {
				counter = 0;
				break;
			}
			counter++;
			
		}
		
		if(counter == 3) {
			return true;
		}
		
		//diagonale secondaria
		for(int k=0; k<Model.ROW; k++) {
			Coordinates currentC = new Coordinates(k, Model.ROW -k -1);
			
			if(!this.table.get(currentC).equals(s)) {
				counter = 0;
				break;
			}
			counter++;
			
		}
		
		if(counter == 3) {
			return true;
		}
		
		return false;
			
	}
	
	
	private void checkBusy(Coordinates c) throws IllegalCoordinatesException {
		
		
		if( !(this.table.get(c).equals(SignEnum.N)) ) {
			throw new IllegalCoordinatesException("Busy");
		}
		
	}


	private void checkBounds(Coordinates c) throws IllegalCoordinatesException {
		
		if(c.getX() > 2 || c.getX() < 0 || c.getY() > 2 || c.getY() < 0 ) {
			throw new IllegalCoordinatesException();
		}
		
	}

	public Model copyModel() throws IllegalCoordinatesException {
		
		Model copy = new Model();
		
		for(int i=0; i < ROW; i++) {
			
			for(int j=0; j<COLUMN; j++) {
				Coordinates c = new Coordinates(i, j);
				SignEnum se = this.table.get(c);
				copy.insert(se, c);
			}
			
		}
		return copy;
		
		
		
	}

	private void initialize() {
		
		for(int i=0; i < ROW; i++) {
			
			for(int j=0; j<COLUMN; j++) {
				Coordinates c = new Coordinates(i, j);
				this.table.put(c, SignEnum.N);
			}
			
		}
		
	}
	
	
	@Override
	public String toString(){
		
		String s = "";
		for(int i=0; i<Model.ROW; i++) {
			for(int j=0; j<Model.COLUMN; j++) {
				s = s + this.table.get(new Coordinates(i, j));
			}
			 s = s + "\n";
		}
		return s;
	}

}
