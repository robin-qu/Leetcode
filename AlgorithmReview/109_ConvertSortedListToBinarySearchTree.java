/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        
        // mid
        ListNode mid = getMid(head);
        // left
        ListNode left = head;
        if (mid == left) {
            left = null;
        } else {
            ListNode leftCurr = left;
            while (leftCurr.next != mid) {
                leftCurr = leftCurr.next;
            }
            leftCurr.next = null;
        }
        
        // right
        ListNode right = mid.next;
        mid.next = null;
        
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(left);
        root.right = sortedListToBST(right);
        
        return root;
    }
    
    private ListNode getMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        return slow;
    }
}