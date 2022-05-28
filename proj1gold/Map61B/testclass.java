package Map61B;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Optional;

public class testclass {
    @Test
    public void test1() {
        ArrayMap<Integer, Integer> am = new ArrayMap<Integer, Integer>();
        am.put(2, 5);
        int expected = 5;
        assertEquals(expected, (int) am.get(2));
    }

    @Test
    public void test2() {
        ArrayMap<Integer, Integer> am = new ArrayMap<Integer, Integer>();
        am.put(2, 5);
        am.put(3, 10);
        int expected = 5;
        assertEquals((Integer) expected, MapHelper.get(am, 2));

    }
}
