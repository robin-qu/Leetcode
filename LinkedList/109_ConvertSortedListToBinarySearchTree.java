// /**
//  * Definition for singly-linked list.
//  * public class ListNode {
//  *     int val;
//  *     ListNode next;
//  *     ListNode(int x) { val = x; }
//  * }
//  */
// /**
//  * Definition for a binary tree node.
//  * public class TreeNode {
//  *     int val;
//  *     TreeNode left;
//  *     TreeNode right;
//  *     TreeNode(int x) { val = x; }
//  * }
//  */
// class Solution {
//     public TreeNode sortedListToBST(ListNode head) {
//         if (head == null) {
//             return null;
//         }
        
//         if (head.next == null) {
//             return new TreeNode(head.val);
//         }
        
//         ListNode mid = getMid(head);
//         ListNode left = head;
//         ListNode right = mid.next;
//         mid.next = null;
        
//         // Exclude the mid node in the left list
//         ListNode leftCurr = left;
//         if (leftCurr == mid) {
//             left = null;
//         }
//         while (leftCurr.next != null && leftCurr.next != mid) {
//             leftCurr = leftCurr.next;
//         }
//         leftCurr.next = null;
        
//         TreeNode root = new TreeNode(mid.val);
//         root.left = sortedListToBST(left);
//         root.right = sortedListToBST(right);
        
//         return root;
//     }
    
//     private ListNode getMid(ListNode head) {
//         ListNode fast = head.next;
//         ListNode slow = head;
        
//         while (fast != null && fast.next != null) {
//             fast = fast.next.next;
//             slow = slow.next;
//         }
        
//         return slow;
//     }
// }

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
        
        // Cut the list in two halves from middle
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }        
        
        ListNode mid = slow;
        ListNode left = head;
        ListNode right = mid.next;
        mid.next = null;
        
        // Exclude the mid node in the left list
        if (prev == null) {
            left = null;
        } else {
            prev.next = null;
        }
        
        // Build tree
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(left);
        root.right = sortedListToBST(right);
        
        return root;
    }
}