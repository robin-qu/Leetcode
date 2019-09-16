// // hashmap O(n^2)time O(n)space
// class WordDistance {
//     private Map<String, Set<Integer>> dists;

//     public WordDistance(String[] words) {
//         dists = new HashMap<>();
//         for (int i = 0; i < words.length; i++) {
//             if (!dists.containsKey(words[i])) {
//                 dists.put(words[i], new HashSet<>());
//             }
//             dists.get(words[i]).add(i);
//         }
//     }
    
//     public int shortest(String word1, String word2) {
//         int min = Integer.MAX_VALUE;
//         for (int i1 : dists.get(word1)) {
//             for (int i2 : dists.get(word2)) {
//                 min = Math.min(min, Math.abs(i1 - i2));
//             }
//         }
        
//         return min;
//     }
// }

// /**
//  * Your WordDistance object will be instantiated and called as such:
//  * WordDistance obj = new WordDistance(words);
//  * int param_1 = obj.shortest(word1,word2);
//  */


// hashmap O(n1 + n2)time O(n)space
class WordDistance {
    private Map<String, List<Integer>> dists;

    public WordDistance(String[] words) {
        dists = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (!dists.containsKey(words[i])) {
                dists.put(words[i], new ArrayList<>());
            }
            dists.get(words[i]).add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        int min = Integer.MAX_VALUE;
        int i1 = 0;
        int i2 = 0;
        List<Integer> l1 = dists.get(word1);
        List<Integer> l2 = dists.get(word2);
        
        while (i1 < l1.size() && i2 < l2.size()) {
            min = Math.min(min, Math.abs(l1.get(i1) - l2.get(i2)));
            if (l1.get(i1) < l2.get(i2)) {
                i1++;
            } else {
                i2++;
            }
        }
        
        return min;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */