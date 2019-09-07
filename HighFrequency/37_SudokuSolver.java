// // DFS O(9^n)time O(n)space  initial version
// class Solution {
//     public void solveSudoku(char[][] board) {
//         if (board == null || board.length == 0 || 
//             board[0] == null || board[0].length == 0) {
//             return;
//         }
        
//         Set<Character>[] rows = new Set[9];
//         Set<Character>[] cols = new Set[9];
//         Set<Character>[] blocks = new Set[9];
//         for (int i = 0 ; i < 9; i++) {
//             rows[i] = new HashSet<>();
//             cols[i] = new HashSet<>();
//             blocks[i] = new HashSet<>();
//         }
        
//         for (int i = 0; i < 9; i++) {
//             for (int j = 0; j < 9; j++) {
//                 if (board[i][j] == '.') {
//                     continue;
//                 }
                
//                 rows[i].add(board[i][j]);
//                 cols[j].add(board[i][j]);
//                 blocks[i / 3 * 3 + j / 3].add(board[i][j]);
//             }
//         }
        
//         char[][] res = new char[9][9];
        
//         dfs(0, board, rows, cols, blocks, res);
        
//         for (int i = 0; i < 9; i++) {
//             for (int j = 0; j < 9; j++) {
//                 board[i][j] = res[i][j];
//             }
//         }
//     }
    
//     private void dfs(int idx, char[][] board, Set<Character>[] rows, Set<Character>[] cols, Set<Character>[] blocks, char[][] res) {
//         if (idx == 81) {
//             for (int i = 0; i < 9; i++) {
//                 for (int j = 0; j < 9; j++) {
//                     res[i][j] = board[i][j];
//                 }
//             }
//             return;
//         }
        
//         int row = idx / 9;
//         int col = idx % 9;
        
//         if (board[row][col] != '.') {
//             dfs(idx + 1, board, rows, cols, blocks, res);
//         } else {
//             for (char c = '1'; c <= '9'; c++) {
//                 if (isValid(c, idx, rows, cols, blocks)) {
//                     board[row][col] = c;
//                     rows[row].add(c);
//                     cols[col].add(c);
//                     blocks[getBlockIdx(idx)].add(c);
//                     dfs(idx + 1, board, rows, cols, blocks, res);
//                     board[row][col] = '.';
//                     rows[row].remove(c);
//                     cols[col].remove(c);
//                     blocks[getBlockIdx(idx)].remove(c);
//                 }
//             }
//         }
//     }
    
//     private int getBlockIdx(int i) {
//         int row = i / 9;
//         int col = i % 9;
//         return row / 3 * 3 + col / 3;
//     }
    
//     private boolean isValid(char c, int idx, Set<Character>[] rows, Set<Character>[] cols, Set<Character>[] blocks) {
//         int row = idx / 9;
//         int col = idx % 9;
//         return !rows[row].contains(c) && !cols[col].contains(c) && !blocks[getBlockIdx(idx)].contains(c);
//     }
// }


// // DFS O(9^n)time O(n)space  with index
// class Solution {
//     public void solveSudoku(char[][] board) {
//         if (board == null || board.length == 0 || 
//             board[0] == null || board[0].length == 0) {
//             return;
//         }
        
//         Set<Character>[] rows = new Set[9];
//         Set<Character>[] cols = new Set[9];
//         Set<Character>[] blocks = new Set[9];
//         for (int i = 0 ; i < 9; i++) {
//             rows[i] = new HashSet<>();
//             cols[i] = new HashSet<>();
//             blocks[i] = new HashSet<>();
//         }
        
//         for (int i = 0; i < 9; i++) {
//             for (int j = 0; j < 9; j++) {
//                 if (board[i][j] == '.') {
//                     continue;
//                 }
                
//                 rows[i].add(board[i][j]);
//                 cols[j].add(board[i][j]);
//                 blocks[i / 3 * 3 + j / 3].add(board[i][j]);
//             }
//         }
        
//         dfs(0, board, rows, cols, blocks);
//     }
    
//     private boolean dfs(int idx, char[][] board, Set<Character>[] rows, Set<Character>[] cols, Set<Character>[] blocks) {
//         if (idx == 81) {
//             return true;
//         }
        
//         int row = idx / 9;
//         int col = idx % 9;
        
//         if (board[row][col] != '.') {
//             return dfs(idx + 1, board, rows, cols, blocks);
//         }
        
//         for (char c = '1'; c <= '9'; c++) {
//             if (isValid(c, idx, rows, cols, blocks)) {
//                 board[row][col] = c;
//                 rows[row].add(c);
//                 cols[col].add(c);
//                 blocks[row / 3 * 3 + col / 3].add(c);
//                 if (dfs(idx + 1, board, rows, cols, blocks)) {
//                     return true;
//                 }
//                 board[row][col] = '.';
//                 rows[row].remove(c);
//                 cols[col].remove(c);
//                 blocks[row / 3 * 3 + col / 3].remove(c);
//             }
//         }
        
//         return false;
//     }
    
//     private boolean isValid(char c, int idx, Set<Character>[] rows, Set<Character>[] cols, Set<Character>[] blocks) {
//         int row = idx / 9;
//         int col = idx % 9;
//         return !rows[row].contains(c) && !cols[col].contains(c) && !blocks[row / 3 * 3 + col / 3].contains(c);
//     }
// }


// DFS O(9^n)time O(n)space  with index, without hash
class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0 || 
            board[0] == null || board[0].length == 0) {
            return;
        }
        
        dfs(0, board);
    }
    
    private boolean dfs(int idx, char[][] board) {
        if (idx == 81) {
            return true;
        }
        
        int row = idx / 9;
        int col = idx % 9;
        
        if (board[row][col] != '.') {
            return dfs(idx + 1, board);
        }
        
        for (char c = '1'; c <= '9'; c++) {
            if (isValid(c, idx, board)) {
                board[row][col] = c;
                if (dfs(idx + 1, board)) {
                    return true;
                }
                board[row][col] = '.';
            }
        }

        return false;
    }
    
    private boolean isValid(char c, int idx, char[][] board) {
        int row = idx / 9;
        int col = idx % 9;
        
        for (int i = 0 ; i < 9; i++) {
            if (board[row][i] == c) {
                return false;
            }
            
            if (board[i][col] == c) {
                return false;
            }
            
            if (board[row / 3 * 3 + i / 3][col / 3 * 3 + i % 3] == c) {
                return false;
            }
        }
        
        return true;
    }
}


// // DFS O(9^n)time O(n)space  hashmap version
// class Solution {
//     public void solveSudoku(char[][] board) {
//         if (board == null || board.length == 0 || 
//             board[0] == null || board[0].length == 0) {
//             return;
//         }
        
//         Set<Character>[] rows = new Set[9];
//         Set<Character>[] cols = new Set[9];
//         Set<Character>[] blocks = new Set[9];
//         for (int i = 0 ; i < 9; i++) {
//             rows[i] = new HashSet<>();
//             cols[i] = new HashSet<>();
//             blocks[i] = new HashSet<>();
//         }
        
//         for (int i = 0; i < 9; i++) {
//             for (int j = 0; j < 9; j++) {
//                 if (board[i][j] == '.') {
//                     continue;
//                 }
                
//                 rows[i].add(board[i][j]);
//                 cols[j].add(board[i][j]);
//                 blocks[i / 3 * 3 + j / 3].add(board[i][j]);
//             }
//         }
        
//         dfs(board, rows, cols, blocks);
//     }
    
//     private boolean dfs(char[][] board, Set<Character>[] rows, Set<Character>[] cols, Set<Character>[] blocks) {
//         for (int i = 0; i < 9; i++) {
//             for (int j = 0; j < 9; j++) {
//                 if (board[i][j] == '.') {
//                     for (char c = '1'; c <= '9'; c++) {
//                         if (isValid(c, i, j, rows, cols, blocks)) {
//                             board[i][j] = c;
//                             rows[i].add(c);
//                             cols[j].add(c);
//                             blocks[i / 3 * 3 + j / 3].add(c);

//                             if (dfs(board, rows, cols, blocks)) {
//                                 return true;
//                             }

//                             board[i][j] = '.';
//                             rows[i].remove(c);
//                             cols[j].remove(c);
//                             blocks[i / 3 * 3 + j / 3].remove(c);
//                         }
//                     }
                
//                     return false;
//                 }
//             }
//         }
        
//         return true;
//     }
    
//     private boolean isValid(char c, int row, int col, Set<Character>[] rows, Set<Character>[] cols, Set<Character>[] blocks) {
//         return !rows[row].contains(c) && !cols[col].contains(c) && !blocks[row / 3 * 3 + col / 3].contains(c);
//     }
// }