// public class Solution {
//     /**
//      * @param board: A list of lists of character
//      * @param words: A list of string
//      * @return: A list of string
//      */
//     class TrieNode {
//         public TrieNode[] sons;
//         public boolean isWord;
//         public String word;
        
//         public TrieNode(TrieNode[] sons, boolean isWord, String word) {
//             this.sons = sons;
//             this.isWord = isWord;
//             this.word = word;
//         }
//     }
    
//     private TrieNode root = new TrieNode(new TrieNode[26], false, null);
    
//     public List<String> wordSearchII(char[][] board, List<String> words) {
//         if (board == null || board.length == 0 ||
//             board[0] == null || board[0].length == 0 ||
//             words == null || words.size() == 0) {
//                 return new ArrayList<>();
//         }
        
//         buildTrie(words);
        
//         int m = board.length;
//         int n = board[0].length;
//         List<String> res = new ArrayList<>();
        
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 dfs(i, j, board, res, root);
//             }
//         }
        
//         return res;
//     }
    
//     private void dfs(int i, int j, char[][] board, List<String> res, TrieNode curr) {
//         if (curr.isWord && !res.contains(curr.word)) {
//             res.add(curr.word);
//         }
        
//         if (!inBound(i, j, board)) {
//             return;
//         }
        
//         char c = board[i][j];
//         int[] dx = new int[]{0, 1, -1, 0};
//         int[] dy = new int[]{1, 0, 0, -1};
//         if (c != '*' && curr.sons[c - 'a'] != null) {
//             board[i][j] = '*';
//             for (int k = 0; k < 4; k++) {
//                 dfs(i + dx[k], j + dy[k], board, res, curr.sons[c - 'a']);
//             }
//             board[i][j] = c;
//         }
//     }
    
//     private boolean inBound(int i, int j, char[][] board) {
//         return i >= 0 && j >= 0 && i < board.length && j < board[0].length;
//     }
    
//     private void buildTrie(List<String> words) {
//         for (int i = 0; i < words.size(); i++) {
//             String word = words.get(i);
//             TrieNode curr = root;
//             for (int j = 0; j < word.length(); j++) {
//                 char c = word.charAt(j);
//                 if (curr.sons[c - 'a'] == null) {
//                     curr.sons[c - 'a'] = new TrieNode(new TrieNode[26], false, null);
//                 }
//                 curr = curr.sons[c - 'a'];
//             }
//             curr.isWord = true;
//             curr.word = word;
//         }
//     }
// }

// optimized by setting word to null when the word is added to
// the result list
public class Solution {
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    class TrieNode {
        public TrieNode[] sons;
        public String word;
        
        public TrieNode(TrieNode[] sons, String word) {
            this.sons = sons;
            this.word = word;
        }
    }
    
    private TrieNode root = new TrieNode(new TrieNode[26], null);
    
    public List<String> wordSearchII(char[][] board, List<String> words) {
        if (board == null || board.length == 0 ||
            board[0] == null || board[0].length == 0 ||
            words == null || words.size() == 0) {
                return new ArrayList<>();
        }
        
        buildTrie(words);
        
        int m = board.length;
        int n = board[0].length;
        List<String> res = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, board, res, root);
            }
        }
        
        return res;
    }
    
    private void dfs(int i, int j, char[][] board, List<String> res, TrieNode curr) {
        if (curr.word != null) {
            res.add(curr.word);
            curr.word = null; // deduplicate
        }
        
        if (!inBound(i, j, board)) {
            return;
        }
        
        char c = board[i][j];
        int[] dx = new int[]{0, 1, -1, 0};
        int[] dy = new int[]{1, 0, 0, -1};
        if (c != '*' && curr.sons[c - 'a'] != null) {
            board[i][j] = '*';
            for (int k = 0; k < 4; k++) {
                dfs(i + dx[k], j + dy[k], board, res, curr.sons[c - 'a']);
            }
            board[i][j] = c;
        }
    }
    
    private boolean inBound(int i, int j, char[][] board) {
        return i >= 0 && j >= 0 && i < board.length && j < board[0].length;
    }
    
    private void buildTrie(List<String> words) {
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            TrieNode curr = root;
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (curr.sons[c - 'a'] == null) {
                    curr.sons[c - 'a'] = new TrieNode(new TrieNode[26], null);
                }
                curr = curr.sons[c - 'a'];
            }
            curr.word = word;
        }
    }
}