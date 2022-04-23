public class ArrayDeque<T> {
    /** Creates an empty list. */
    private T []items;
    private static  int capaticy=8;
    private int right;
    private int left;

    public ArrayDeque() {
        items=(T []) new Object[capaticy];
        left=right=0;
    }

    /** Inserts X into the back of the list. */
    private void resize(int cap)
    {
        T []newarray=(T [])new Object[cap];
        int size=this.size();
        if(right>left)
        {
            System.arraycopy(items,left,newarray,0,size);

        }else if(left>right){
            System.arraycopy(items,left,newarray,0,capaticy-left);
            System.arraycopy(items,0,newarray,capaticy-left,right);
        }
        left=0;
        right=size;
        items=newarray;
        capaticy=cap;
    }
    private boolean isFull() {
        return size() == capaticy - 1;
    }
    public void  addFirst(T x)
    {
        if(isFull())
        {
            resize((int)(capaticy*2));
        }
        left=(left-1+capaticy)%capaticy;
        items[left]=x;
    }

    public void addLast(T x) {
        if(isFull())
        {
            resize((int)(capaticy*2));
        }items[right]=x;
        right=(right+1+capaticy)%capaticy;

    }

    /** Returns the item from the back of the list. */
    public T getLast() {
        return items[right-1];
    }
    public T getFirst()
    {
        return items[left];
    }
    /** Gets the ith item in the list (0 is the front). */
    public T get(int i) {
        return items[(left+i)%capaticy];
    }
    public boolean isEmpty()
    {
        return (left==right);
    }

    /** Returns the number of items in the list. */
    public int size() {
        return (right-left+capaticy)%capaticy;
    }
    public T removeLast()
    {
        if(isEmpty())
        {
            return null;
        }
        T result=items[(right-1+capaticy)%capaticy];
        items[(right-1+capaticy)%capaticy]=null;
        right=(right-1+capaticy)%capaticy;
        if (isLowUsageRate()) {
            resize((int) (capaticy * 0.5));
        }
        return result;

    }
    public T removeFirst()
    {
        if(isEmpty())
        {
            return null;
        }
        T result=items[left];
        items[left]=null;
        left=(left+1+capaticy)%capaticy;
        if (isLowUsageRate()) {
            resize((int) (capaticy * 0.5));
        }
        return  result;

    }
    private boolean  isLowUsageRate()
    {
        return capaticy>16&&(this.size()/(double)capaticy<0.25);
    }
    public void printDeque()
    {
        for(int i=0;i<size();i++)
        {
            System.out.print(get(i));
            System.out.print(" ");
        }
    }




}
