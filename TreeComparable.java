package algs11;



public class TreeComparable<T extends Comparable<T>> {
    
    
    static class Node<T extends Comparable<T>> {
        Node<T> left, right;
        T value;
    public Node(T value){
        this.value = value;
    }
    public Node(T value, Node<T> left, Node<T> right){
        this.value = value;
        this.left = left;
        this.right = right;
    }

    }
    
    
    
    Node<T> root;

    public void insert(T v){
        root = insert(root, v); //calls private to traverse and insert value
    }
    private Node<T> insert(Node<T> node, T v){ 

        if (node == null){ 
            return new Node<T>(v); // base case so if node is null,  we create new node here
        }

         if (v.compareTo(node.value) < 0){  //if new node is less than current node, we go left
             node.left = insert(node.left, v); //recursively call insert to keep going left until null
            }
            

        else if (v.compareTo(node.value) > 0){ // if its bigger, we go right
            node.right = insert(node.right, v); // recursively call insert to keep going right until null
            }
        

        return node; // return node
        }
    public boolean lookup(T v){
        if (root == null){
            return false;
        }
        return lookup(root, v);

    }

    private boolean lookup(Node<T> node, T v){
        if (node == null) return false;

        if (v.compareTo(node.value) == 0) return true;

        else if (v.compareTo(node.value) < 0) {
            return lookup(node.left, v);
        }
        else {
            return lookup(node.right, v);}
    }

    public T min(){
        if(root == null){ // if tree is empty return ull
            return null;
        }
        return min(root);
    }

    private static <T extends Comparable<T>> T min(Node<T> node){
        while (node.left != null){  //while left is not null, go left
            node = node.left;} // change node to left until null
        return node.value; // return min value

    }



    public T max(){
        if (root == null){ // if tree is empty return null
            return null;
        }
        return max(root);
    }
    private static <T extends Comparable<T>> T max(Node<T> node){
        while (node.right != null) {//while right is not null, go right
            node = node.right;}// change node to right until null
        return node.value; // return max value
    }

    public static boolean isBST1(TreeComparable<Integer> node){
        if (node.root == null){ // if tree is empty then true
            return true;}
            
        
        return isBST3(node.root, null, null);} 
    
    private static boolean isBST3(Node<Integer> node, Integer min, Integer max){
        if (node == null){  // if nod e is null return true
            return true;}

        if ((min != null && node.value <= min) || (max != null && node.value >= max)){ // if min is not null and node is less than min or max is not null and node value is greater than max thern return false
            return false;}

        return isBST3(node.left, min, node.value) && isBST3(node.right, node.value, max); // recursive call to go left and right
    }

    public static boolean  isBST2(TreeComparable<Integer> node){
        if (node.root == null){
            return true;
        }
        return isBST4(node.root, null, null);
    }
    private static boolean isBST4(Node<Integer> node, Integer min, Integer max){
        if (node == null){ // if node is null return true
            return true;
        }
        if (min != null && node.value <= min || max != null && node.value >= max){ // if min is not null and node is less than min or max is not null and node value is greater than max thern return false
            return false;
        }
        return isBST4(node.left, min, node.value) && isBST4(node.right, node.value, max); // recursive call to go left and right
    }
    
}
