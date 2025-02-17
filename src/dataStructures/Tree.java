/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

/**
 *
 * @author Adjatay
 */
public class Tree<Key extends Comparable, Data> {
    class Node {
        public Key key;
        public Data data;
        public Node left;
        public Node right;
       
        public Node(Key key, Data data) {
            this.key = key;
            this.data = data;
            this.left = null;
            this.right = null;
        }
        
    }
    private Node root;
    public Tree() {
        root = null;
        
    }
    public void insert(Key key, Data data) {
        root = insert(key, data, root);
    }
    private Node insert(Key k, Data d, Node r) {
        if ( r == null) {
            r = new Node ( k, d);
        }
        else { 
            int ct = k.compareTo(r.key);
            
            if (ct == 0) r.data = d;
            if (ct < 0) r.left = insert(k, d, r.left);
            if (ct > 0) r.right = insert (k,d, r.right);
        }
        return r;
    }
    public boolean contains(Key key) {
        return(retrieve(key) != null);
    }
    public Key smallest() {
        return smallest(root);
    }
    private Key smallest(Node r){
        Key k = null;
        if (r!=null) {
            if (r.left == null) k = r.key;
            else k = smallest(r.left);
        }
        return k;
    }
    public Key largest() {
        return largest(root);
        
    }
    private Key largest (Node r) {
        Key k = null;
        if (r!=null) {
        if (r.right == null) k = r.key;
        else k = largest(r.right);
    }
        return k;
    }
    public Data retrieve(Key key) {
        return retrieve(key, root);
    }
   private Data retrieve(Key k, Node r) {
       Data d = null;
       
       if (r != null) {
           int ct = k.compareTo(r.key);
           
           if (ct == 0) d = r.data;
           if (ct < 0) d = retrieve(k, r.left);
           if (ct > 0) d = retrieve (k, r.right);
           
       }
       return d;
   }
    
    public boolean isEmpty() {return root == null;}
    
    public static void main(String [] args) {
        Tree<Integer, String> books = new Tree<>();
        
        books.insert(50, "CompSci II w/Java");
         books.insert(90, "CompSci II w/C++");
          books.insert(80, "CompSci II w/C#");
           books.insert(60, "CompSci II w/Python");
            books.insert(40, "CompSci II w/Delphi");
             books.insert(20, "CompSci II w/Fortron");
              books.insert(45, "CompSci II w/Angular");
              
              System.out.println(books.retrieve(80));
              System.out.println(books.retrieve(81));
              
              System.out.println(books.contains(60));
              System.out.println(books.contains(75));
              
              System.out.println(books.smallest());
              System.out.println(books.largest());
              
    }
    
}
