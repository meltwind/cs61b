import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArraySet<T> implements Iterable<T> {
    private int size;
    private T[] items;

    public ArraySet() {
        size = 0;
        items = (T[]) new Object[100];
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i += 1) {
            if (items[i] == null) {
                if (x == null) {
                    return true;
                }

            } else {
                if (items[i].equals(x)) {
                    return true;
                }

            }
        }
        return false;
    }


    /* Associates the specified value with the specified key in this map.
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (contains(x)) {
            return;
        } else {
            items[size] = x;
            size += 1;
        }

    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    @Override//相当于python里的yield
    public Iterator<T> iterator() {
        return new Arraysetiterator();
    }

    private class Arraysetiterator implements Iterator<T> {
        int flag;

        Arraysetiterator() {
            flag = 0;
        }

        @Override
        public boolean hasNext() {
            return flag < size;
        }

        @Override
        public T next() {
            T ret = items[flag];
            flag += 1;
            return ret;
        }
    }

    @Override//打印的字符形式
    public String toString() {
        StringBuilder ss = new StringBuilder("{");
        List<String> ls = new ArrayList<>();
        for (T it : this) {
            if (it == null) {
                ls.add(null);
            } else {
                ls.add(it.toString());
            }
        }
        ss.append(String.join(",", ls));
        ss.append("}");
        return ss.toString();
    }

    @Override
    public boolean equals(Object j) {
        if (j == null) {
            return false;
        }
        if (this == j) {
            return true;
        }
        if (this.getClass() != j.getClass()) {
            return false;
        }
        ArraySet<T> aset = (ArraySet<T>) j;
        if (this.size() != aset.size()) {
            return false;
        }
        for (T item : this) {
            if (!aset.contains(item)) {
                return false;
            }
        }
        return true;

    }

    public static <T> ArraySet<T> of(T... staff) {//iterator
        ArraySet<T> arst = new ArraySet<>();
        for (T it : staff) {
            arst.add(it);
        }
        return arst;
    }

    public static void main(String[] args) {
        try //ArraySet<String> s = ArraySet.of("cat","pig","dog");
        {
            ArraySet<String> s = new ArraySet<>();
            s.add("pig");
            s.add(null);
            s.add("cat");
            System.out.println(s);
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }
}