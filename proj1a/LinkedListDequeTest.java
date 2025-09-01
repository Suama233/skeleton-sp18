

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
    public static void selfTest() {
        System.out.println("Running tests.\n");


        LinkedListDeque<Integer> LinkedListDeque = new LinkedListDeque<Integer>();
        LinkedListDeque.addLast(0);
        int ans = LinkedListDeque.removeFirst();

        System.out.println(ans);

        System.out.println("Test Finished");
    }
    public static void main(String[] args){
        selfTest();
    }
}
