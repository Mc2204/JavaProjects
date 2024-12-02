A collection of various projects from my Data Structures II Course with a quick description below of them all.

Graph.java:
The construction of a chess board that builds, tracks, and returns the amount of edges and vertices of that resembles the path of a knight piece in chess.

KnightsTour.java:
Using Graph.java, we utilize it to find a solution to output a path of a knight through a chessboard of n^2 size through the addition of edges and vertices while keeping track of visited squares.

MarvelPaths.java:
Builds a graph and returns a path of 2 Marvel characters based on their appearances in comics.

PriceQueue.java:
Using data structures like a queue, the PriceQueue class stores price objects with a linked list and tree map. This makes managing nodes and looking them up much more efficient.
Price objects can be enqueued and dequeued at logarithmic time.

TreeComparable.java:
Implements a BST to check if the given tree is a valid BST. Using insertion, looking up max and min values that follow those rules of a valid BST.

WordFrequencyAnalyzer.java:
Analyzes the frequency of words in a text file after converting all the words to lowercase and reading it line by line. It uses a symbol table that stores the words as a key and its occurences as values. 

WordGraph.java:
This program constructs a graph of words where edges connect words that are different by only one character and it finds the shortest path between the two using BFS. Words are grouped into buckets based on templates, which are words with one character being replaced with "_". The program outputs path of the words and the sequence if a connection exists, or indicates that the words are not connected.

