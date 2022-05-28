package Map61B;

import java.util.List;

public class MapHelper {
    public static <K, V> V get(ArrayMap<K, V> msi, K key) {
        if (msi.containsKey(key)) {
            return msi.get(key);
        } else {
            return null;
        }
    }

    public static <K extends Comparable<K>, V> K maxget(ArrayMap<K, V> msi) {
        List<K> ls = msi.keys();
        K flag = ls.get(0);
        for (int i = 1; i < ls.size(); i += 1) {
            if (flag.compareTo(ls.get(i)) < 0) {
                flag = ls.get(i);
            }
        }
        return flag;

    }


}
