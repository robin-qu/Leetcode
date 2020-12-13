class Solution {
    private static final int DEAD = 0;
    private static final int LIVE = 1;
    private static final int D2L = 2;
    private static final int L2D = 3;
    private static final int[] dx = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
    private static final int[] dy = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
    private int m;
    private int n;

    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }

        m = board.length;
        n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = getNeighbor(i, j, board);
                if (board[i][j] == LIVE) {
                    if (count < 2 || count > 3) {
                        board[i][j] = L2D;
                    }
                } else {
                    if (count == 3) {
                        board[i][j] = D2L;
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == L2D) {
                    board[i][j] = DEAD;
                }
                if (board[i][j] == D2L) {
                    board[i][j] = LIVE;
                }
            }
        }
    }

    private int getNeighbor(int x, int y, int[][] board) {
        int res = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                continue;
            }
            if (board[nx][ny] == LIVE || board[nx][ny] == L2D) {
                res++;
            }
        }
        return res;
    }
}