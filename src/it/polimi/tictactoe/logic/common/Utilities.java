package it.polimi.tictactoe.logic.common;

import it.polimi.tictactoe.logic.intelligenceModel.StateNode;

public class Utilities {

	public static final int DEPTH = 9;
	public static int INFINITE = 2;
	public static int MAX = 1;
	public static int ZERO = 0;
	
	public StateNode setLeafNode(StateNode node, SignEnum player, boolean maxPlayer) {
	
		if(maxPlayer) {
			switch (node.getCurrentState().isOver(player)) {
				case T:	
					System.out.println("[DEBUG] ho messo MAX");
					node.setValue(Utilities.MAX);
					node.setLeaf(true);
					break;
					
				case D:
					
					node.setValue(Utilities.ZERO);
					node.setLeaf(true);
					break;
					
				case C:
					node.setValue(-Utilities.INFINITE);
					node.setLeaf(false);
				default:
					break;
			}
		}
		else {
			
			switch (node.getCurrentState().isOver(player)) {
				case T:	
					System.out.println("[DEBUG] HO MESSO -1");
					node.setValue(-Utilities.MAX);
					node.setLeaf(true);
					break;
					
				case D:
					
					node.setValue(Utilities.ZERO);
					node.setLeaf(true);
					break;
					
				case C:
					node.setValue(Utilities.INFINITE);
					node.setLeaf(false);
				default:
					break;
			}
			
		}
		return node;
						
	}
}
