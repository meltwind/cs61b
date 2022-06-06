package hw3.hash;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int n = oomages.size();
        int up = (int) (n / 2.5);
        int down = (int) (n / 50);
        int[] Array = new int[M];
        for (Oomage oomage : oomages) {
            int backNum = (oomage.hashCode() & 0x7FFFFFFF) % M;
            Array[backNum] += 1;
        }
        for (int i = 0; i < M; i++) {
            if (down > Array[i] || Array[i] > up) {
                return false;
            }
        }
        return true;
    }

}
