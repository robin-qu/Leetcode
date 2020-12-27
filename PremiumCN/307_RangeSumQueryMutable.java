class NumArray {
    private STNode root;

    public NumArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        this.root = buildTree(nums, 0, nums.length - 1);
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
    
    public void update(int i, int val) {
        updateHelper(root, i, val);
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
    
    public int sumRange(int i, int j) {
        return queryHelper(root, i, j);
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
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */