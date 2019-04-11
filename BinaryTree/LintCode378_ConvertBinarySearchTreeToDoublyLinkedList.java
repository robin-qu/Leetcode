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

public class Solution {
    /**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    public DoublyListNode bstToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        DoublyListNode front = new DoublyListNode(0);
        DoublyListNode currListNode = front;
        DoublyListNode prevListNode = null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currTreeNode = root;
        TreeNode prevTreeNode = null;
        
        while (currTreeNode != null || !stack.isEmpty()) {
            while (currTreeNode != null) {
                stack.push(currTreeNode);
                currTreeNode = currTreeNode.left;
            }
            
            currTreeNode = stack.pop();
            DoublyListNode newListNode = new DoublyListNode(currTreeNode.val);
            currListNode.next = newListNode;
            newListNode.prev = currListNode;
            currListNode = newListNode;
            
            currTreeNode = currTreeNode.right;
        }
        
        currListNode.next = null;
        front = front.next;
        front.prev = null;
        
        return front;
    }
}