class Vector2D {
    private int[][] v;
    private int m;
    private int n;
    private int row;
    private int col;

    public Vector2D(int[][] v) {
        this.v = v;
        if (v == null || v.length == 0 || v[0] == null) {
            return;
        }
        this.m = v.length;
        this.n = v[0].length;
        this.row = 0;
        this.col = 0;
    }
    
    public int next() {
        hasNext();
        int res = v[row][col];
        col++;
        return res;
    }
    
    public boolean hasNext() {
        while (row < v.length && col >= v[row].length) {
            row++;
            col = 0;
        }
        return row < v.length;
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D obj = new Vector2D(v);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */