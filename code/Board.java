import java.util.ArrayList;
public class Board {
	public static int rows=5;
	public static int columns=3;
	public int cost;
	public int g;
	public int h;
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

	//Use cyclic ordering for expanding nodes - Right, Down, Left, Up
	public ArrayList<Board> getSuccessors()
	{
		ArrayList<Board> successors = new ArrayList<Board>();
		this.findBlankPos();	
		
	//first try and move the blank to the right. If it is a legal move, add it to successors
		if(blank_col != columns-1 && tiles[blank_row][blank_col+1] != -1)
		{
			Board rightBoard = new Board(tiles);
			swapTiles(rightBoard, blank_row, blank_col, 'r');	
			successors.add(rightBoard);
		}
		//Next try and move the blank to the down 
		if(blank_row != rows-1 && tiles[blank_row+1][blank_col] != -1)
		{
			Board downBoard = new Board(tiles);
			swapTiles(downBoard, blank_row, blank_col, 'd');	
			successors.add(downBoard);
		}
		//Try to move the blank left
		if(blank_col != 0 && tiles[blank_row][blank_col-1] != -1)
		{
			Board leftBoard = new Board(tiles);
			swapTiles(leftBoard, blank_row, blank_col, 'l');	
			successors.add(leftBoard);
		}
		//Finally try and move the blank Up
		if(blank_row != 0 && tiles[blank_row-1][blank_col] != -1)
		{
			Board upBoard = new Board(tiles);
			swapTiles(upBoard, blank_row, blank_col, 'u');	
			successors.add(upBoard);
		}
		return successors;
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

	public int hashCode()
	{
		return cost;
	}

	public void setCost(int g_n, int h_n)
	{
		g = g_n;
		h = h_n;
		cost = g+h;
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
	//Returns true if swap happend. False if trying to swap with -1.
	//WARNING:This method does not do any bounds checking for out of bounds error 
	//Checking must be done before calling this method. 
	//Swap direction: r - right , l - left, u - up, d - down
	private boolean swapTiles(Board board, int row, int col, char direction)
	{
		int tempTile;
		
		switch(direction)
		{
			case 'r':
				//if(tiles[row][col+1] == -1)
				//	return false;
				tempTile = board.tiles[row][col];
				board.tiles[row][col] = board.tiles[row][col+1];
				board.tiles[row][col+1] = tempTile; 	
				break;
			case 'l':
				//if(tiles[row][col-1] == -1)
				//	return false;
				tempTile = board.tiles[row][col];
				board.tiles[row][col] = board.tiles[row][col-1];
				board.tiles[row][col-1] = tempTile; 	
				break;
			case 'u':
				//if(tiles[row-1][col] == -1)
				//	return false;
				tempTile = board.tiles[row][col];
				board.tiles[row][col] = board.tiles[row-1][col];
				board.tiles[row-1][col] = tempTile; 	
				break;
			case 'd':
				//if(tiles[row+1][col] == -1)
				//	return false;
				tempTile = board.tiles[row][col];
				board.tiles[row][col] = board.tiles[row+1][col];
				board.tiles[row+1][col] = tempTile; 	
				break;
		}
		return true;
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
