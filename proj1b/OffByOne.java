public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int a = x;
        int b = y;
        int c = Math.abs(a - b);
        return c == 1;
    }
}
