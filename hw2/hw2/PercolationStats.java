package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] array;
    private int T;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N < 0 || T < 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        array = new double[T];
        Percolation pe = pf.make(N);
        for (int i = 0; i < T; i++) {
            while (!pe.percolates()) {
                int x, y;
                do {
                    x = StdRandom.uniform(N);
                    y = StdRandom.uniform(N);
                } while (pe.isOpen(x, y));
                pe.open(x, y);
            }
            array[i] = (double) pe.numberOfOpenSites() / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(array);
    }

    public double stddev() {
        return StdStats.stddev(array);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

}
