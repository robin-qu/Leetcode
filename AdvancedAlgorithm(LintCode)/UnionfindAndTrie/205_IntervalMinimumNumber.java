/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    /**
     * @param A: An integer array
     * @param queries: An query list
     * @return: The result list
     */
    
    class SegmentTreeNode {
        public int min;
        public int start;
        public int end;
        public SegmentTreeNode left;
        public SegmentTreeNode right;
        
        public SegmentTreeNode(int min, int start, int end) {
            this.min = min;
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
        }
    }
    
    private SegmentTreeNode buildSegmentTree(int[] A, int start, int end) {
        if (start == end) {
            return new SegmentTreeNode(A[start], start, end);
        }
        
        int mid = start + (end - start) / 2;
        
        SegmentTreeNode root = new SegmentTreeNode(0, start, end);
        root.left = buildSegmentTree(A, start, mid);
        root.right = buildSegmentTree(A, mid + 1, end);
        root.min = Math.min(root.left.min, root.right.min);
        
        return root;
    }
    
    private int query(SegmentTreeNode root, int start, int end) {
        if (start == root.start && end == root.end) {
            return root.min;
        }
        
        if (start > root.end || end < root.start) {
            return Integer.MAX_VALUE;
        }
        
        int mid = root.start + (root.end - root.start) / 2;
        int left = query(root.left, start, Math.min(mid, end));
        int right = query(root.right, Math.max(mid + 1, start), end);
        
        return Math.min(left, right);
    }
    
    public List<Integer> intervalMinNumber(int[] A, List<Interval> queries) {
        if (A == null || A.length == 0) {
            return new ArrayList<>();
        }
        
        List<Integer> res = new ArrayList<>();
        SegmentTreeNode root = buildSegmentTree(A, 0, A.length - 1);
        for (Interval i : queries) {
            res.add(query(root, i.start, i.end));
        }
        
        return res;
    }
}