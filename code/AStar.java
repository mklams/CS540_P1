import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Comparator;
public class AStar {
	 
	private Board initialState;
	private Board goalState;
	private AStarHeuristic heuristic;

	public AStar(Board initial, Board goal, AStarHeuristic heur)
	{
		initialState = initial;
		goalState = goal;
		heuristic = heur;
	}

	public void search()
	{
      	/* Declare and initialize Frontier and Explored data structures */ 
		ArrayList<Board> explored = new ArrayList<Board>();
		PriorityQueue<Board> frontier = new PriorityQueue<Board>(100, new Comparator<Board>() {
			public int compare(Board board1, Board board2) {
				return board1.cost > board2.cost ? 1 : -1;
			}
		});
		/* Put start node in Fringe list Frontier */
		initialState.setCost(0, heuristic.getCost(initialState, goalState));
		frontier.add(initialState);
		boolean goalFound = false;
		while (!frontier.isEmpty() && !goalFound)
		{
			/* Remove from Frontier list the node n for which f(n) is minimum */
			/* Add n to Explored list*/
			Board n = frontier.poll();
			explored.add(n); 
			//First check if current state is goal state
			if (n.equals(goalState))
			{
				/* Print the solution path and other required information */
				/* Trace the solution path from goal state to initial state using getParent() function*/
				ArrayList<Board> solutionPath = new ArrayList<Board>();
				solutionPath.add(n);
				Board parent = n.getParent();
				int moveCount = 0;
				while(parent != null)
				{
					solutionPath.add(0, parent);
					parent = parent.getParent();
					moveCount++;
				}
				while(solutionPath.size() != 0)
				{
					Board currBoard = solutionPath.remove(0);
					currBoard.print();
					System.out.println();
				}
				System.out.println(moveCount);
				System.out.println(explored.size());
				goalFound = true;
				break;
			}
			//otherwise get successors
			ArrayList<Board> successors = n.getSuccessors();
			for (int i = 0 ;i<successors.size(); i++)
			{
				Board n1 = successors.get(i);
				n1.setParent(n);
				//Need to iterate through both frontier and explored to see if this board is in
				//either of them
				boolean inFrontier = false;
				Board frontierBoard = null;
				boolean inExplored = false;
				Board exploredBoard = null;
				//First check if board is in frontier
				for(Board curr : frontier)
				{
					if(curr.equals(n1))
					{
						inFrontier = true;
						frontierBoard = curr;
						break;
					}
				}
				
				//Next check if board is in explored
				for(Board curr : explored)
				{
					if(curr.equals(n1))
					{
						inExplored = true;
						exploredBoard = curr;
						break;
					}
				}
				/* if n1 is not already in either Frontier or Explored
				      Compute h(n1), g(n1) = g(n)+c(n, n1), f(n1)=g(n1)+h(n1), place n1 in Frontier
				   if n1 is already in either Frontier or Explored
				      if g(n1) is lower for the newly generated n1
				          Replace existing n1 with newly generated g(n1), h(n1), set parent of n1 to n
				          if n1 is in Explored list
				              Move n1 from Explored to Frontier list*/

				//First check if n1 hasn't been seen before
				n1.setCost(n.g + 1, heuristic.getCost(n1, goalState));

				if(!inFrontier && !inExplored)
				{
					frontier.add(n1);
				}
				//else see if n1 has a better cost then what if in frontier or explored
				else 
				{
					if(inFrontier && n1.g < frontierBoard.g)
					{
						frontier.remove(frontierBoard);
						frontier.add(n1);
					}
					else if(inExplored && n1.g < exploredBoard.g)
					{						
						explored.remove(exploredBoard);
						frontier.add(n1);
					}
				}
			}
		}
		if(!goalFound)
			System.out.println("No Solution");
	}

}
