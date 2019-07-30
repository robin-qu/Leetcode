// brute force O(n^2 * k)time O(1)space
// class Solution {
//     public List<List<Integer>> palindromePairs(String[] words) {
//         if (words == null || words.length == 0) {
//             return new ArrayList<>();
//         }
        
//         int n = words.length;
//         List<List<Integer>> res = new ArrayList<>();
        
//         for (int i = 0; i < n; i++) {
//             for (int j = i + 1; j < n; j++) {
//                 String w1 = words[i];
//                 String w2 = words[j];
                
//                 if (isPalindrome(w1 + w2)) {
//                     List<Integer> pair = new ArrayList<>();
//                     pair.add(i);
//                     pair.add(j);
//                     res.add(pair);
//                 }
                
//                 if (isPalindrome(w1 + w2)) {
//                     List<Integer> pair = new ArrayList<>();
//                     pair.add(j);
//                     pair.add(i);
//                     res.add(pair);
//                 }
//             }
//         }
        
//         return res;
//     }
    
//     private boolean isPalindrome(String word) {
//         int left = 0;
//         int right = word.length() - 1;
        
//         while (left < right) {
//             if (word.charAt(left) != word.charAt(right)) {
//                 return false;
//             }
//             left++;
//             right--;
//         }
        
//         return true;
//     }
// }


// Trie O(n * k^2)time O(k)space 
class Solution {
    class TrieNode {
        public TrieNode[] children;
        public int idx;  // the index of word in array, -1 when is not a word
        public List<Integer> list;  // the indices of words whose succeeding substring of the current nod is palindrome

        public TrieNode() {
            this.children = new TrieNode[26];
            this.idx = -1;
            this.list = new ArrayList<>();
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }
        
        int n = words.length;
        TrieNode root = buildTrie(words);        
        
        List<List<Integer>> res = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int len = word.length();
            TrieNode curr = root;
            for (int j = 0; j < len; j++) {
                char c = word.charAt(j);
                
                if (curr.idx >= 0 && curr.idx != i && isPalindrome(word, j, len - 1)) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(i);
                    pair.add(curr.idx);
                    res.add(pair);
                }
                
                curr = curr.children[c - 'a'];
                
                if (curr == null) {  // no valid palindrome
                    break;
                }
            }
            
            if (curr == null) {
                continue;
            }
            
            for (int j : curr.list) {
                if (i != j) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(i);
                    pair.add(j);
                    res.add(pair);
                }
            }
        }
        
        return res;
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        
        for (int i = 0; i < words.length; i++) {
            TrieNode curr = root;
            
            for (int j = words[i].length() - 1; j >= 0; j--) {
                char c = words[i].charAt(j);
                
                if (curr.children[c - 'a'] == null) {
                    curr.children[c - 'a'] = new TrieNode();
                }
                
                if (isPalindrome(words[i], 0, j)) {
                    curr.list.add(i);
                }
                
                curr = curr.children[c - 'a'];
            }
            
            curr.list.add(i);
            curr.idx = i;
        }
        
        return root;
    }
    
    private boolean isPalindrome(String word, int i, int j) {
        int left = i;
        int right = j;
        
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
}