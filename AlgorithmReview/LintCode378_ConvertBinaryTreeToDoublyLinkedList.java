/**
 * Definition for Doubly-ListNode.
 * public class DoublyListNode {
 *     int val;
 *     DoublyListNode next, prev;
 *     DoublyListNode(int val) {
 *         this.val = val;
 *         this.next = this.prev = null;
 *     }
 * } * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

// public class Solution {
//     /**
//      * @param root: The root of tree
//      * @return: the head of doubly list node
//      */
//     public DoublyListNode bstToDoublyList(TreeNode root) {
//         if (root == null) {
//             return null;
//         }
        
//         Stack<TreeNode> stack = new Stack<>();
//         TreeNode curr = root;
        
//         DoublyListNode dummy = new DoublyListNode(-1);
//         DoublyListNode tail = dummy;
        
//         while (curr != null || !stack.isEmpty()) {
//             while (curr != null) {
//                 stack.push(curr);
//                 curr = curr.left;
//             }
            
//             curr = stack.pop();
//             tail.next = new DoublyListNode(curr.val);
//             tail.next.prev = tail;
//             tail = tail.next;
            
//             curr = curr.right;
//         }
        
//         dummy.next.prev = null;
//         return dummy.next;
//     }
// }


public class Solution {
    /**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
     
    class ResultType {
        public DoublyListNode head;
        public DoublyListNode tail;
        
        public ResultType(DoublyListNode head, DoublyListNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }
    
    public DoublyListNode bstToDoublyList(TreeNode root) {
        return helper(root).head;
    }
    
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(null, null);
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        DoublyListNode middle = new DoublyListNode(root.val);
        
        if (left.tail != null) {
            left.tail.next = middle;
            middle.prev = left.tail;
        }
        
        if (right.head != null) {
            middle.next = right.head;
            right.head.prev = middle;
        }
        
        DoublyListNode head = left.head == null ? middle : left.head;
        DoublyListNode tail = right.tail == null ? middle : right.tail;
        
        return new ResultType(head, tail);
    }
}