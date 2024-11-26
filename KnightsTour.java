package algs41;
import java.util.*;
import stdlib.*;
import algs41.Graph;
import algs13.Bag;


class KnightTour {
    static Graph chess;

    public KnightTour(int n){
        chess = new Graph(n*n);
        System.out.println("KnightTour Graph initialized with " + (n * n) + " vertices.");

    }

    void buildGraph(int n){
        chess = new Graph(n*n);
        System.out.println("Building graph for " + n + "x" + n + " chessboard:");

        for (int yAxis = 0; yAxis<n; yAxis++ ){
            for (int xAxis = 0; xAxis<n; xAxis++){
                System.out.println("Processing square: (" + xAxis + ", " + yAxis + ")");
                int u = yAxis * n + xAxis; // get current vertex
                int [][] validMoves = new int[][]{{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}}; // 8 possible moves according to d2l discussion post
                for (int[] move : validMoves){ // loop thorugh valid moves
                    int newX = yAxis + move[0]; // new x coordinate
                    int newY = xAxis + move[1]; // new y coordinate
                    if (newX >= 0 && newX < n && newY >= 0 && newY < n){ // if new x and y axis are in board
                        int v = newX * n + newY; // new vertex
    
                        System.out.println("Adding edge between " + u + " and " + v); 
                        chess.addEdge(u, v); // add the edge between current and new
                    }
                }
                

            

            }
        }
    }

    // builds the graph of the second page. N is the size of the chess board }

        public static void addEdge(int u, int v){
        Bag <Integer>[] adj; 
        for (int i = 0; i < chess.V(); i++){
            for (int j = 0; j < chess.V(); j++){
                
            }
           // System.out.println("Adding edge between " + u + " and " + v);
        }
    }

    List<Integer> knightTour (int n, List<Integer> path, int u, int limit, boolean[] visited){
        if (n == limit){
            return new ArrayList<>(path);
        }
        List<Integer> sort = getSortedMoves(u, visited);
        for (int v : sort){
            path.add(v);
            visited[v] = true;
            List<Integer> result = knightTour(n+1, path, v, limit);
            if (result != null){
                return result;
            }
            path.remove(path.size() - 1);
            visited[v] = false;
        }

    }

    private List<Integer> getSortedMoves(int u, boolean[] visited){
        List<Integer> moves = new ArrayList<>();
        for (int v : chess.adj(u)){
            if (!visited[v]){
                moves.add(v);
            }
        }
        moves.sort(Comparator.comparingInt(v -> countOnwardMoves(v, visited)));
        return moves;
    }
    private int countOnwardMoves(int u, boolean[] visited){
        int count = 0;
        for (int v : chess.adj(u)){
            if (!visited[v]){
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        int boardSize = 5; // Change this to whatever size you want to test
        KnightTour output = new KnightTour(boardSize);
        output.buildGraph(boardSize);



        
        // Start the knight tour from vertex 0 with an empty path list and depth 0
        List<Integer> path = new ArrayList<>();
        path.add(0); // Start from the first square
        int limit = boardSize * boardSize; // Total number of squares to visit

        int u = 1;
        int v = 3;
        KnightTour.addEdge(u, v);
        System.out.println("Adding edge between " + u + " and " + u);
        

    
        List<Integer> tour = output.knightTour(1, path, 0, limit);
        if (tour != null) {
            System.out.println("Found a knight's tour path: " + tour);
        } else {
            System.out.println("No knight's tour found.");
        }
    }
    

    // n, the current depth in the search tree
    // path, a list of vertices visited up to this point;
    // u the vertex in the graph we wish to explore
    // limit = number of squares in the chess board.
   }
