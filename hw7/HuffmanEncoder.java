import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols){
        HashMap<Character,Integer> hm = new HashMap();
        for(char c: inputSymbols){
            if(!hm.containsKey(c)){
                hm.put(c,1);
            }else {
                hm.put(c,(int)hm.get(c) + 1);
            }
        }
        return hm;

    }
    public static void main(String[] args){
        char[] inputSymbols = FileUtils.readFile(args[0]);
        BinaryTrie bt = new BinaryTrie(buildFrequencyTable(inputSymbols));
        ObjectWriter ow = new ObjectWriter(args[0] + ".huf");
        ow.writeObject(bt);
        Map hm = bt.buildLookupTable();
        ArrayList<BitSequence> list = new ArrayList<>();
        for(char c: inputSymbols){
            list.add((BitSequence) hm.get(c));
        }
    ow.writeObject(BitSequence.assemble(list));
    }
}
