// Exercise 4.1.3 (Solution published at http://algs4.cs.princeton.edu/)
package algs41;
import stdlib.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import algs13.Bag;

/**
 *  The {@code Graph} class represents an undirected graph of vertices
 *  named {@code 0} through {@code V-1}.
 *  It supports the following operations: add an edge to the graph,
 *  iterate over all of the neighbors adjacent to a vertex.
 *  Parallel edges and self-loops are permitted.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/51undirected">Section 5.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class Graph {
	private final int V; // number of vertices
	private int E; // number of edges
	private final Bag<Integer>[] adj; // adjacency lists
	private int boardSize;

	// private final String[] names;

	// size of adj is V

	/**
	 * Create an empty graph with V vertices.
	 */
	@SuppressWarnings("unchecked")
	public Graph(int V) {
		if (V < 0) throw new Error("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		this.adj = new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<>();
		}
	}
	public Graph( int n, boolean isKnightTour){
		this( n * n);
		this.boardSize = n;
		buildGraph(n); 
	}

	// void explore(int v){
	//	for all w in adj[v]
	//         explore(w)
	
	
	

	/**
	 * Return the number of vertices in the graph.
	 */
	public int V() { return V; }

	/**
	 * Return the number of edges in the graph.
	 */
	public int E() { return E; }


	/**
	 * Add the undirected edge v-w to graph.
	 * @throws java.lang.IndexOutOfBoundsException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
	 */
	public void addEdge(int v, int w) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		if (w < 0 || w >= V) throw new IndexOutOfBoundsException();
		E++;
		adj[v].add(w); 
		adj[w].add(v);
	}


	/**
	 * Return the list of neighbors of vertex v as in Iterable.
	 * @throws java.lang.IndexOutOfBoundsException unless {@code 0 <= v < V}
	 */
	public Iterable<Integer> adj(int v) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		return adj[v];
	}
	

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(int v) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		return adj[v].size();
    }

	/**
	 * Return a string representation of the graph.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		String NEWLINE = System.getProperty("line.separator");
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj[v]) {
				s.append(w + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

	/**
	 * Save a graphviz representation of the graph.
	 * See <a href="http://www.graphviz.org/">graphviz.org</a>.
	 */
	public void toGraphviz(String filename) {
		GraphvizBuilder gb = new GraphvizBuilder ();
		for (int v = 0; v < V; v++) {
			gb.addNode (v);
			boolean showSelfLoop = false;
			for (int w : adj[v]) {
				if (v < w) // only once each edge
					gb.addEdge (v, w);
				if (v == w) {
					showSelfLoop = !showSelfLoop;
					if (showSelfLoop)
						gb.addEdge (v, w);
				}
			}
		}
		gb.toFileUndirected (filename);
	}
	private void buildGraph(int n){

        System.out.println(n + "x" + n + " chessboard:");
		int [][] validMoves = new int[][]{{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}}; // 8 possible moves according to d2l discussion post
        for (int yAxis = 0; yAxis<n; yAxis++ ){ // loop through y axis
            for (int xAxis = 0; xAxis<n; xAxis++){ // loop through x axis
               //Debugging: System.out.println("Processing square: (" + xAxis + ", " + yAxis + ")");
                int u = yAxis * n + xAxis; // get current vertex
                for (int[] move : validMoves){ // loop thorugh valid moves
                    int newX = yAxis + move[0]; // new x coordinate
                    int newY = xAxis + move[1]; // new y coordinate
                    if (newX >= 0 && newX < n && newY >= 0 && newY < n){ // if new x and y axis are in board
                        int v = newX * n + newY; // new vertex
    
                     //Debugging:  System.out.println("Adding edge between " + u + " and " + v); 
                        addEdge(u, v); // add the edge between current and new
                    }
                }
                

            

            }
        }
	}

	public List<Integer> knightTour (int n, List<Integer> path, int u, int limit, boolean[] marked){
        if (n == limit){ // if it reaches limit, return path
            return new ArrayList<>(path);
        }
        List<Integer> sort = sorted(u, marked); // a sorted list of moves
        for (int v : sort){ // looping through the sorred list
            path.add(v); // add vertext to path
            marked[v] = true; // mark it as visited
            List<Integer> result = knightTour(n+1, path, v, limit, marked); // recursive call to keep going
            if (result != null){ // if result is not null, return result
                return result;
            }
            path.remove(path.size() - 1); // remove last element
            marked[v] = false; // mark as not visited
        }
		return null;
    }

	private List<Integer> sorted(int u, boolean[] visited){
        List<Integer> moves = new ArrayList<>(); // list of the moves
        for (int v : adj(u)){  // loop through the adjacent vertices through adj method
            if (!visited[v]){ // if it hasn't been visited
                moves.add(v); // add it to list
            }
        }
        moves.sort(Comparator.comparingInt(v -> forwardMoves(v, visited))); // Using the comparator and lambda expression to sort the moves
        return moves; // After sorting, we return moves
    }
    private int forwardMoves(int u, boolean[] visited){
        int count = 0; // create count
        for (int v : adj(u)){ // loop through adjacent vertices
            if (!visited[v]){ // if it hasn't been visited
                count++; // increment count
            }
        }
        return count; // return count
    }

	/**
	 * Test client.
	 */
	public static void main(String[] args) {
		//args = new String [] { "data/tinyCG.txt" };
		//args = new String [] { "ds2/data/tinyG.txt" };  // Changed the file path to the correct one
		//args = new String [] { "20", "40" };

		//Graph G;
		//if (args.length == 1) {
		//	In in = new In(args[0]);
		//	G = GraphGenerator.fromIn (in);
		//} else {
		//	int V = Integer.parseInt (args[0]);
		//	int E = Integer.parseInt (args[1]);
		//	G = GraphGenerator.simple(V, E);
		//}
		//StdOut.println(G);
		//G.toGraphviz ("g.png");

		//^^^ Do not know whether or not to leave the code above or not ^^^
		//Code below is for assignment
		

		int boardSize = 5; // Don't know whether or not board size needs to be user inputted or hardcoded

        Graph knightTourOutput = new Graph(boardSize, true); // graph made for tour
        List<Integer> path = new ArrayList<>(); // A list of the vertices for the knight path
		int limit = boardSize * boardSize; // limit isthe board size
        boolean[] visited = new boolean[boardSize * boardSize]; // boolean size of board to keep track of visited vertices


        path.add(0); // start at 0
        visited[0] = true; // 0 has been visited
		
        List<Integer> finalTour = knightTourOutput.knightTour(1, path,0, limit, visited); // List of final tour path of knight
       
		if (finalTour != null) { // if there is a path of knight's tour
            System.out.println("Knight's tour path: " +finalTour); // DONE if this prints
        } else { // if not
            System.out.println("No knight's tour found."); // Not done if this prints
        }
	}
}
