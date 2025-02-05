public class LinkedList<V> implements List<V>{
    class Node<V>{
        private V value;
        private Node<V> Next;

        public Node(V value){
            this.value = value;
        }   

        public V getValue() {
            return value;
        }
        public void setValue(V value) {
            this.value = value;
        }
        public Node<V> getNext() {
            return Next;
        }
        public void setNext(Node<V> nodeNext) {
            this.Next = nodeNext;
        }

        

        

    }

    Node<V> head = null;
    Node<V> tail = null;
    int size = 0;

    public void add(V item){
        Node<V> last = tail;
        Node<V> newNode = new Node<>(item);
        tail = newNode;

        if(last == null){
            this.head = newNode;
            
        }else{
            last.setNext(newNode);
        }
        size++;
    }


    public Node<V> search(int idx){
        Node<V> current = this.head;
        for(int i=0; i <idx; i++){
            current = current.getNext();
        }
        return current;
    }

    public void remove(int idx){
        Node<V> prev = search(idx-1);
        Node<V> next = prev.getNext().getNext();

        prev.setNext(next);
        size--;

    }
    public int size(){
        return this.size;

    }
    public boolean isEmpty(){
        return this.head == null ? true : false;

    }
    public V get(int idx){
        return search(idx).getValue();
        
    }
}
