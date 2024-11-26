package algs41;
import java.util.*;

import algs43.Edge;
import stdlib.*;
import java.io.*;


public class MarvelPaths {
    private final Graph2 graph; // make graph
    private final Map<String, Integer> in; // make map for index of string and integer
    private final Map<Integer, String> name1; // make map for name of integer and string
    private int vCount; // make integer for vertex count
    private int eCount; // make integer for edge count
    //

    public MarvelPaths(int maxVertices){
        this.graph = new Graph2(maxVertices); // make graph with len of maxVertices
        this.in = new HashMap<>(); // make map for index of string and integer
        this.name1 = new HashMap<>(); // make map for name of integer and string
        this.vCount = 0; // set vertex count to 0
        this.eCount = 0; // set edge count to 0
    }

    private int index(String name){
        if (!in.containsKey(name)){ // if name is not in in map
            int index = vCount++; // increment vertex count
            in.put(name, index); // put name and index in in map
            name1.put(index, name); // put index and name in name1 map
            graph.addVertextName(index, name);  // add vertex name to graph 
        }
        return in.get(name); // return name
    }

    public void hero(String x, String y) throws IOException{
        Map<String, List<String>> char1 = new HashMap<>(); // make map of string and list of string

        try(BufferedReader reader = new BufferedReader(new FileReader(y))){ // read file
            String line; // make string called line 
            while ((line = reader.readLine()) != null){ // while not null


                String[] tokens = line.split(","); // split lines by comma
                String character = tokens[0].trim(); // get the marvel character
                String comic = tokens[1].trim(); // get marvel comic

                int charIndex = index(character);
                char1.putIfAbsent(comic, new ArrayList<>()); // comic goes to char1 map
                char1.get(comic).add(character); // add character to comic
                
            }
        }

        for (Map.Entry<String, List<String>> begin : char1.entrySet()){ // for each entry in char1
            String comic = begin.getKey(); // get key as comic
            List<String> characters = begin.getValue();  // get value as characters
            for (int i = 0; i < characters.size(); i++){ // for each character in characters
                for (int j = i + 1; j < characters.size(); j++){  // for each character in characters
                    int a = in.get(characters.get(i)); // get index of character
                    int b = in.get(characters.get(j)); // get index of character
                    graph.addEdge(a,b,comic); // add edge between a and b with comic bc we need 2 edges per connection
                    graph.addEdge(b,a,comic); // add edge between b and a with comic bc we need 2 edges per connection
                    eCount+= 2; // increment edge count
                  // System.out.println("Adding edge between: " + characters.get(i) + " and " + characters.get(j) + " comic: " + comic);

                }
            }
        }
    }
    public void bipartite(String x, String y) throws IOException{
        try(BufferedReader reader = new BufferedReader(new FileReader(y))){ // read file
            String line; // make string called line 
            while ((line = reader.readLine()) != null){ // while not null
                String [] tokens = line.split(","); // split lines by comma
                String character = tokens[0].trim(); // get marvel character
                String comic = tokens[1].trim(); // get marvel comic

                int chIndex = index(character);
                int coIndex = index(comic);

                graph.addEdge(chIndex, coIndex, " "); // add edge between character and comic
                eCount++; // increment edge count
            }}
            }


    public List<String> pathS(String start, String dest){
        if (!in.containsKey(start) || !in.containsKey(dest)){ // if start or dest is not in in map
            return null; // return null
        }

            int sIndex = in.get(start); // get index of start as sIndex
            int dIndex = in.get(dest); // get index of dest as dIndex

            Map<Integer, List<Integer>> paths = new HashMap<>(); // make map of integer and list of integer and call it paths

            Queue<Integer> queue = new LinkedList<>(); // make queue

            queue.add(sIndex); // add sIndex to queue
            paths.put(sIndex, new ArrayList<>()); // put sIndex and new arraylist in paths

            while(!queue.isEmpty()){ // while queue is not empty
                int curr = queue.poll(); // poll the first element in queue and set it to curr
                List<Integer> path = paths.get(curr); // get the path of curr

                if (curr == dIndex){ // if curr is equal to dIndex
                    path.add(curr); // add curr to path
                    List<String> pathN = new ArrayList<>(); // make new arraylist called pathN
                    for (int i = 0; i < path.size() - 1; i++){ // for each element in path
                        int a = path.get(i); // get element at i
                        int b = path.get(i + 1); // get element at i + 1
                        pathN.add(name1.get(a) + " Comic: " + graph.getEdgeName(a, b)+ " " + " -> " + " " + name1.get(b));  // adding the name of a, the comic, and the name of b to pathN to be printed         
                    }
                    
                return pathN; // return pathN
                }
                for (int neighbor : graph.getAdj(curr)){ // for each neighbor in graph 
                    if (!paths.containsKey(neighbor)){ // if paths does not contain neighbor
                        List<Integer> Path2 = new ArrayList<>(path); // make new arraylist called Path2
                        Path2.add(curr); // add curr to Path2
                        paths.put(neighbor, Path2); // put neighbor and Path2 in paths
                        queue.add(neighbor); // add neighbor to queue
                    }
                }
                
            }
    
        return null; 
    }

    public static void main(String[] args) throws IOException{
        MarvelPaths marvelPaths = new MarvelPaths(10000); // make a new marvelPaths with 10000 vertices max
       

        //Hero Grqph
        long startTime = System.currentTimeMillis(); // start time
        marvelPaths.hero("ds2/data/LISTOFheroesANDcomics.csv", "ds2/data/herosINcomics.csv"); // files to read from
        long endTime = System.currentTimeMillis(); // end time

        System.out.println("Time to build graph Hero Graph: " + (endTime - startTime) + " milliseconds"); // print time to build graph
        System.out.println("Amount of Nodes: " + marvelPaths.vCount); // print amount of vertices
        System.out.println("Amount of Edges: " + marvelPaths.eCount); // print amount of edges

        //Hero Graph
        List<String> endPath = marvelPaths.pathS("3-D MAN/CHARLES CHAN","4-D MAN/MERCURIO"); // Strings used to test according to ChatGPT
        if (endPath != null){ // if endPath is not null
            System.out.println("Path: " + endPath); // print path
            for (String e : endPath){ // for each step in endPath
                System.out.println(e); // print step
            }
        }
        else{
            System.out.println("No path found");
        }

        MarvelPaths marvelPaths2 = new MarvelPaths(20000); // Dont know how to fix this error, file size too big?? Leads to heap space error

        //Bipartie Graph
        long startTime2 = System.currentTimeMillis(); // start time
        marvelPaths2.bipartite("ds2/data/LISTOFheroesANDcomics.csv", "ds2/data/herosINcomics.csv"); // files to read from
        long endTime2 = System.currentTimeMillis(); // end time

        System.out.println("Time to build graph Bipartite Graph: " + (endTime2 - startTime2) + " milliseconds"); // print time to build graph
        System.out.println("Amount of Nodes: " + marvelPaths2.vCount); // print amount of vertices
        System.out.println("Amount of Edges: " + marvelPaths2.eCount); // print amount of edges

    

         //Bipartite Graph
         List<String> bipartitePath = marvelPaths2.pathS("3-D MAN/CHARLES CHAN","4-D MAN/MERCURIO");
         if (bipartitePath != null){ // if bipartitePath is not null
             System.out.println("Path: " + bipartitePath); // print path
             for (String e : bipartitePath){ // for each step in bipartitePath
                 System.out.println(e); // print step
             }
         }
         else{
             System.out.println("No path found");
         }


}}
