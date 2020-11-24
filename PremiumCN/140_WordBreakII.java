// class Solution {
//     public List<String> wordBreak(String s, List<String> wordDict) {
//         if (s == null || wordDict == null || wordDict.size() == 0) {
//             return new ArrayList<>();
//         }

//         TrieNode root = buildTrie(wordDict);
//         List<String> res = new ArrayList<>();
//         dfs(0, s, new StringBuilder(), root, res);
//         return res;
//     }

//     private void dfs(int idx, String s, StringBuilder sb, TrieNode root, List<String> res) {
//         if (idx == s.length()) {
//             res.add(sb.toString().trim());
//             return;
//         }

//         TrieNode curr = root;
//         for (int i = idx; i < s.length(); i++) {
//             char c = s.charAt(i);
//             if (curr.sons[c - 'a'] == null) {
//                 return;
//             }
//             curr = curr.sons[c - 'a'];
//             if (curr.word != null) {
//                 sb.append(curr.word).append(" ");
//                 dfs(i + 1, s, sb, root, res);
//                 sb.setLength(sb.length() - curr.word.length() - 1);
//             }
//         }
//     }

//     private TrieNode buildTrie(List<String> words) {
//         TrieNode root = new TrieNode();
//         for (String word : words) {
//             TrieNode curr = root;
//             for (char c : word.toCharArray()) {
//                 if (curr.sons[c - 'a'] == null) {
//                     curr.sons[c - 'a'] = new TrieNode();
//                 }
//                 curr = curr.sons[c - 'a'];
//             }
//             curr.word = word;
//         }
//         return root;
//     }

//     class TrieNode {
//         public TrieNode[] sons;
//         public String word;

//         public TrieNode() {
//             this.sons = new TrieNode[26];
//             this.word = null;
//         }
//     }
// }


class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null || wordDict.size() == 0) {
            return new ArrayList<>();
        }
        
        Map<Integer, List<List<String>>> memo = new HashMap<>();
        List<String> res = new ArrayList<>();
        for (List<String> ss : dfs(0, s, new HashSet<>(wordDict), memo)) {
            res.add(String.join(" ", ss));
        }
        return res;
    }

    private List<List<String>> dfs(int idx, String s, Set<String> words, Map<Integer, List<List<String>>> memo) {
        if (memo.containsKey(idx)) {
            return memo.get(idx);
        }

        List<List<String>> res = new ArrayList<>();
        if (idx == s.length()) {
            res.add(new ArrayList<>());
            memo.put(idx, res);
            return res;
        }
        
        for (int i = idx + 1; i <= s.length(); i++) {
            String word = s.substring(idx, i);
            if (words.contains(word)) {
                List<List<String>> list = dfs(i, s, words, memo);
                for (List<String> ss : list) {
                    List<String> curr = new ArrayList<>();
                    curr.add(word);
                    curr.addAll(ss);
                    res.add(curr);
                }
            }
        }

        memo.put(idx, res);
        return res;
    }
}