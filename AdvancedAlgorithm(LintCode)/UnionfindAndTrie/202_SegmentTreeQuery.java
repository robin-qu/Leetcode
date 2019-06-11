/**
 * Definition of SegmentTreeNode:
 * public class SegmentTreeNode {
 *     public int start, end, max;
 *     public SegmentTreeNode left, right;
 *     public SegmentTreeNode(int start, int end, int max) {
 *         this.start = start;
 *         this.end = end;
 *         this.max = max
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: The root of segment tree.
     * @param start: start value.
     * @param end: end value.
     * @return: The maximum number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        // special cases
        if (start > end) {
            return Integer.MIN_VALUE;
        }
        
        // same interval
        if (root.start == start && root.end == end) {
            return root.max;
        }
        
        // no overlapping
        if (start > root.end || end < root.start) {
            return Integer.MIN_VALUE;
        }
        
        int mid = root.start + (root.end - root.start) / 2;
        
        int left = query(root.left, start, Math.min(end, mid));
        int right = query(root.right, Math.max(mid + 1, start), end);
        
        return Math.max(left, right);
    }
}