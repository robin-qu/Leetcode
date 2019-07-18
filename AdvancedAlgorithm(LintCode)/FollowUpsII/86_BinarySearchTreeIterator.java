/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 * Example of iterate a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * } 
 */
 
 public class BSTIterator {
    /*
    * @param root: The root of binary tree.
    */
    private Stack<TreeNode> stack;
    
    public BSTIterator(TreeNode root) {
        this.stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        TreeNode curr = stack.pop();
        TreeNode res = curr;
        
        if (curr.right != null) {
            curr = curr.right;
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
        }
        
        return res;
    }
}


// public class BSTIterator {
//     /*
//     * @param root: The root of binary tree.
//     */
//     private Stack<TreeNode> stack;
//     private TreeNode curr;
    
//     public BSTIterator(TreeNode root) {
//         this.stack = new Stack<>();
//         this.curr = root;
//     }

//     /*
//      * @return: True if there has next node, or false
//      */
//     public boolean hasNext() {
//         return !stack.isEmpty() || curr != null;
//     }

//     /*
//      * @return: return next node
//      */
//     public TreeNode next() {
//         while (curr != null) {
//             stack.push(curr);
//             curr = curr.left;
//         }
        
//         TreeNode res = stack.pop();
//         curr = res.right;
        
//         return res;
//     }
// }