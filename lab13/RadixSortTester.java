
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.PriorityQueue;

public class RadixSortTester {
    //private static String[] unsorted = {"cat","apple","dog","pig","mouse"};
    private static String[] unsorted = {"1","4","101","2312","124"};


    public static void assertIsSorted(String[] a) {
        String previous = "";
        for (String x : a) {
            assertTrue(x.compareTo(previous) >= 0);
            previous = x;
        }
    }

    @Test
    public void testSort(){

        for(String item: unsorted){
            System.out.println(item);
        }
        String[] sorted = RadixSort.sort(unsorted);
        //assertIsSorted(sorted);
        for(String item: sorted){
            System.out.println(item);
        }
    }

}
