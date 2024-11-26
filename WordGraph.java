package algs41;

import java.util.HashSet;
import java.util.Queue;

import stdlib.StdIn;
import stdlib.StdOut;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class WordGraph {
    String [] labels; // make string array
    private Map<String, List<String>> adjList; // make map of string and list of string
    private Map<String, List<String>> bucket; // bucket for grouping words
    

    public WordGraph(HashSet<String> words){
        this.bucket = new HashMap<>(); // bucket map
        this.adjList = new HashMap<>(); // map of string and list of string

        long start = System.currentTimeMillis(); // start time
        grouped(words); // group words in buckets
        buildGraph(words); // grpah is built
        long end = System.currentTimeMillis(); // end time

        System.out.println("Graph built in " + (end - start) + " ms"); // print time
        // After bucket creatuion, speed of graph being built increased

        

    }

    private void grouped(HashSet<String> words){
        for (String word : words) { // for each word in words
            List<String> templates = temp(word); // get the templates
            for (String x : templates){ // for each word in templates
                bucket.computeIfAbsent(x, k -> new ArrayList<>()).add(word); // compute the buckets
            }
        }
    }

    private List<String> temp(String word){
        List<String> templates = new ArrayList<>(); // make a new list
        for (int i =0; i < word.length(); i++){ // for each word in word
            String temp = word.substring(0, i) + "_" + word.substring(i+1); // get the substring
            templates.add(temp); // add the template
        }
        return templates;
    }
    private void buildGraph(HashSet<String> words){
        for(String x : words){ // for each word in words
            adjList.put(x, new ArrayList<>()); // the words goes in the list
            List<String> templates = temp(x); // get the templates

            for (String y : templates){ // for each word in words
                List<String> bWords = bucket.get(y); // get the bucket words
                if (bWords != null){ // if bucket words is not null
                    for (String neighbor : bWords){ // for each word in bWords
                        if (!x.equals(neighbor) && isNeighbor(x, neighbor)){ // if x is not equal its neighbor
                            adjList.get(x).add(neighbor); // add neighbor to x
                        }
                    }
                }
            }
        }
    }

    private boolean isNeighbor(String a, String b){
        if (a.length() != b.length()){// if lengths aren't equal
            return false; // return false
        }
        int differ = 0; // differ is 0
        for (int i = 0; i < a.length(); i++){ // for each word in a
            if (a.charAt(i) != b.charAt(i)){ // if theyre not the same
                differ++; // increment differ
                if (differ > 1){ // if differ is greater than 1
                    return false; // return false
                }
            }
        }
        return differ == 1; 
    }


    public String[] path(String first, String last1){
        if (!adjList.containsKey(first) || !adjList.containsKey(last1)){ // if first and last are not in adjList
            return null;
        }
    

    Queue<List<String>> q = new LinkedList<>(); // queue of list of strngs

    Set<String> v = new HashSet<>(); // visited set

    q.add(Collections.singletonList(first)); // add first to the queue

    v.add(first); // add first to visited

    while (!q.isEmpty()){ // while queue is not empty
        List<String> path = q.poll(); // poll the first element
        String last2 = path.get(path.size() - 1); // get the last element
    
    if (last2.equals(last1)){ // if last2 is equal to last1
        int edge = path.size() - 1; // edge is the size of path - 1
        System.out.println("Edges: " + edge); // print the number of edges
        return path.toArray(new String[0]); // return the path
    }

    for (String x : adjList.get(last2)){ // for each word in adjList
        if (!v.contains(x)){ // if visited does not contain x
            List<String> path2 = new ArrayList<>(path); // make a new path2
            path2.add(x); // add x to the path2
            q.add(path2); // add path2 to the queue
            v.add(x); // add x to visited
        }
    }
    }

    return null;
}

public static void main(String[] args){
    args = new String [] {"flirt", "break"}; // test words
    StdIn.fromFile("ds2/data/words5.txt"); // file to read from

    String from = args [0]; // first word
    String to = args[1]; // second word

    HashSet<String> words = new HashSet<>(); // hashset of strings

    while(!StdIn.isEmpty()){ // while input is not empty
        String word = StdIn.readString(); // read
        if (word.length() == from.length()){ // if length is equal to first word
            words.add(word); // add word to words
        }
    }
    System.out.println("Finished reading word list");

    WordGraph graph = new WordGraph(words); // make a new graph

    String[] path = graph.path(from, to); // path from first to second word

    if (path != null){ // if path is not null
        StdOut.println("Number of words = " + (path.length)); // print the number of words
        for (String word : path){ // for each word in path
            StdOut.println(word); // print the word
        }
    } else {
        StdOut.println("Not connected");
    }
    
}}

