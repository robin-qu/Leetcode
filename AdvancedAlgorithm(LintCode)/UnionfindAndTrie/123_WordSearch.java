// // DFS (Time Limit Exceeded)
// public class Solution {
//     /**
//      * @param board: A list of lists of character
//      * @param word: A string
//      * @return: A boolean
//      */
//     class Pair {
//         public int x;
//         public int y;
        
//         public Pair(int x, int y) {
//             this.x = x;
//             this.y = y;
//         }
//     }
    
//     public boolean exist(char[][] board, String word) {
//         if (board == null || board.length == 0 ||
//             board[0] == null || board[0].length == 0 ||
//             word == null || word.length() == 0) {
//             return false;
//         }
        
//         int m = board.length;
//         int n = board[0].length;
//         Map<Character, List<Pair>> map = new HashMap<>();
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 char c = board[i][j];
//                 if (!map.containsKey(c)) {
//                     map.put(c, new ArrayList<>());
//                 }
//                 map.get(c).add(new Pair(i, j));
//             }
//         }
//         Set<Pair> visited = new HashSet<>();
//         List<String> res = new ArrayList<>();
        
//         dfs(word, 0, null, map, visited, new StringBuffer(), res);
        
//         return res.size() != 0;
//     }
    
//     private void dfs(String word, int idx,
//                     Pair prev,
//                     Map<Character, List<Pair>> map,
//                     Set<Pair> visited,
//                     StringBuffer sb,
//                     List<String> res) {
//         if (idx == word.length()) {
//             res.add(sb.toString());
//             return;
//         }               
        
//         char c = word.charAt(idx);
//         if (!map.containsKey(c)) {
//             return;
//         }
//         for (Pair p : map.get(c)) {
//             if ((prev == null) || (!visited.contains(p) && prev != null && isNeighbor(p, prev))) {
//                 sb.append(c);
//                 visited.add(p);
//                 dfs(word, idx + 1, p, map, visited, sb, res);
//                 visited.remove(p);
//                 sb.deleteCharAt(sb.length() - 1);
//             }
//         }
//     }
    
//     private boolean isNeighbor(Pair a, Pair b) {
//         return (a.x == b.x && Math.abs(a.y - b.y) == 1) || 
//               (a.y == b.y && Math.abs(a.x - b.x) == 1);
//     }
// }

// // DFS
// public class Solution {
//     /**
//      * @param board: A list of lists of character
//      * @param word: A string
//      * @return: A boolean
//      */
//     public boolean exist(char[][] board, String word) {
//         if (board == null || board.length == 0 ||
//             board[0] == null || board[0].length == 0 ||
//             word == null || word.length() == 0) {
//             return false;
//         }
        
//         int m = board.length;
//         int n = board[0].length;
//         boolean[][] visited = new boolean[m][n];
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (dfs(i, j, 0, word, board, visited)) {
//                     return true;
//                 }
//             }
//         }

//         return false;
//     }
    
//     private boolean dfs(int i, int j, int idx, String word,
//                      char[][] board, boolean[][] visited) {
//         if (idx == word.length()) {
//             return true;
//         }               
        
//         if (!inBound(i, j, board)) {
//             return false;
//         }
        
//         char c = word.charAt(idx);
        
//         if (board[i][j] == c && !visited[i][j]) {
//             visited[i][j] = true;
//             boolean res =  dfs(i, j + 1, idx + 1, word, board, visited) ||
//             dfs(i, j - 1, idx + 1, word, board, visited) ||
//             dfs(i + 1, j, idx + 1, word, board, visited) ||
//             dfs(i - 1, j, idx + 1, word, board, visited);
//             visited[i][j] = false;
//             return res;
//         }
        
//         return false;
//     }
    
//     private boolean inBound(int i, int j, char[][] board) {
//         return i >= 0 && j >= 0 && i < board.length && j < board[0].length;
//     }
// }

// DFS  without using visited matrix
public class Solution {
    /**
     * @param board: A list of lists of character
     * @param word: A string
     * @return: A boolean
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 ||
            board[0] == null || board[0].length == 0 ||
            word == null || word.length() == 0) {
            return false;
        }
        
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, 0, word, board)) {
                    return true;
                }
            }
        }

        return false;
    }
    
    private boolean dfs(int i, int j, int idx, String word,
                     char[][] board) {
        if (idx == word.length()) {
            return true;
        }               
        
        if (!inBound(i, j, board)) {
            return false;
        }
        
        char c = word.charAt(idx);
        
        if (board[i][j] == c) {
            board[i][j] = '*';
            boolean res =  dfs(i, j + 1, idx + 1, word, board) ||
            dfs(i, j - 1, idx + 1, word, board) ||
            dfs(i + 1, j, idx + 1, word, board) ||
            dfs(i - 1, j, idx + 1, word, board);
            board[i][j] = c;
            return res;
        }
        
        return false;
    }
    
    private boolean inBound(int i, int j, char[][] board) {
        return i >= 0 && j >= 0 && i < board.length && j < board[0].length;
    }
}