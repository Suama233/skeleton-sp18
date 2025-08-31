public class LinkedListDeque<T> {

    /** saving the size of the deque as the value of the sentinel node */
    private Node<T> sentinel;
    int size;

    /** addition and removal should take constant time */
    public void addFirst(T item){
        size += 1;
        sentinel.next = new Node<>(item,sentinel,sentinel.next);
    }
    public void addLast(T item){
        size += 1;
        sentinel.prev = new Node<>(item,sentinel.prev,sentinel);
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    /** should take constant time*/
    public int size(){
        return size;
    }
    public void printDeque(){
        Node<T> tmp = sentinel;
        for(int i = 0;i < size; i ++){
            tmp = tmp.next;
            System.out.print(tmp.item + " ");

        }
    }
    public T removeFirst(){
        size -= 1;
        T item = this.sentinel.next.item;
        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev = this.sentinel;
        return item;
    }
    public T removeLast(){
        size -= 1;
        T item = this.sentinel.prev.item;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next = this.sentinel;
        return item;
    }

    /** should only use iteration */
    public T get(int index){
        Node<T> tmp = this.sentinel;
        for(int i = 0;i <= index;i++){
            tmp = tmp.next;
        }
        return tmp.item;
    }

    /** creat an empty deque */
    public LinkedListDeque(){
        this.sentinel = new Node<>(null,null,null);
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
        size = 0;
    }
    public T getRecursive(int index){
        return getRecursiveNode(index,sentinel.next);
    }
    private T getRecursiveNode(int index,Node<T> node){
        if(index == 0){
            return node.item;
        }else{
            return getRecursiveNode(index-1,node.next);
        }
    }


    private static class Node<T>{
        public T item;
        public Node<T> prev;
        public Node<T> next;

        public Node(T it,Node<T> p,Node<T> n) {
            this.item = it;
            this.prev = p;
            this.next = n;
        }
    }
}
