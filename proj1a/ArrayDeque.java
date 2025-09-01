public class ArrayDeque<T> {

    @SuppressWarnings("unchecked")

    /*
      循环是：index = (capacity + index) % capacity
     */

    public ArrayDeque() {
        this.capacity = 2;
        this.items = (T[]) new Object[capacity];
        this.size = 0;
        this.startPos = 0;
    }
    public void addFirst(T item) {
        if (getUsageRadio() >= 1) {
            resize(1);
        }
        startPos = (capacity + startPos - 1) % capacity;
        items[startPos] = item;
        size += 1;
    }
    public void addLast(T item) {
        if (getUsageRadio() >= 1) {
            resize(1);
        }
        items[(startPos + size) % capacity] = item;
        size += 1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(this.items[(startPos + i) % capacity] + " ");
        }
    }
    public T removeFirst() {
        T item = null;
        if (size > 1) {
            item = items[startPos];
            items[startPos] = null;
            startPos = (startPos + 1) % capacity;
            size -= 1;
            if (getUsageRadio() <= 0.25) {
                resize(0);
            }
        }
        return item;
    }
    public T removeLast() {
        T item = null;
        if (size > 1) {
            item = items[(startPos + size - 1) % capacity];
            items[(startPos + size - 1) % capacity] = null;
            size -= 1;
            if (getUsageRadio() <= 0.25) {
                resize(0);
            }
        }
        return item;
    }

    public T get(int index) {
        return items[(startPos + index) % capacity];
    }

    /** condition == 1 --> enlarge;condition == 0 --> shrink*/
    @SuppressWarnings("unchecked")
    private void resize(int condition) {
        if (condition == 1) {
            T[] newItems = (T[]) new Object[capacity * 2];
            for (int i = 0; i < size; i++) {
                newItems[i] = items[(startPos + i) % capacity];
                items[(startPos + i) % capacity] = null;
            }
            startPos = 0;
            items = newItems;
            capacity *= 2;
        } else if (condition == 0 && size > 1) { //否则可能出现size=1后还在缩的情况
            T[] newItems = (T[]) new Object[capacity / 2];
            for (int i = 0; i < size; i++) {
                newItems[i] = items[(startPos + i) % capacity];
                items[(startPos + i) % capacity] = null;
            }
            startPos = 0;
            items = newItems;
            capacity /= 2;
        }
    }
    private double getUsageRadio() {
        return size * 1.0 / capacity;
    }

    private T[] items;
    private int size;
    private int startPos;
    private int capacity;
}
