import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator obo = new OffByOne();
    static CharacterComparator obn = new OffByN(1);

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } //Uncomment this class once you've created your Palindrome class. */

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertFalse(palindrome.isPalindrome("aBA"));
    }
    @Test
    public void testIsPalindromeObo() {
        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome("", obo));
        assertFalse(palindrome.isPalindrome("racecar", obo));
        assertFalse(palindrome.isPalindrome("aBA", obo));
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("agfb", obo));
    }
    @Test
    public void testIsPalindromeObn() {
        assertTrue(palindrome.isPalindrome("a", obn));
        assertTrue(palindrome.isPalindrome("", obn));
        assertFalse(palindrome.isPalindrome("racecar", obn));
        assertFalse(palindrome.isPalindrome("aBA", obn));
        assertTrue(palindrome.isPalindrome("flake", obn));
        assertTrue(palindrome.isPalindrome("agfb", obn));
    }
}
