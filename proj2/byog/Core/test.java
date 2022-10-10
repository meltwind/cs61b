package byog.Core;

import org.junit.Test;

import static org.junit.Assert.*;


public class test {
    @Test
    public void test1() {
        room r1 = new room(new position(0, 0), 20, 20);
        for (int i = 0; i <= 19; i++) {
            for (int j = 0; j <= 19; j++) {
                assertTrue(r1.isInn(new position(i, j)));
            }
        }
        for (int i = 20; i < 100; i++) {
            for (int j = 20; j < 100; j++) {
                assertFalse(r1.isInn(new position(i, j)));
            }
        }
    }

    @Test
    public void test2() {
        room r1 = new room(new position(0, 0), 20, 20);
        room r2 = new room(new position(10, 10), 10, 20);
        assertTrue(r1.iscover(r2));
    }
}