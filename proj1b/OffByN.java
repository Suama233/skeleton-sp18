public class OffByN implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return (diff  == this.n || diff == this.n * -1);
    }
    public OffByN(int n) {
        this.n = n;
    }

    private int n;
}
