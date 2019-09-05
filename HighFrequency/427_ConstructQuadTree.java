/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
*/

// O(n^2)time O(logn)space
class Solution {
    public Node construct(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return null;
        }
        
        int n = grid.length;
        
        return helper(grid, 0, n - 1, 0, n - 1);
    }
    
    private Node helper(int[][] grid, int left, int right, int up, int down) {
        if (left == right && up == down) {
            return new Node(grid[up][left] == 1 ? true : false, true, null, null, null, null);
        }
        
        Node curr = new Node();
        int rowMid = left + (right - left) / 2;
        int colMid = up + (down - up) / 2;
        curr.topLeft = helper(grid, left, rowMid, up, colMid);
        curr.topRight = helper(grid, rowMid + 1, right, up, colMid);
        curr.bottomLeft = helper(grid, left, rowMid, colMid + 1, down);
        curr.bottomRight = helper(grid, rowMid + 1, right, colMid + 1, down);
        
        if (curr.topLeft.isLeaf && curr.topRight.isLeaf &&
            curr.bottomLeft.isLeaf && curr.bottomRight.isLeaf &&
            curr.topLeft.val == curr.topRight.val &&
            curr.bottomLeft.val == curr.bottomRight.val &&
            curr.topLeft.val == curr.bottomLeft.val) {
            curr = new Node(curr.topLeft.val, true, null, null, null, null);
        }
        return curr;
    }
}