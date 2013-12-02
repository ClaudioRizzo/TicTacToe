package it.polimi.tictactoe.logic.intelligenceModel;

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
		
		int value = Utilities.INFINITE;
		
		for(StateNode n: children) {
			n.setValue(alphaBeta(n, Utilities.DEPTH, -Utilities.INFINITE, Utilities.INFINITE, !maxPlayer));
			
			if(n.getValue() < value) {
				value = n.getValue();
			}
			
		}
		
		for(StateNode n: children) {
			if(value == n.getValue()) {
				return n;
			}
				
		}
		return null; 
		
		
		
		
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

	/**/public void test() throws IllegalCoordinatesException {
		Model m = new Model();
		m.insert(SignEnum.X, new Coordinates(0, 1));
		m.insert(SignEnum.O, new Coordinates(1, 1));
		m.insert(SignEnum.X, new Coordinates(2, 0));
		m.insert(SignEnum.O, new Coordinates(1, 0));
		m.insert(SignEnum.X, new Coordinates(2, 1));
		//m.insert(SignEnum.O, new Coordinates(2, 0));
		//m.insert(SignEnum.X, new Coordinates(2, 2));
		
		//System.out.println(m.isOver(SignEnum.X));
		StateNode n = new StateNode(m, Utilities.INFINITE, SignEnum.X);
		System.out.println("[DEBUG] ab value: "+alphaBeta(n, Utilities.DEPTH, -Utilities.INFINITE, Utilities.INFINITE, true));
		StateNode move = bestMove(n, false);
		System.out.println("[DEBUG] sono il valore computato: "+move.getValue());
		System.out.println(move.getCurrentState());
	}
	
	private int min(int x, int y) {
		
		return x <= y ? x : y;
	}

	private int max(int x, int y) {
		
		return x >= y ? x : y;
	}
	
	/*List<StateNode> childs = new ArrayList<StateNode>();
		
		childs = node.getChilds(maxPlayer);
		StateNode nextNode = null;
		
		if(maxPlayer) {
			
			int value = -Utilities.INFINITE;
			for(StateNode child: childs) {
				int tmp = alphaBeta(child, Utilities.DEPTH, -Utilities.INFINITE, Utilities.INFINITE, maxPlayer);
				//System.out.println("[DEBUG] sono tmp: "+tmp);
				if(tmp > value) {
					value = tmp;
					nextNode = child;
					
				}
			}
			
		} else {
			
			int value = Utilities.INFINITE;
			for(StateNode child: childs) {
				int tmp = alphaBeta(child, Utilities.DEPTH, -Utilities.INFINITE, Utilities.INFINITE, maxPlayer);
				//System.out.println("[DEBUG] sono tmp: "+tmp);
				if(tmp < value) {
					value = tmp;
					nextNode = child;
					
				}
			}
			
		}
			
		
		return nextNode;*/
}
