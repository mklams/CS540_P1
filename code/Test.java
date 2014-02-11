
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Test {

    static int n = 5, m = 3;
    static String inputfile;
    static Board initialBoard;
    static Board goalBoard;
    static String heuristic;

    public static void Initialize(String filename) {
        String inputLine = null;
        File inputFile = new File(filename);
        try {
            BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
            try {
                //read intial state from file
                int[][] maps = new int[n][m];
                for (int i = 0; i < n; i++) {
                    inputLine = inputReader.readLine();
                    StringTokenizer stk = new StringTokenizer(inputLine, "\t");
                    int j = 0;
                    while (stk.hasMoreElements()) {
                        maps[i][j++] = Integer.parseInt(stk.nextToken());   //Populate initial state
                        if (j == m) {
                            break;
                        }
                    }
                }
                initialBoard = new Board(maps);
                inputReader.readLine();    //Reads empty line between Initial state and Goal State
                //read goal state from file
                for (int i = 0; i < n; i++) {
                    inputLine = inputReader.readLine();
                    StringTokenizer stk = new StringTokenizer(inputLine, "\t");
                    int j = 0;
                    while (stk.hasMoreElements()) {
                        maps[i][j++] = Integer.parseInt(stk.nextToken());   //Populate Goal state
                        if (j == m) {
                            break;
                        }
                    }
                }
                goalBoard = new Board(maps);


            } finally {
                inputReader.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage : Token heuristic inputFile");
            System.exit(-1);
        }
	inputfile = args[0];
        Initialize(inputfile);
	//initialBoard.print();	
	PriorityQueue<Board> frontier = new PriorityQueue<Board>(100, new Comparator<Board>(){
		public int compare(Board board1, Board board2){
			return board1.cost > board2.cost ? 1 : -1;
		}
	});	
	ArrayList<Board> succ = initialBoard.getSuccessors();
	succ.add(initialBoard);
	int cost = 3;
	for (Board board : succ){
		//System.out.println();
		board.cost = cost;
		cost--;	
		//board.print();
		frontier.add(board);
	}
	while(frontier.peek() != null){
		System.out.println();
		Board next = frontier.poll();
		next.print();
		System.out.println("The cost was: "+next.cost);
	}
    }
}
