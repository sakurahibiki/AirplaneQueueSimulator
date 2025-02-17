package dataStructures;

public class List<Type extends Comparable>{
    
    public class Node {
        public Type data;
        public Node next;
        public Node prev;
    
        public Node(Type data) {
            this.data = data;
            this.next = null;
            this.next = null;
        }
    }
    
    private Node first;
    private Node last;
    private Node iterator;
    private int counter;
    
    public List(){
        first = last = iterator = null;
        counter = 0;
        }
    
    public void insert(Type data){
        Node left = null;
        Node right = first;
        
        while(right != null) {
            if (data.compareTo(right.data) < 0)
                break;
            
            left = right;
            right = right.next;
        }
        
        Node temp = new Node (data);
        temp.prev = left;
        temp.next = right;
        
        if (left != null) left.next = temp;
        else first = temp;
        
        if (right != null) right.prev = temp;
        else last = temp;
        counter++;
    }
    
    
    
    
    
/**    public void append (Type data) {
        Node temp = new Node(data);
        temp.prev = last;

        if (last != null){
            last.next = temp;
        }
        else {
            first = temp;
        }
        
        last = temp;
    }
    
    public void prepend (Type data) {
        Node temp = new Node(data);
        temp.next = first;

        if (first != null){
            first.prev = temp;
        }
        else {
            last = temp;
        }
        
        first = temp;
    }
**/    
    
    
    
    
    public void moveToFirst(){iterator = first; }
    public void moveTolast(){iterator = last; }
    public void moveToNext(){iterator = iterator.next;}
    public void moveToPrev(){iterator = iterator.prev;}
    
    public Type getData(){return iterator.data;}
    public boolean hasData() {return (iterator != null);}
    
    public boolean isEmpty() {return (first == null);}
    public int size() {return counter;}
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for(moveToFirst(); hasData(); moveToNext()){
            sb.append(getData().toString());
        }
        return sb.toString();
    }
    
}



    


