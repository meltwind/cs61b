public class OffByN implements CharacterComparator {
    private int N;
    OffByN(int n){
        N=n;
    }
    @Override
    public boolean equalChars(char x, char y){
        int a=x;
        int b=y;
        int c=Math.abs(a-b);
        return c==N;
    }
}
