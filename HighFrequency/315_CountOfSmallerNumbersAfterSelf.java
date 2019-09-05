// // brute force O(n^2)time O(n)space
// class Solution {
//     public List<Integer> countSmaller(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return new ArrayList<>();
//         }
        
//         int n = nums.length;
//         int[] counts = new int[n];
        
//         for (int i = n - 1; i >= 0; i--) {
//             int curr = nums[i];
//             for (int j = i - 1; j >= 0; j--) {
//                 if (nums[j] > curr) {
//                     counts[j]++;
//                 }
//             }
//         }
        
//         List<Integer> res = new ArrayList<>();
//         for (int count : counts) {
//             res.add(count);
//         }
        
//         return res;
//     }
// }


// // merge sort O(nlogn)time O(n)space
// // the number of smaller numbers on the right of a number is the number of jumps from right to left when performing a stable sort
// class Solution {
//     private int[] counts;
    
//     public List<Integer> countSmaller(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return new ArrayList<>();
//         }
        
//         int n = nums.length;
//         counts = new int[n];
        
//         int[] indices = new int[n];
//         for (int i = 0; i < n; i++) {
//             indices[i] = i;
//         }
        
//         mergeSort(nums, indices, 0, n - 1);
        
//         List<Integer> res = new ArrayList<>();
//         for (int count : counts) {
//             res.add(count);
//         }
        
//         return res;
//     }
    
//     private void mergeSort(int[] nums, int[] indices, int start, int end) {
//         if (start >= end) {
//             return;
//         }
        
//         int mid = start + (end - start) / 2;
//         mergeSort(nums, indices, start, mid);
//         mergeSort(nums, indices, mid + 1, end);
        
//         merge(nums, indices, start, end);
//     }
    
//     private void merge(int[] nums, int[] indices, int start, int end) {
//         int mid = start + (end - start) / 2;
//         int left = start;
//         int right = mid + 1;
//         int countRight = 0;
//         int[] newIndices = new int[end - start + 1];  // a sorted array whose elements are the index of nums
//         int idx = 0;  // newIndices's idx pointer
        
//         while (left <= mid && right <= end) {
//             if (nums[indices[right]] < nums[indices[left]]) {  // right half is smaller
//                 newIndices[idx++] = indices[right++];
//                 countRight++;  // increment the number of jump
//             } else {  // left half is smaller
//                 counts[indices[left]] += countRight;
//                 newIndices[idx++] = indices[left++];
//             }
//         }
        
//         while (left <= mid) {
//             counts[indices[left]] += countRight;
//             newIndices[idx++] = indices[left++];
//         }
        
//         while (right <= end) {
//             newIndices[idx++] = indices[right++];
//         }
        
//         for (int i = start; i <= end; i++) {
//             indices[i] = newIndices[i - start];
//         }
//     }
// }


// BST O(nlogn)time O(n)space
class Solution {
    private class TreeNode {
        public int val;
        public int leftSum;
        public int dup;
        public TreeNode left;
        public TreeNode right;
        
        public TreeNode(int val) {
            this.val = val;
            this.leftSum = 0;
            this.dup = 1;
            this.left = null;
            this.right = null;
        }
    }
    
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        int n = nums.length;
        TreeNode root = null;
        List<Integer> res = new ArrayList<>();
        
        for (int i = n - 1; i >= 0; i--) {
            root = insert(nums[i], root, 0, res);
        }
        
        Collections.reverse(res);
        
        return res;
    }
    
    private TreeNode insert(int num, TreeNode root, int sum, List<Integer> res) {
        if (root == null) {
            res.add(sum);
            return new TreeNode(num);
        }
        
        if (root.val < num) {
            sum += root.leftSum + root.dup;
            root.right = insert(num, root.right, sum, res);
        } else if (num < root.val) {
            root.leftSum++;
            root.left = insert(num, root.left, sum, res);
        } else {
            root.dup++;
            sum += root.leftSum;
            res.add(sum);
        }
        
        return root;
    }
    
}