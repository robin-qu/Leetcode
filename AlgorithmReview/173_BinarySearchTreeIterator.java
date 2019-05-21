/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class BSTIterator {
    private Stack<TreeNode> stack;
    
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        TreeNode curr = root;
        
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
    }
    
    /** @return the next smallest number */
    public int next() {
        TreeNode res = stack.pop();
        
        TreeNode curr = res.right;
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
        
        return res.val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

// class BSTIterator {
//     private Queue<Integer> queue;
//     private int size;
    
//     public BSTIterator(TreeNode root) {
//         queue = new LinkedList<>();
//         Stack<TreeNode> stack = new Stack<>();
//         TreeNode curr = root;
        
//         while (curr != null || !stack.isEmpty()) {
//             while (curr != null) {
//                 stack.push(curr);
//                 curr = curr.left;
//             }
            
//             curr = stack.pop();
//             queue.offer(curr.val);
//             size++;
//             curr = curr.right;
//         }
//     }
    
//     /** @return the next smallest number */
//     public int next() {
//         size--;
//         return queue.poll();
//     }
    
//     /** @return whether we have a next smallest number */
//     public boolean hasNext() {
//         return size > 0;
//     }
// }

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */