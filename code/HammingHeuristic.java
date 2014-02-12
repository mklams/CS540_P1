
public class HammingHeuristic implements AStarHeuristic{
	public int getCost(Board state, Board goalState)
	{
		//Need to loop over the 2d array that represents the board. 
		//Count how many tiles in state don't match goalstate. 
		int mismatches = 0;
		int rows = state.tiles.length;
		int cols = state.tiles[0].length;
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				if(state.tiles[i][j] != goalState.tiles[i][j])
					mismatches++;
			}
		}		

		return mismatches;
	}
}

