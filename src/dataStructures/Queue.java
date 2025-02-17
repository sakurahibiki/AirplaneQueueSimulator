package dataStructures;

public class Queue<Type> {
    
    public class Node{
        protected Type data;
        protected Node next;
    
            public Node (Type data){
                this.data = data;
                this.next = null;
            
            }
    }
    
    private Node head;
    private Node tail;
    private int counter;
    
    public Queue(){
        head = tail = null;
        counter = 0;
    }
    
    public void enqueue(Type data){
        Node temp = new Node(data);
        if (tail != null) {
            tail.next = temp;
        }
        else{
            head = temp;
        }
        tail = temp;
        counter++;
    }
    
    public Type dequeue() {
        Node temp = head;
        
        head = head.next;
        
        if (head == null){
            tail = null;
        }
        
        counter--;
        return temp.data;
    }
    
    public boolean isEmpty(){return (head == null);}

    public int size() {return counter;}
}
    