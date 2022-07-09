

public class LinkedListDeque<T> {

    private IntNode sentinel = new IntNode(null, null, null);
    private int size;

    private class IntNode {
        public IntNode prev;
        public T item;
        public IntNode next;

        public IntNode(IntNode p, T it, IntNode n) {
            prev = p;
            item = it;
            next = n;

        }

    }

    public LinkedListDeque() {
        size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;//圈


    }

    private LinkedListDeque(T x) {
        size = 1;
        IntNode newnode = new IntNode(sentinel, x, sentinel);
        sentinel.next = newnode;
        sentinel.prev = newnode;

    }

    public void addFirst(T item) {
        size += 1;
        IntNode newnode = new IntNode(sentinel, item, sentinel.next);
        sentinel.next.prev = newnode;
        sentinel.next = newnode;


    }

    public void addLast(T item) {
        size += 1;
        IntNode newnode = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = newnode;
        sentinel.prev = newnode;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T result = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return result;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T result = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return result;
    }

    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        int flag = 0;
        IntNode p = sentinel.next;
        while (index != flag) {

            flag += 1;
            p = p.next;
        }
        return p.item;

    }

    private T gethelp(IntNode p, int index) {
        if (index == 0) {
            return p.item;
        } else {
            return gethelp(p.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        return gethelp(sentinel.next, index);
    }

    public void printDeque() {
        IntNode p = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
    }


}
