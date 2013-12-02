package it.polimi.tictactoe.logic.intelligenceModel;

import it.polimi.tictactoe.exceptions.BestMoveFailException;
import it.polimi.tictactoe.exceptions.IllegalCoordinatesException;
import it.polimi.tictactoe.logic.common.Coordinates;
import it.polimi.tictactoe.logic.common.SignEnum;
import it.polimi.tictactoe.logic.common.Utilities;
import it.polimi.tictactoe.logic.model.Model;

import java.util.ArrayList;
import java.util.List;

public class ArtificialIntelligence {

	
	public StateNode bestMove(StateNode node, boolean maxPlayer) {
		
		
		List<StateNode> children = node.getChildren(maxPlayer);
		
		/*int value = Utilities.INFINITE;
		
		for(StateNode n: children) {
			n.setValue(alphaBeta(n, Utilities.DEPTH, -Utilities.INFINITE, Utilities.INFINITE, !maxPlayer));
			
			if(n.getValue() < value) {
				value = n.getValue();
			}
			
		}*/
		int value = this.bestValueForMinPlayer(children, !maxPlayer);
		for(StateNode n: children) {
			if(value == n.getValue()) {
				return n;
			}
				
		}
		throw new BestMoveFailException();
		
	}
	
	private int bestValueForMinPlayer(List<StateNode> nodes, boolean maxPlayer) {
		
		int value = Utilities.INFINITE;
		
		for (StateNode n: nodes) {
			n.setValue(alphaBeta(n, Utilities.DEPTH, -Utilities.INFINITE, Utilities.INFINITE, maxPlayer));
			
			if(n.getValue() < value)
				value = n.getValue();
		}
		
		return value;
		
	}
	
	private int alphaBeta(StateNode node, int depth, int alpha, int beta, boolean maxPlayer) {
		
		if(depth == 0 || node.isLeaf()) {
			return node.getValue();	
		}
		
		if(maxPlayer) {
			for(StateNode child : node.getChildren(maxPlayer)) {
				alpha = max(alpha, alphaBeta(child, depth - 1, alpha, beta, false));
				if(beta <= alpha) {
					break;
				}
			}
			return alpha;
		}
		else {
			for(StateNode child : node.getChildren(maxPlayer)) {
				beta = min(beta, alphaBeta(child, depth-1, alpha, beta, true));
				if(beta <= alpha) {
					break;
				}
			}
			return beta;
		}
		
	}

	
	
	private int min(int x, int y) {
		
		return x <= y ? x : y;
	}

	private int max(int x, int y) {
		
		return x >= y ? x : y;
	}
	
}
