package lab9;

import org.junit.Test;

public class tree {
    public String key;
    public int value;

    /* Children of this Node. */
    public tree left;
    public tree right;
    public tree(String k, int v) {
        key = k;
        value = v;

    }
    public static void test(tree t){
        t = null;
    }

    public static void main(String[] args) {
        tree t1 = new tree("cat",1);
        t1.left = new tree("dog",2);
        t1.right = new tree("pig",3);
        tree.test(t1);
        System.out.println(t1.right == null);

    }

}
