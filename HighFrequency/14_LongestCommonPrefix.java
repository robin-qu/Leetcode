// // Trie O(nl)time O(l)space
// class Solution {
//     class TrieNode {
//         public TrieNode[] children;
//         public boolean isWord;
        
//         public TrieNode() {
//             this.children = new TrieNode[26];
//             this.isWord = false;
//         }
//     }
    
//     public String longestCommonPrefix(String[] strs) {
//         if (strs == null || strs.length == 0) {
//             return "";
//         }
        
//         TrieNode root = new TrieNode();
        
//         for (String s : strs) {
//             char[] ss = s.toCharArray();
//             TrieNode curr = root;
            
//             for (char c : ss) {
//                 if (curr.children[c - 'a'] == null) {
//                     curr.children[c - 'a'] = new TrieNode();
//                 }
                
//                 curr = curr.children[c - 'a'];
//             }
            
//             curr.isWord = true;
//         }
        
//         TrieNode curr = root;
//         String prefix = "";
//         int idx = 0;
//         int count = 0;
//         boolean stop = false;
//         for (int i = 0; i < 26; i++) {
//             if (curr.children[i] != null) {
//                 count++;
//             }
//             if (curr.isWord) {
//                 stop = true;
//             }
//         }
        
//         while (count == 1 && !stop) {
//             curr = curr.children[strs[0].charAt(idx) - 'a'];
//             idx++;
//             count = 0;
//             for (int i = 0; i < 26; i++) {
//                 if (curr.children[i] != null) {
//                     count++;
//                 }
//                 if (curr.isWord) {
//                     stop = true;
//                 }
//             }
//         }
        
//         return strs[0].substring(0, idx);
//     }
// }



// Trie O(nl)time O(1)space
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.length() == 0) {
                    return "";
                }
            }
        }
        
        return prefix;
    }
}