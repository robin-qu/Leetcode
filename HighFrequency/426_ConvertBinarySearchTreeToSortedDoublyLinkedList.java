/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

// // O(n)time O(n)space  initial version
// class Solution {
//     public Node treeToDoublyList(Node root) {
//         if (root == null) {
//             return null;
//         }
        
//         Node left = treeToDoublyList(root.left);
//         Node right = treeToDoublyList(root.right);
        
//         Node head;
//         if (left == null && right == null) {
//             root.left = root;
//             root.right = root;
//             head = root;
//         } else if (left == null) {
//             root.right = right;
//             root.left = right.left;
//             right.left.right = root;
//             right.left = root;
//             head = root;
//         } else if (right == null) {
//             root.left = left.left;
//             root.right = left;
//             left.left.right = root;
//             left.left = root;
//             head = left;
//         } else {
//             root.right = right;
//             root.left = left.left;
//             right.left.right = left;
//             left.left.right = root;
//             left.left = right.left;
//             right.left = root;
//             head = left;
//         }
        
//         return head;
//     }
// }



// O(n)time O(n)space  initial version
class Solution {
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        
        Node left = treeToDoublyList(root.left);
        Node right = treeToDoublyList(root.right);
        
        // connect root into a circular linkedlist
        root.left = root;
        root.right = root;
        
        return connect(left, connect(root, right));
    }
    
    private Node connect(Node a, Node b) {
        if (a == null) {
            return b;
        }
        
        if (b == null) {
            return a;
        }
        
        a.left.right = b;
        b.left.right = a;
        Node temp = a.left;
        a.left = b.left;
        b.left = temp;
        
        return a;
    }
}