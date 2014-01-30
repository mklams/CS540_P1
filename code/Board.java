import java.util.ArrayList;
public class Board {
	public static int rows=5;
	public static int columns=3;
	private Board parent = null; /* only initial board's parent is null */
	public int[][] tiles;
	private int blank_row;
	private int blank_col;

	public Board(int[][] cells)                 //Populate states
	{
	  tiles = new int[rows][columns];
		for (int i = 0 ;i<rows; i++)
			for (int j = 0; j<columns; j++)
			{
				tiles[i][j] = cells[i][j];
			}
	}
	public boolean equals(Object x)         //Can be used for repeated state checking
	{
		Board another = (Board)x;
		if (columns != another.columns || rows != another.rows) return false;
		for (int i = 0; i<rows; i++)
			for (int j = 0; j<columns; j++)
				if (tiles[i][j] != another.tiles[i][j])
					return false;
		return true;
	}
	public ArrayList<Board> getSuccessors()     //Use cyclic ordering for expanding nodes - Right, Down, Left, Up
	{
		ArrayList<Board> successors = new ArrayList<Board>();
		this.findBlankPos();	
		
		//first try and move the blank to the right. If it is a legal move, add it to successors
		if(blank_col != 0 && tiles[blank_row][blank_col + 1] == -1)
		{
			Board rightBoard = new Board(tiles);
		}
	}
	
	public void print()
	{
		for (int i = 0; i<rows; i++)
		{
			for (int j = 0 ;j<columns; j++)
			{
				if (j > 0) System.out.print("\t");
				System.out.print(tiles[i][j]);
			}
			System.out.println();
		}
	}
	
	public void setParent(Board parentBoard)
	{
		parent = parentBoard;
	}
	
	public Board getParent()
	{
		return parent;
	}
	
	//Given a board, a tile position, and direction, this method will swap two tiles. 
	//WARNING:This method does not do any bounds checking of check if the swap is a leagl move. 
	//Checking must be done before calling this method. 
	//Swap direction -
	//r - right , l - left, u - up, d - down
	private void swapTiles(Board board, int row, int col, char direction)
	{
		switch(direction)
		{
			case r:
				
				break;
			case l:

				break;
			case u:
			
				break;
			case d:

				break;
		{
	}

	//This method will go through this.tiles and find where the blank is. It will set the
	//local private varabiles to refelect this postion. 
	private void findBlankPos()
	{
		rowsLoop:
		for (int i = 0 ;i<rows; i++)
		{
			for (int j = 0; j<columns; j++)
			{
				if(tiles[i][j] == 0)
				{
					blank_row = i;
					blank_col = j;
					break rowsLoop;		 
				}
			}
		}
	}
	
}
