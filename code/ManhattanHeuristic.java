
import java.lang.Math.*;

public class ManhattanHeuristic implements AStarHeuristic{

	private int rows;
	private int cols;
	private Board currBoard;
	
	public int getCost(Board state, Board goalState)
	{
		int cost = 0;
		currBoard = state;
		//First we need to iterate through the goalState array and find where
		//the matching tile is located in the state board. 
		rows = state.tiles.length;
		cols = state.tiles[0].length;

		for(int i = 0; i<rows; i++)
		{
			 for(int j = 0; j < cols; j++)
			{
				cost += getDistance(goalState.tiles[i][j], i, j);	
			}	
		}
		
		return cost;
	}
	
	private int getDistance(int value, int goalRow, int goalCol)
	{
		int row = 0;
		int cost = 0;
		boolean found = false;
		while(!found && row < rows)
		{
			for(int col = 0; col < cols; col++)
			{
				if(currBoard.tiles[row][col] == value)
				{
					cost = Math.abs(goalRow - row) + Math.abs(goalCol - col);
					found = true;
					break; 
				}
			}
			row++;
		}
		
		return cost;
	}
}

