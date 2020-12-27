class NumMatrix {
    private STNode root;
    private int[][] matrix;
    private int[] colSums;
    private int m;
    private int n;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        this.matrix = matrix;
        this.m = matrix.length;
        this.n = matrix[0].length;
        this.colSums = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                colSums[i] += matrix[j][i];
            }
        }
        this.root = buildTree(colSums, 0, n - 1);
    }

    private STNode buildTree(int[] nums, int start, int end) {
        if (start == end) {
            return new STNode(start, end, nums[start], null, null);
        }

        int mid = start + (end - start) / 2;

        STNode left = buildTree(nums, start, mid);
        STNode right = buildTree(nums, mid + 1, end);
        return new STNode(start, end, left.sum + right.sum, left, right);
    }
    
    public void update(int row, int col, int val) {
        colSums[col] += val - matrix[row][col];
        updateHelper(root, col, colSums[col]);
        matrix[row][col] = val;
    }

    private void updateHelper(STNode root, int i, int val) {
        if (root.start == i && root.end == i) {
            root.sum = val;
            return;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (i <= mid) {
            updateHelper(root.left, i, val);
        } else {
            updateHelper(root.right, i, val);
        }

        root.sum = root.left.sum + root.right.sum;
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int colSum = queryHelper(root, col1, col2);
        for (int i = 0; i < row1; i++) {
            for (int j = col1; j <= col2; j++) {
                colSum -= matrix[i][j];
            }
        }
        for (int i = row2 + 1; i < m; i++) {
            for (int j = col1; j <= col2; j++) {
                colSum -= matrix[i][j];
            }
        }
        return colSum;
    }

    private int queryHelper(STNode root, int i, int j) {
        if (i > j) {
            return 0;
        }

        if (i == root.start && j == root.end) {
            return root.sum;
        }

        int mid = root.start + (root.end - root.start)  / 2;
        if (j <= mid) {
            return queryHelper(root.left, i, j);
        }
        if (i >= mid + 1) {
            return queryHelper(root.right, i, j);
        }

        return queryHelper(root.left, i, mid) + queryHelper(root.right, mid + 1, j);
    }

    class STNode {
        private int start;
        private int end;
        private int sum;
        private STNode left;
        private STNode right;

        public STNode(int start, int end, int sum, STNode left, STNode right) {
            this.start = start;
            this.end = end;
            this.sum = sum;
            this.left = left;
            this.right = right;
        }
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */