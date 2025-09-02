import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testT() {

        StudentArrayDeque<Integer> wA = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> rA = new ArrayDequeSolution<>();

        /* Call SAD methods and ADS methods randomly
         * until a disagreement occur*/
        /* Using numbers to encode operations:
        * 0 addFirst
        * 1 addLast
        * 2 removeFirst
        * 3 removeLast*/
        int code = -1;
        // Number for addition
        int numAdd = -1;
        // Get number removed
        int rANumRemoveRA = -1;
        int wANumRemove = -1;
        // To prevent infinite looping
        int numMaxOperation = 10000;
        //To record current operations
        int numOperations = 0;
        //To generate next step
        int codeOperation = -1;
        //Record Operation
        String log = "";
        while (numOperations < numMaxOperation) {
            if (rA.isEmpty()) {
                code = StdRandom.uniform(2);
            } else {
                code = StdRandom.uniform(4);
            }

            if (code <= 1) {
                numAdd = StdRandom.uniform(500);
                if (code == 0) {
                    rA.addFirst(numAdd);
                    wA.addFirst(numAdd);
                    log = log + "addFirst(" + numAdd + ")\n";
                } else {
                    rA.addLast(numAdd);
                    wA.addLast(numAdd);
                    log = log + "addLast(" + numAdd + ")\n";
                }
            } else {
                if (code == 2) {
                    rANumRemoveRA = rA.removeFirst();
                    wANumRemove = wA.removeFirst();
                    log = log + "removeFirst\n";
                } else {
                    rANumRemoveRA = rA.removeLast();
                    wANumRemove = wA.removeLast();
                    log = log + "removeLast\n";
                }
                assertEquals(log,rANumRemoveRA,wANumRemove);
            }
            numOperations++;
        }

    }
}
