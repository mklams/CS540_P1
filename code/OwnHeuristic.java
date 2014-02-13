
import java.util.ArrayList;
public class OwnHeuristic implements AStarHeuristic{
	public int getCost(Board state, Board goalState)
	{
		int cost = 0;
		int rows = state.tiles.length;
		int cols = state.tiles[0].length;
	//This heuristic is a modification of the Manhattan heuristic. Not only will it 
	//consider the distance a tile has to travel, it will also look for linear conflict. 
	//This is when two tiles are in the correct row or column but the tiles are on the wrong
	//side of each other. Each time this happens, we can guarantee that there will be at least
	//two extra moves that one of the tiles will have to make.	
	
		//First get the cost from the Manhattan Heuristic.  
		ManhattanHeuristic manhattan = new ManhattanHeuristic();
		cost = manhattan.getCost(state, goalState); 
		
		//Now go back and add cost for linear conflict. 
		//First check the row
		for(int i = 0; i < rows; i++)
		{
			int[] currRow = state.tiles[i];
			int[] goalRow = goalState.tiles[i];
			cost += conflictCost(currRow, goalRow);
		}
		
		//Now evaluate the cols. To do this we will put the state col and the goal
		//col int a 1d array and send it to the conflictCost function.
		for(int i = 0; i < cols; i++)
		{
			int[] currCol = getColArray(i, state.tiles);
			int[] goalCol = getColArray(i, goalState.tiles);	
			cost += conflictCost(currCol, goalCol);
		}			

		return cost;
	}

	private int[] getColArray(int pos, int[][] board)
	{
		int[] cols = new int[board.length];
		for(int i = 0; i < cols.length; i++)
		{
			cols[i] = board[i][pos];
		}	
		return cols;
	}

	private int conflictCost(int[] currLine, int[] goalLine)
	{
		int cost = 0;
		int error = 0;
		int[] invalidPos = new int[goalLine.length];
		for(int i = 0; i<currLine.length; i++)
		{
			for(int j = 0; j<goalLine.length; j++)
			{
				//We will ignore -1 spaces since there can be multiple of them
				//which will not work with this algo
				if(currLine[i] == goalLine[j] && currLine[i] != -1)
				{
					invalidPos[error] = j;	
					error++;
				}
			}
		}

		for(int i = 0; i < error; i++)
		{
			for(int j = i+1; j<error; j++)
			{
				if(invalidPos[i] > invalidPos[j])
					cost +=2;
			}
		}
		
		return cost;
	}
	
}
