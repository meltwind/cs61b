public class doo {
    public  static int  doN(int N,int x,IntUnaryFunction f){
        if (N==0){
            return x;
        }else{
            return doN(N-1,f.apply(x),f);
        }
    }

    public static void main(String[] args) {
        System.out.println(doN(3,2,new sqN()));
    }
}
