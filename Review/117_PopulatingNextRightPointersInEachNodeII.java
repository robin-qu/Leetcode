/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node dummy = new Node(0);
        Node curr = root;
        Node nextRowCurr = dummy;
        while (curr != null || dummy.next != null) {
            if (curr == null) {
                curr = dummy.next;
                dummy.next = null;
                nextRowCurr = dummy;
                continue;
            }
            if (curr.left != null) {
                nextRowCurr.next = curr.left;
                nextRowCurr = nextRowCurr.next;
            }

            if (curr.right != null) {
                nextRowCurr.next = curr.right;
                nextRowCurr = nextRowCurr.next;
            }

            curr = curr.next;
        }

        return root;
    }
}