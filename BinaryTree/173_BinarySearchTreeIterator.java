/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// class BSTIterator {
//     private List<Integer> list;
//     private int size;

//     public BSTIterator(TreeNode root) {
//         List<Integer> traversal = new LinkedList<>();
        
//         if (root == null) {
//             this.list = traversal;
//         }
        
//         Stack<TreeNode> stack = new Stack<>();
//         TreeNode curr = root;
        
//         while (curr != null || !stack.isEmpty()) {
//             while (curr != null) {
//                 stack.push(curr);
//                 curr = curr.left;
//             }
            
//             curr = stack.pop();
//             traversal.add(curr.val);
//             curr = curr.right;
//         }
        
//         this.list = traversal;
//         this.size = traversal.size();
//     }
    
//     /** @return the next smallest number */
//     public int next() {
//         this.size--;
//         return this.list.remove(0);
//     }
    
//     /** @return whether we have a next smallest number */
//     public boolean hasNext() {
//         return size > 0;
//     }
// }


class BSTIterator {
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.stack = new Stack<>();
        
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
    
    /** @return the next smallest number */
    public int next() {
        TreeNode next = stack.pop();
        if (next.right != null) {
            TreeNode curr = next.right;
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
        }
        
        return next.val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */