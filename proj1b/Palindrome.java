public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> wordDeque = new LinkedListDeque<>();
        for (int i = word.length() - 1; i >= 0; i--) {
            wordDeque.addFirst(word.charAt(i));
        }
        return wordDeque;
    }
    public boolean isPalindrome(String word){
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeRecursion(wordDeque);
    }
    private boolean isPalindromeRecursion(Deque<Character> wordDeque) {
        if (wordDeque.size() <= 1) {
            return true;
        }

        Character front = wordDeque.removeFirst();
        Character end = wordDeque.removeLast();

        if (front.charValue() != end.charValue()) {
            return false;
        }
        return isPalindromeRecursion(wordDeque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeRecursion(wordDeque, cc);
    }
    private boolean isPalindromeRecursion(Deque<Character> wordDeque, CharacterComparator cc) {
        if (wordDeque.size() <= 1) {
            return true;
        }

        Character front = wordDeque.removeFirst();
        Character end = wordDeque.removeLast();

        if (!cc.equalChars(front, end)) {
            return false;
        }
        return isPalindromeRecursion(wordDeque, cc);
    }
}
