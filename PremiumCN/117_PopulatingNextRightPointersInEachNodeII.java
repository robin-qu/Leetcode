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

        Node first = root;
        while (first != null) {
            Node curr = first;
            Node prev = null;
            Node nextFirst = null;
            while (curr != null) {
                if (curr.left != null) {
                    if (prev == null) {
                        nextFirst = curr.left;
                    } else {
                        prev.next = curr.left;
                    }
                    prev = curr.left;
                }
                if (curr.right != null) {
                    if (prev == null) {
                        nextFirst = curr.right;
                    } else {
                        prev.next = curr.right;
                    }
                    prev = curr.right;
                }
                curr = curr.next;
            }
            first = nextFirst;
        }

        return root;
    }
}