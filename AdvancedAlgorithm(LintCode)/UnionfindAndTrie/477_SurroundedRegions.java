// // BFS
// public class Solution {
//     /*
//      * @param board: board a 2D board containing 'X' and 'O'
//      * @return: nothing
//      */
    
//     class Pair {
//         public int x;
//         public int y;
        
//         public Pair(int x, int y) {
//             this.x = x;
//             this.y = y;
//         }
//     }
    
//     public void surroundedRegions(char[][] board) {
//         if (board == null || board.length == 0 || 
//             board[0] == null || board[0].length == 0) {
//                 return;
//         }
        
//         int m = board.length;
//         int n = board[0].length;
//         boolean[][] visited = new boolean[m][n];
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 visited[i][j] = false;
//             }
//         }
        
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (atEdge(i, j, board)) {
//                     if (board[i][j] == 'O') {
//                         bfs(i, j, board, visited);
//                     } else {
//                         visited[i][j] = true;
//                     }
//                 }
//             }
//         }
        
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (!visited[i][j] && board[i][j] == 'O') {
//                     board[i][j] = 'X';
//                 }
//             }
//         }
//     }
    
//     private void bfs(int i, int j, char[][] board, boolean[][] visited) {
//         int[] dX = new int[]{0, 1, -1, 0};
//         int[] dY = new int[]{1, 0, 0, -1};
        
//         Queue<Pair> queue = new LinkedList<>();
//         visited[i][j] = true;
//         queue.offer(new Pair(i, j));
        
//         while (!queue.isEmpty()) {
//             Pair curr = queue.poll();
//             int x = curr.x;
//             int y = curr.y;
            
//             for (int k = 0; k < 4; k++) {
//                 int newX = x + dX[k];
//                 int newY = y + dY[k];
//                 if (!inBound(newX, newY, board)) {
//                     continue;
//                 }
                
//                 if (!visited[newX][newY] && board[newX][newY] == 'O') {
//                     queue.offer(new Pair(newX, newY));
//                     visited[newX][newY] = true;
//                 }
//             }
//         }
        
//     }
    
//     private boolean atEdge(int i, int j, char[][] board) {
//         return i == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1;
//     }
    
//     private boolean inBound(int i, int j, char[][] board) {
//         return i >= 0 && j >= 0 && i < board.length && j < board[0].length;
//     }
// }


// // Union Find
class Solution {
    /*
     * @param board: board a 2D board containing 'X' and 'O'
     * @return: nothing
     */
     
    private int[] unionfind;
    
    public void surroundedRegions(char[][] board) {
        if (board == null || board.length == 0 || 
            board[0] == null || board[0].length == 0) {
            return;
        }
        
        int m = board.length;
        int n = board[0].length;
        this.unionfind = new int[m * n + 1];  // with an extra dummy position
        for (int i = 0; i <= m * n; i++) {
            unionfind[i] = i;
        }
        
        int[] dX = new int[]{0, 1, -1, 0};
        int[] dY = new int[]{1, 0, 0, -1};
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    if (atEdge(i, j, m, n)) {
                        connect(m * n, i * n + j);
                    } else {
                        for (int k = 0; k < 4; k++) {
                            int x = i + dX[k];
                            int y = j + dY[k];
                            if (inBound(x, y, m, n) && board[x][y] == 'O') {
                                connect(x * n + y, i * n + j);
                            }
                        }
                    }
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && find(m * n) != find(i * n + j)) {
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    private int find(int a) {
        int root = a;
        
        while (unionfind[root] != root) {
            root = unionfind[root];
        }
        
        int curr = a;
        
        while (curr != root) {
            int parent = unionfind[curr];
            unionfind[curr] = root;
            curr = parent;
        }
        
        return root;
    }
    
    private void connect(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            unionfind[rootB] = rootA;
        }
        
    }
    
    private boolean atEdge(int i, int j, int m, int n) {
        return i == 0 || j == 0 || i == m - 1 || j == n - 1;
    }
    
    private boolean inBound(int i, int j, int m, int n) {
        return i >= 0 && j >= 0 && i < m && j < n;
    }
}