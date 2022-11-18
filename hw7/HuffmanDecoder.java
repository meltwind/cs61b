import java.util.ArrayList;

public class HuffmanDecoder {
    public static void main(String[] args){
        ObjectReader or = new ObjectReader(args[0]);
        BinaryTrie bt = (BinaryTrie) or.readObject();
        BitSequence bs = (BitSequence) or.readObject();
        ArrayList<Character> al = new ArrayList<>();
        while (bt.longestPrefixMatch(bs) != null){
            Match match = bt.longestPrefixMatch(bs);
            al.add(match.getSymbol());
            bs = bs.allButFirstNBits(match.getSequence().length());
        }
        char[] chars = new char[al.size()];
        for(int i = 0; i < al.size(); i++){
            chars[i] = al.get(i);
        }
        FileUtils.writeCharArray(args[1],chars);




    }
}
