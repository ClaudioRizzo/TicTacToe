package it.polimi.tictactoe.logic.intelligenceModel;

import java.util.ArrayList;
import java.util.List;
import it.polimi.tictactoe.exceptions.IllegalCoordinatesException;
import it.polimi.tictactoe.logic.common.Coordinates;
import it.polimi.tictactoe.logic.common.SignEnum;
import it.polimi.tictactoe.logic.common.Utilities;
import it.polimi.tictactoe.logic.model.Model;



public class StateNode {

	private Model currentState;
	private SignEnum lastPlayer;
	private boolean leaf;
	private Utilities u;
	
	private int value;
	
	public StateNode(Model currentState, int value, SignEnum computerPlayer) {
		this.currentState = currentState;
		this.lastPlayer = computerPlayer;
		this.value = value;
		this.leaf = false;
		u = new Utilities();
	}

	
	public Model getCurrentState() {
		return currentState;
	}

	public void setCurrentState(Model currentState) {
		this.currentState = currentState;
	}


	public List<StateNode> getChildren(boolean maxPlayer) {
		
		List<StateNode> childs = new ArrayList<StateNode>();
		
		for(int i=0; i<Model.ROW; i++) {
			for(int j=0; j<Model.COLUMN; j++) {

				try {
					
					Model tmp = this.currentState.copyModel();
					
					SignEnum currentPlayer;
					switch(this.lastPlayer) {
						case X: 
							currentPlayer = SignEnum.O;
							break;
						case O: 
							currentPlayer = SignEnum.X;
							break;
						default: 
							currentPlayer = SignEnum.N;
							break;
					}
					
					tmp.insert(currentPlayer, new Coordinates(i, j));
					
					StateNode child = new StateNode(tmp, 0, currentPlayer);
					this.u.setLeafNode(child, currentPlayer, maxPlayer);
					childs.add(child);
								
				} catch (IllegalCoordinatesException e) {
					continue;
				}
				
				
			}
		}
		
		return childs;
		
	}
	
	
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}


	public boolean isLeaf() {
		return leaf;
	}
	
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}


}
