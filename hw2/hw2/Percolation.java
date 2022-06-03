package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] Array;
    private int openSize;
    private int nn;
    private WeightedQuickUnionUF un;
    private WeightedQuickUnionUF un2;

    public Percolation(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        un = new WeightedQuickUnionUF(n * n + 2);
        un2 = new WeightedQuickUnionUF(n * n + 1);
        Array = new boolean[n][n];
        openSize = 0;
        nn = n * n;
    }

    public void open(int row, int col) {
        validateRange(row, col);
        if (isOpen(row, col)) {
            return;
        }
        Array[row][col] = true;
        connect(row, col);
        openSize += 1;
    }

    public boolean isOpen(int row, int col) {
        validateRange(row, col);
        return Array[row][col];
    }

    public boolean isFull(int row, int col) {
        validateRange(row, col);
        if (!isOpen(row, col)) {
            return false;
        }
        int num = num(row, col);
        return un2.connected(nn, num(row, col));
    }

    public int numberOfOpenSites() {
        return openSize;
    }

    /*return the index of Array site*/
    private int num(int row, int col) {
        int lon = Array[0].length;
        return lon * row + col;
    }

    public boolean percolates() {
        return un.connected(nn, nn + 1);
    }

    private boolean inTop(int num) {
        return (num >= 0 && num < Array[0].length);

    }

    private boolean inBottom(int num) {
        return (num >= (Array.length - 1) * Array[0].length && num < Array.length * Array[0].length);
    }

    /*check up,left,right has full*/
    private boolean bound(int row, int col) {
        if ((valid(row, col - 1) && un2.connected(nn, num(row, col - 1))) ||
                (valid(row, col + 1) && un2.connected(nn, num(row, col + 1))) ||
                (valid(row - 1, col) && un2.connected(nn, num(row - 1, col))) ||
                (valid(row + 1, col) && un2.connected(nn, num(row + 1, col)))) {
            return true;

        }
        return false;
    }


    /*connect full site around,if in the top wall,connect topSentinel nn,bottom connect bottomSentinel nn+1*/
    private void connect(int row, int col) {
        int num = num(row, col);
        if (inTop(num)) {
            un.union(nn, num);
            un2.union(nn, num);
        }
        if (inBottom(num)) {
            un.union(nn + 1, num);
            if (bound(row, col)) {
                un.union(nn, num);
                un2.union(nn, num);
            }

        }
        if (!inBottom(num) && !inTop(num)) {
            if (bound(row, col)) {
                un.union(nn, num);
                un2.union(nn, num);
            }
        }
        connectPrevious(row, col);
    }

    private void validateRange(int row, int col) {
        if (row < 0 || row >= Array[0].length || col < 0 || col >= Array.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean valid(int row, int col) {
        if (row < 0 || row >= Array[0].length || col < 0 || col >= Array.length) {
            return false;
        }
        return true;
    }

    private void connectPrevious(int row, int col) {
        if (isFull(row, col)) {
            int num = num(row, col);
            if (col != 0 && (!un2.connected(nn, num - 1)) && isOpen(row, col - 1)) {
                un.union(nn, num - 1);
                un2.union(nn, num - 1);
                connectPrevious(row, col - 1);
            }
            if (col != Array[0].length - 1 && (!un2.connected(nn, num + 1)) && isOpen(row, col + 1)) {
                un.union(nn, num + 1);
                un2.union(nn, num + 1);
                connectPrevious(row, col + 1);
            }
            if (row != 0 && (!un2.connected(nn, num - Array[0].length)) && isOpen(row - 1, col)) {
                un.union(nn, num - Array[0].length);
                un2.union(nn, num - Array[0].length);
                connectPrevious(row - 1, col);
            }
            if (row != Array.length - 1 && (!un2.connected(nn, num + Array[0].length)) && isOpen(row + 1, col)) {
                un.union(nn, num + Array[0].length);
                un2.union(nn, num + Array[0].length);
                connectPrevious(row + 1, col);
            }

        }
    }

    public static void main(String[] args) {
        Percolation pe = new Percolation(1);
        pe.open(0, 0);
        System.out.println(pe.percolates());
    }
}
