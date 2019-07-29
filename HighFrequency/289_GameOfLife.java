// // O(mn)time O(mn)space
// class Solution {
//     public static final int[] dx = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
//     public static final int[] dy = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
    
//     public void gameOfLife(int[][] board) {
//         if (board == null || board.length == 0 ||
//             board[0] == null || board[0].length == 0) {
//             return;
//         }
        
//         int m = board.length;
//         int n = board[0].length;
//         int[][] newBoard = new int[m][n];
        
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 int neighbor = getNeighbor(i, j, board);
//                 if (board[i][j] == 0 && neighbor == 3) {
//                     newBoard[i][j] = 1;
//                 } else if (board[i][j] == 1 && neighbor >= 2 && neighbor <= 3) {
//                     newBoard[i][j] = 1;
//                 }
//             }
//         }
        
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 board[i][j] = newBoard[i][j];
//             }
//         }
//     }
    
//     private int getNeighbor(int x, int y, int[][] board) {
//         int count = 0;
//         for (int i = 0; i < 8; i++) {
//             if (inBound(x + dx[i], y + dy[i], board)) {
//                 count += board[x + dx[i]][y + dy[i]];
//             }
//         }
        
//         return count;
//     }
    
//     private boolean inBound(int x, int y, int[][] board) {
//         return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
//     }
// } 


// O(mn)time O(1)space
class Solution {
    public static final int[] dx = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
    public static final int[] dy = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
    
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 ||
            board[0] == null || board[0].length == 0) {
            return;
        }
        
        int m = board.length;
        int n = board[0].length;
        
        // board[i][j] == -1 means new value is 1 and old value is 0
        // board[i][j] == 2 means new value is 0 and old value is 1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int neighbor = getNeighbor(i, j, board);
                
                if (board[i][j] == 0) {
                    if (neighbor == 3) {
                        board[i][j] = -1;
                    }
                } else {  // board[i][j] == 1
                    if (neighbor < 2 || neighbor > 3) {
                        board[i][j] = 2;
                    }
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                }
                
                if (board[i][j] == -1) {
                    board[i][j] = 1;
                }
            }
        }
    }
    
    private int getNeighbor(int x, int y, int[][] board) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (inBound(nx, ny, board)) {
                if (board[nx][ny] == 1 || board[nx][ny] == 2) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    private boolean inBound(int x, int y, int[][] board) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }
} 