// // O(n)time O(n^2)space
// class TicTacToe {
//     private int n;
//     private int[][] grid;
//     private int status;  // 0: no one wins, 1: 1 wins, 2: 2 wins

//     /** Initialize your data structure here. */
//     public TicTacToe(int n) {
//         this.n = n;
//         this.grid = new int[n][n];
//     }
    
//     /** Player {player} makes a move at ({row}, {col}).
//         @param row The row of the board.
//         @param col The column of the board.
//         @param player The player, can be either 1 or 2.
//         @return The current winning condition, can be either:
//                 0: No one wins.
//                 1: Player 1 wins.
//                 2: Player 2 wins. */
//     public int move(int row, int col, int player) {
//         if (status != 0) {
//             return status;
//         }
        
//         grid[row][col] = player;
        
//         // horizontal
//         int horizontal = 1;
//         int left = col - 1;
//         while (left >= 0 && grid[row][left] == player) {
//             horizontal++;
//             left--;
//             if (horizontal == n) {
//                 return player;
//             }
//         }
//         int right = col + 1;
//         while (right < n && grid[row][right] == player) {
//             horizontal++;
//             right++;
//             if (horizontal == n) {
//                 return player;
//             }
//         }
        
//         // vertical
//         int vertical = 1;
//         int up = row - 1;
//         while (up >= 0 && grid[up][col] == player) {
//             vertical++;
//             up--;
//             if (vertical == n) {
//                 return player;
//             }
//         }
//         int down = row + 1;
//         while (down < n && grid[down][col] == player) {
//             vertical++;
//             down++;
//             if (vertical == n) {
//                 return player;
//             }
//         }
        
//         // diagonal /
//         int diag1 = 1;
//         up = row - 1;
//         right = col + 1;
//         while (up >= 0 && right < n && grid[up][right] == player) {
//             diag1++;
//             up--;
//             right++;
//             if (diag1 == n) {
//                 return player;
//             }
//         }
//         down = row + 1;
//         left = col - 1;
//         while (down < n && left >= 0 && grid[down][left] == player) {
//             diag1++;
//             down++;
//             left--;
//             if (diag1 == n) {
//                 return player;
//             }
//         }
        
//         // diagonal \
//         int diag2 = 1;
//         up = row - 1;
//         left = col - 1;
//         while (up >= 0 && left >= 0 && grid[up][left] == player) {
//             diag2++;
//             up--;
//             left--;
//             if (diag2 == n) {
//                 return player;
//             }
//         }
//         down = row + 1;
//         right = col + 1;
//         while (down < n && right < n && grid[down][right] == player) {
//             diag2++;
//             down++;
//             right++;
//             if (diag2 == n) {
//                 return player;
//             }
//         }
        
//         return 0;
//     }
// }

// /**
//  * Your TicTacToe object will be instantiated and called as such:
//  * TicTacToe obj = new TicTacToe(n);
//  * int param_1 = obj.move(row,col,player);
//  */


// // O(1)time O(n)space
// class TicTacToe {
//     private int n;
//     private int[] rows;
//     private int[] cols;
//     private int diag1 = 0;
//     private int diag2 = 0;

//     /** Initialize your data structure here. */
//     public TicTacToe(int n) {
//         this.n = n;
//         rows = new int[n];
//         cols = new int[n];
//     }
    
//     /** Player {player} makes a move at ({row}, {col}).
//         @param row The row of the board.
//         @param col The column of the board.
//         @param player The player, can be either 1 or 2.
//         @return The current winning condition, can be either:
//                 0: No one wins.
//                 1: Player 1 wins.
//                 2: Player 2 wins. */
//     public int move(int row, int col, int player) {
//         if (player == 1) {  // +1 for player1
//             if (rows[row] < 0) {
//                 rows[row] = n + 1;
//             } else {
//                 rows[row]++;
//             }
            
//             if (cols[col] < 0) {
//                 cols[col] = n + 1;
//             } else {
//                 cols[col]++;
//             }
            
//             if (row == col) {
//                 if (diag1 < 0) {
//                     diag1 = n + 1;
//                 } else {
//                     diag1++;
//                 }
//             }
            
//             if (row + col == n - 1) {
//                 if (diag2 < 0) {
//                     diag2 = n + 1;
//                 } else {
//                     diag2++;
//                 }
//             }
//         } else {  //  -1 for player 2
//             if (rows[row] > 0) {
//                 rows[row] = n + 1;
//             } else {
//                 rows[row]--;
//             }
            
//             if (cols[col] > 0) {
//                 cols[col] = n + 1;
//             } else {
//                 cols[col]--;
//             }
            
//             if (row == col) {
//                 if (diag1 > 0) {
//                     diag1 = n + 1;
//                 } else {
//                     diag1--;
//                 }
//             }
            
//             if (row + col == n - 1) {
//                 if (diag2 > 0) {
//                     diag2 = n + 1;
//                 } else {
//                     diag2--;
//                 }
//             }
//         }
        
            
//         if (Math.abs(rows[row]) == n || 
//             Math.abs(cols[col]) == n || 
//             Math.abs(diag1) == n || 
//             Math.abs(diag2) == n) {
//             return player;
//         }
        
//         return 0;
//     }
// }

// /**
//  * Your TicTacToe object will be instantiated and called as such:
//  * TicTacToe obj = new TicTacToe(n);
//  * int param_1 = obj.move(row,col,player);
//  */


// O(1)time O(n)space  final version
class TicTacToe {
    private int n;
    private int[] rows;
    private int[] cols;
    private int diag1 = 0;
    private int diag2 = 0;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        this.n = n;
        rows = new int[n];
        cols = new int[n];
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int delta = player == 1 ? 1 : -1; // +1 for player1,-1 for player2
        
        rows[row] += delta;
        cols[col] += delta;
        
        if (row == col) {
            diag1 += delta;
        }        
        
        if (row + col == n - 1) {
            diag2 += delta;
        }
            
        if (Math.abs(rows[row]) == n || 
            Math.abs(cols[col]) == n || 
            Math.abs(diag1) == n || 
            Math.abs(diag2) == n) {
            return player;
        }
        
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */