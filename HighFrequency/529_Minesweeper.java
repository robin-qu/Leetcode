// BFS  O(mn)time O(mn)space
class Solution {
    class Pair {
        public int x;
        public int y;
        
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private static final int[] dx = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
    private static final int[] dy = new int[]{1, 1, 1, 0, 0, -1, -1, -1};
    
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0 ||
            board[0] == null || board[0].length == 0) {
            return new char[0][0];
        }
        
        int m = board.length;
        int n = board[0].length;
        int x = click[0];
        int y = click[1];
        
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
        } else if (exploreAdj(board, x, y) != 0) {
            board[x][y] = (char) ('0' + exploreAdj(board, x, y));
        } else {
            board[x][y] = 'B';
            bfs(board, new Pair(x, y), m, n);
        }
        
        return board;
    }
    
    private void bfs(char[][] board, Pair p, int m, int n) {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(p);
        boolean[][] visited = new boolean[m][n];
        visited[p.x][p.y] = true;
        
        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            
            for (int i = 0; i < 8; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (inBound(board, nx, ny) && !visited[nx][ny]) {
                    int neighbor = exploreAdj(board, nx, ny);
                    if (neighbor == 0) {
                        board[nx][ny] = 'B';
                        queue.offer(new Pair(nx, ny));
                    } else {
                        board[nx][ny] = (char) ('0' + neighbor);
                    }
                    visited[nx][ny] = true;
                }
            }
                
        }
        
    }
    
    private int exploreAdj(char[][] board, int x, int y) {
        int res = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (inBound(board, nx, ny) && board[nx][ny] == 'M') {
                res++;
            }
        }
        
        return res;
    }
    
    private boolean inBound(char[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }
}