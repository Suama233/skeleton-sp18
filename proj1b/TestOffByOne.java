import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator obo = new OffByOne();

    // Your tests go here.
    //Uncomment this class once you've created your CharacterComparator interface and OffByOne class.

    @Test
    public void testEqualChars(){
        assertTrue(obo.equalChars('a','b'));
        assertTrue(obo.equalChars('f','e'));
        assertFalse(obo.equalChars('a','a'));
    }
}
