package lab9;

import edu.princeton.cs.algs4.In;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;
        private int size;

        private Node(K k, V v,int s) {
            key = k;
            value = v;
            size = s;
        }
    }

    private Node root;  /* Root node of the tree. */
    /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if(p == null){
            return null;
        }
        int cmp = key.compareTo(p.key);
        if(cmp>0){
            return getHelper(key,p.right);
        } else if (cmp < 0) {
            return getHelper(key,p.left);
        }else {
            return p.value;
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key,root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if(p == null){
            return new Node(key,value,1);
        }
        int cmp = key.compareTo(p.key);
        if(cmp>0){
            p.right = putHelper(key,value,p.right);
        } else if (cmp < 0) {
            p.left = putHelper(key,value,p.left);
        }else {
            p.value = value;
        }
        p.size = 1 + getSize(p.left) + getSize(p.right);
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key,value,root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return getSize(root);
    }

    private int getSize(Node x){
        if(x == null){
            return 0;
        }
        return x.size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set se = new HashSet<>();
        keySet(root,se);
        return se;



    }
    private void keySet(Node p,Set<K> s){
        if(p !=null){
            s.add(p.key);
            keySet(p.left,s);
            keySet(p.right,s);
        }



    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        return remove(key,root);
    }

    private V remove(K key,Node p){
        if(p == null){
            return null;
        }
        V value = get(key);
        if(value == null){
            return null;
        }
        int cmp = key.compareTo(p.key);
        if(cmp > 0){
            remove(key,p.right);
        } else if (cmp < 0) {
            remove(key,p.left);
        }else {
            Node t = p;
            p = min(t.right);
            p.right = deleteMin(t.right);
            p.left = t.left;
           p.size = 1 + getSize(p.left) + getSize(p.right);
        }
        return value;

    }

    private Node deleteMin(Node p){
        if(p.left == null){
            return p.right;
        }
        p.left = deleteMin(p.left);
        p.size = 1 + getSize(p.right) + getSize(p.left);
        return p;
    }

    private Node min(Node p){
        if(p.left == null){
            return p;
        }
        return min(p.left);
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if(value == get(key)) {
            return remove(key);
        }else{
            return null;
        }

    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public static void main(String[] args) {
        BSTMap<String, Integer > bstMap = new BSTMap();
        bstMap.put("cat",1);
        bstMap.put("dog",2);
        bstMap.put("pig",3);
        bstMap.remove("ddd");
        for(String items:bstMap){
            System.out.println(items);
        }
    }
}
