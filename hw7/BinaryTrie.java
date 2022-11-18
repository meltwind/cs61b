

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class BinaryTrie  implements Serializable {
    private Node root;
    private static class Node implements Comparable<Node>, Serializable  {


        private final Node left, right;
        private Map.Entry<Character,Integer> freqEntry;

        Node(Map.Entry<Character,Integer> freq,Node left, Node right)  {
            this.freqEntry = freq;
            this.left  = left;
            this.right = right;
        }

        // is the node a leaf node?
        private boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            return this.freqEntry.getValue()- that.freqEntry.getValue();
        }
    }

    public BinaryTrie(Map<Character, Integer> frequencyTable){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(Map.Entry<Character,Integer> en : frequencyTable.entrySet()){
            pq.add(new Node(en,null,null));
        }
        while (pq.size() > 1){
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node(new AbstractMap.SimpleEntry<>
                    (null,left.freqEntry.getValue()+right.freqEntry.getValue()),left,right
            );
            pq.add(parent);
        }
        root = pq.poll();

    }
    public Match longestPrefixMatch(BitSequence querySequence){
        BitSequence BitsResult = new BitSequence("");
        Node node = root;
        for(int i = 0; i < querySequence.length(); i++){
            if(querySequence.bitAt(i) == 0){
                node = node.left;
               BitsResult = BitsResult.appended(0);
            }else {
                node = node.right;
                BitsResult = BitsResult.appended(1); // same as String is immutable
            }
            if(node.isLeaf()){
                return new Match(BitsResult,node.freqEntry.getKey());
            }
        }
        return null;
    }
    public Map<Character, BitSequence> buildLookupTable(){

        Map<Character,BitSequence> result = new HashMap<>();
        buildCodeHelp(result,root,new BitSequence());
        return  result;
    }
    private void buildCodeHelp(Map<Character,BitSequence> map,
                                                 Node thisRoot,BitSequence bits){
        if(!thisRoot.isLeaf()){
            buildCodeHelp(map,thisRoot.left,bits.appended(0));
            buildCodeHelp(map,thisRoot.right,bits.appended(1));
        }
        else {
            map.put(thisRoot.freqEntry.getKey(),bits);
        }
    }

}
