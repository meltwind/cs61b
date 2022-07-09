
public interface Deque<T> {
    public void addFirst(T item);
    public void addLast(T item);
    public boolean isEmpty();
    public int size();
    public void printDeque();
    public T removeFirst();
    public T removeLast();
    public T get(int index);
    default public void print(){
        for(int i=0;i<size();i++){
            System.out.println(get(i));
        }

    }

    public static void main(String[] args) {
        Deque<Integer> p=new LinkedListDeque<>();
        p.addLast(1);
        p.addLast(2);
        p.addLast(3);
        p.print();
    }

}
