class TicTacToe {
    private int[] rows;
    private int[] cols;
    private int diag1;
    private int diag2;
    private int n;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        this.rows = new int[n];
        this.cols = new int[n];
        this.diag1 = 0;
        this.diag2 = 0;
        this.n = n;
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
        int move = player == 1 ? 1 : -1;
        rows[row] += move;
        cols[col] += move;
        if (row == col) {
            diag1 += move;
        }
        if (row + col == n - 1) {
            diag2 += move;
        }
        if (rows[row] == n || cols[col] == n || diag1 == n || diag2 == n) {
            return 1;
        }
        if (rows[row] == -n || cols[col] == -n || diag1 == -n || diag2 == -n) {
            return 2;
        }
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */