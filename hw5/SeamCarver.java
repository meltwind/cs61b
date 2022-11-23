import edu.princeton.cs.algs4.Picture;

import java.util.Arrays;

public class SeamCarver {
    private final Picture pc;
    private double[][] energy;
    private int[][] edgeTo;
    private double[][] MinEnergy;
    private boolean IsVertical = true;


    public SeamCarver(Picture picture) {
        pc = new Picture(picture);
        calculateEnergy();
    }

    public Picture picture() {
        return new Picture(pc);
    }

    public int width() {
        return pc.width();
    }

    public int height() {
        return pc.height();
    }

    public double energy(int x, int y) {

        return energy[x][y];
    }

    private void calculateEnergy() {
        energy = new double[pc.width()][pc.height()];
        double xx;
        double yy;
        double x1;
        double x2;
        double x3;
        double y1;
        double y2;
        double y3;
        int w = pc.width();
        int h = pc.height();
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                x1 = pc.get(Math.floorMod(x + 1, w), y).getRed() - pc.get(Math.floorMod(x - 1, w), y).getRed();
                x2 = pc.get(Math.floorMod(x + 1, w), y).getBlue() - pc.get(Math.floorMod(x - 1, w), y).getBlue();
                x3 = pc.get(Math.floorMod(x + 1, w), y).getGreen() - pc.get(Math.floorMod(x - 1, w), y).getGreen();
                xx = x1 * x1 + x2 * x2 + x3 * x3;
                y1 = pc.get(x, Math.floorMod(y + 1, h)).getRed() - pc.get(x, Math.floorMod(y - 1, h)).getRed();
                y2 = pc.get(x, Math.floorMod(y + 1, h)).getBlue() - pc.get(x, Math.floorMod(y - 1, h)).getBlue();
                y3 = pc.get(x, Math.floorMod(y + 1, h)).getGreen() - pc.get(x, Math.floorMod(y - 1, h)).getGreen();
                yy = y1 * y1 + y2 * y2 + y3 * y3;
                energy[x][y] = xx + yy;

            }
        }
    }

    /* public int[] findVerticalSeam() {
         int[] result = new int[energy[0].length];
         ArrayList<Integer> al = null;
         double Sum = Double.POSITIVE_INFINITY;
         int nextNum;//the next small index of energy
         for (int i = 0; i < energy.length; i++) {
             double newSum = 0.0;
             nextNum = i;
             ArrayList<Integer> NewAl = new ArrayList<>();
             NewAl.add(nextNum);
             newSum += energy[nextNum][0];
             for (int j = 1; j < energy[0].length; j++) {
                 nextNum = findMinVertical(nextNum, j - 1);
                 newSum += energy[nextNum][j];
                 NewAl.add(nextNum);
             }
             if (newSum < Sum) {
                 Sum = newSum;
                 al = NewAl;
             }
         }
         for (int i = 0; i < energy[0].length; i++) {
             result[i] = al.get(i);
         }
         return result;
     }*/
    public int[] findVerticalSeam() {
        if (IsVertical) {
            edgeTo = new int[pc.width()][pc.height()];
            MinEnergy = Arrays.copyOf(energy, energy.length);
        }

        int[] result = new int[MinEnergy[0].length];
        int flag;//the index of findMinVertical
        for (int i = MinEnergy[0].length - 2; i >= 0; i--) {
            for (int j = 0; j < MinEnergy.length; j++) {
                flag = findMinVertical(j, i);
                MinEnergy[j][i] += MinEnergy[flag][i + 1];
                edgeTo[j][i] = flag;
            }
        }
        int first = 0;
        for (int i = 1; i < MinEnergy.length; i++) {
            if (MinEnergy[first][0] > MinEnergy[i][0]) {
                first = i;
            }
        }
        result[0] = first;
        for (int i = 1; i < MinEnergy[0].length; i++) {
            result[i] = edgeTo[result[i - 1]][i - 1];
        }
        IsVertical = true;
        return result;

    }

    private int findMinVertical(int x, int y) {
        if (x == 0) {
            if (MinEnergy[x][y + 1] > MinEnergy[x + 1][y + 1]) {
                return x + 1;
            } else {
                return x;
            }
        } else if (x == MinEnergy.length - 1) {
            if (MinEnergy[x][y + 1] > MinEnergy[x - 1][y + 1]) {
                return x - 1;
            } else {
                return x;
            }
        } else {
            if (MinEnergy[x][y + 1] > MinEnergy[x - 1][y + 1]) {
                if (MinEnergy[x - 1][y + 1] > MinEnergy[x + 1][y + 1]) {
                    return x + 1;
                } else {
                    return x - 1;
                }
            } else {
                if (MinEnergy[x][y + 1] > MinEnergy[x + 1][y + 1]) {
                    return x + 1;
                } else {
                    return x;
                }
            }
        }
    }

    public int[] findHorizontalSeam() {
        IsVertical = false;
        edgeTo = new int[pc.height()][pc.width()];
        MinEnergy = new double[pc.height()][pc.width()];
        for (int i = 0; i < pc.width(); i++) {
            for (int j = 0; j < pc.height(); j++) {
                MinEnergy[j][i] = energy[i][j];
            }
        }
        return findVerticalSeam();
    }

    public void removeHorizontalSeam(int[] seam) {
        SeamRemover.removeHorizontalSeam(pc, seam);
    }

    public void removeVerticalSeam(int[] seam) {
        SeamRemover.removeVerticalSeam(pc, seam);
    }


}

