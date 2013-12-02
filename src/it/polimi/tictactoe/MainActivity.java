package it.polimi.tictactoe;



import it.polimi.tictactoe.exceptions.IllegalCoordinatesException;
import it.polimi.tictactoe.logic.common.Coordinates;
import it.polimi.tictactoe.logic.common.SignEnum;
import it.polimi.tictactoe.logic.common.Utilities;
import it.polimi.tictactoe.logic.intelligenceModel.ArtificialIntelligence;
import it.polimi.tictactoe.logic.intelligenceModel.StateNode;
import it.polimi.tictactoe.logic.model.Model;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {

	Model model ;
	ArtificialIntelligence aI;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.model = new Model();
		this.aI =  new ArtificialIntelligence();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClick(View v) {
		
		
		
		/**/TableLayout group = (TableLayout) findViewById(R.id.tableLayout1);
		TextView tv = (TextView) v;
		TableRow row;
		TextView ctv;
		
		for (int i=0; i<Model.ROW; i++){
			row = (TableRow) group.getChildAt(i);
			for(int j=0; j<Model.COLUMN; j++){
				ctv = (TextView) row.getChildAt(j);
				
				if(ctv.getId() == tv.getId()) {
					try {
						model.insert(SignEnum.X, new Coordinates(i, j));
						tv.setText("X");
						
					} catch (IllegalCoordinatesException e) {		
						System.out.println("[DEBUG] La cella è occupata!");
					}
				}
				
			}
		}
		
		
		StateNode move = aI.bestMove(new StateNode(model, Utilities.INFINITE, SignEnum.X), false);

		
		try {
			this.model = move.getCurrentState().copyModel();
			for (int i=0; i<Model.ROW; i++){
				row = (TableRow) group.getChildAt(i);
				for(int j=0; j<Model.COLUMN; j++){
					ctv = (TextView) row.getChildAt(j);
					switch (model.getCell(new Coordinates(i,j))) {
						case X:
							ctv.setText("X");
							break;
						case O:
							ctv.setText("O");
							break;
						default:
							break;
							
					}
				}
			}
		} catch (IllegalCoordinatesException e) {
			e.printStackTrace();
		}
		
		/*ArtificialIntelligence ai = new ArtificialIntelligence();
		try {
			ai.test();
		} catch (IllegalCoordinatesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
				
		
	}

}
