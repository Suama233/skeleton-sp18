package synthesizer;

import java.util.HashSet;
import java.util.Set;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayRingBuffer<Double>((int) Math.round(SR / frequency));
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        int cap = this.buffer.capacity();
        Set<Double> rNums = new HashSet<>();
        for (int i = 0; i < cap; i++) {
            double rTmp = Math.random() - 0.5;
            rNums.add(rTmp);
            while (rNums.size() < cap && (!rNums.contains(rTmp))) {
                rNums.add(rTmp);
                rTmp = Math.random() - 0.5;
            }
        }
        for (double i : rNums) {
            buffer.enqueue(i);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        double item = buffer.dequeue();
        buffer.enqueue((item + buffer.peek()) / 2.0 * DECAY);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
