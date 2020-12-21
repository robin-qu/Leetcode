/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

class Solution {
    public Node inorderSuccessor(Node node) {
        if (node == null) {
            return null;
        }

        Node curr = node;
        if (curr.right != null) {
            curr = curr.right;
            while (curr.left != null) {
                curr = curr.left;
            }
            return curr;
        }

        while (curr.parent != null && curr != curr.parent.left) {
            curr = curr.parent;
        }

        return curr.parent;
    }
}