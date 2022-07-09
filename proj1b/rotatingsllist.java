public class rotatingsllist<T> extends LinkedListDeque<T>{
    public rotatingsllist(T x){
        super(x);//写了有参构造后，系统不再提供默认构造，和c一样

    }
//    public void rotateRight(){
//        sentinel.prev.next=sentinel.next;
//        sentinel.next.prev=sentinel.prev;
//        sentinel.next=sentinel.prev;
//        sentinel.prev=sentinel.prev.prev;
//    }


//    public static void main(String[] args) {
//        rotatingsllist<Integer> ls= new rotatingsllist(3);
//        ls.addLast(2);
//        ls.addLast(3);
//        ls.addFirst(1);
//        ls.rotateRight();
//    }
}
