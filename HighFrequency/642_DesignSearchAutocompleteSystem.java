// // Trie AutocompleteSystem: O(nl)time O(l)space 
// //      input: O(node + nlog3)time O(1)time
// class AutocompleteSystem {
//     private class TrieNode implements Comparable<TrieNode> {
//         public TrieNode[] children;
//         public String search;
//         public boolean isSentence;
//         public int count;
        
//         public TrieNode() {
//             this.children = new TrieNode[27];
//             this.search = "";
//             this.isSentence = false;
//             this.count = 0;
//         }
        
//         @Override
//         public int compareTo(TrieNode other) {
//             if (this.count != other.count) {
//                 return this.count - other.count;
//             }
            
//             return other.search.compareTo(this.search);
//         }
//     }
    
//     private TrieNode root;
//     private TrieNode curr;
//     private static final int LIMIT = 3;

//     public AutocompleteSystem(String[] sentences, int[] times) {
//         this.root = new TrieNode();
//         for (int i = 0; i < sentences.length; i++) {
//             String sentence = sentences[i];
//             char[] ss = sentence.toCharArray();
//             this.curr = root;
            
//             for (char c : ss) {
//                 int idx = (c == ' ' ? 26 : c - 'a');
//                 if (curr.children[idx] == null) {
//                     curr.children[idx] = new TrieNode();
//                 }
//                 String prev = curr.search;
//                 curr = curr.children[idx];
//                 curr.search = prev + c;
//             }
            
//             curr.isSentence = true;
//             curr.count = times[i];
//         }
//         this.curr = root;
//     }
    
//     public List<String> input(char c) {
//         if (c == '#') {
//             curr.isSentence = true;
//             curr.count++;
//             curr = root;
            
//             return new ArrayList<>();
//         }
        
//         int idx = (c == ' ' ? 26 : c - 'a');
//         if (curr.children[idx] == null) {
//             curr.children[idx] = new TrieNode();
//             String prev = curr.search;
//             curr = curr.children[idx];
//             curr.search = prev + c;
            
//             return new ArrayList<>();
//         }
        
//         curr = curr.children[idx];
//         PriorityQueue<TrieNode> pq = new PriorityQueue<>();
//         dfs(curr, pq);
//         List<String> res = new ArrayList<>();
//         while (!pq.isEmpty()) {
//             res.add(0, pq.poll().search);
//         }
        
//         return res;
//     }
    
//     // pq is of size 3
//     private void dfs(TrieNode node, PriorityQueue<TrieNode> pq) {
//         if (node == null) {
//             return;
//         }
        
//         if (node.isSentence) {
//             pq.offer(node);
//             if (pq.size() > LIMIT) {
//                 pq.poll();
//             }
//         }
        
//         for (int i = 0; i < 27; i++) {
//             dfs(node.children[i], pq);
//         }
//     }
// }

// /**
//  * Your AutocompleteSystem object will be instantiated and called as such:
//  * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
//  * List<String> param_1 = obj.input(c);
//  */



// Trie AutocompleteSystem: O(nl)time O(l)space 
//      input: O(node + nlog3)time O(1)time
class AutocompleteSystem {
    private class TrieNode {
        public TrieNode[] children;
        public Map<String, Integer> counts;  // use a map to store the (sentence, time) pair whose prefix is search
        public String search;
        
        public TrieNode() {
            this.children = new TrieNode[27];
            this.counts = new HashMap<>();
            this.search = null;
        }
    }
    
    private class Pair implements Comparable<Pair> {
        public String sentence;
        public int count;
        
        public Pair(String sentence, int count) {
            this.sentence = sentence;
            this.count = count;
        }
        
        @Override
        public int compareTo(Pair other) {
            if (this.count != other.count) {
                return this.count - other.count;
            }
            
            return other.sentence.compareTo(this.sentence);
        }
    }
    
    private TrieNode root;
    private String prefix;
    private static final int LIMIT = 3;

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.root = new TrieNode();
        this.prefix = "";
        for (int i = 0; i < sentences.length; i++) {
            insert(sentences[i], times[i]);
        }
    }
    
    private void insert(String sentence, int time) {
        char[] ss = sentence.toCharArray();
        TrieNode curr = root;

        for (char c : ss) {
            int idx = (c == ' ' ? 26 : c - 'a');
            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
            curr.counts.put(sentence, curr.counts.getOrDefault(sentence, 0) + time);
        }

        curr.search = sentence;
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            insert(prefix, 1);
            prefix = "";
            
            return new ArrayList<>();
        }
        
        prefix += c;
        TrieNode curr = root;
        
        for (char cc : prefix.toCharArray()) {
            int idx = (cc == ' ' ? 26 : cc - 'a');
            if (curr.children[idx] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[idx];
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (String s : curr.counts.keySet()) {
            pq.offer(new Pair(s, curr.counts.get(s)));
            if (pq.size() > LIMIT) {
                pq.poll();
            }
        }
        
        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(0, pq.poll().sentence);
        }
        
        return res;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */