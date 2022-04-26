public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> de = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            de.addLast(word.charAt(i));
        }
        return de;

    }

    public boolean isPalindrome(String word) {
        if (word.length() == 1) {
            return true;
        }
        Deque<Character> ls = wordToDeque(word);
        while (ls.size() > 1) {
            Character f = ls.removeFirst();
            Character n = ls.removeLast();
            if (f != n) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() <= 1) {
            return true;
        }
        Deque<Character> ls = wordToDeque(word);
        while (ls.size() > 1) {
            Character f = ls.removeFirst();
            Character n = ls.removeLast();
            if (!cc.equalChars(f, n)) {
                return false;
            }
        }
        return true;
    }

}

