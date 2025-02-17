package dataStructures;


public class Stack<xType> {
    public class Node{
        public xType data;
        public Node next;
        
        public Node(xType data){
            this.data = data;
            this.next = null;
        }
    }
    
    private Node top;
    private int counter;
    
    public Stack(){
        top = null;
        counter = 0;
    }
    
    public void push (xType data){
        Node temp = new Node(data);
        temp.next = top;
        top = temp;
        counter ++;
    }
    
    public xType pop() {
        counter--;
        xType data = top.data;
        top = top.next;
        return data;
    }
    
    public xType peek() {
        xType data = top.data;
        return data;
    }
    
    public boolean isEmpty(){return (top == null);}

    public int size() {return counter;}

}

