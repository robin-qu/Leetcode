// // O(n*n!)time O(n)space
// class Solution {
//     public List<List<Integer>> permute(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return new ArrayList<>();
//         }
        
//         List<List<Integer>> res = new ArrayList<>();
//         dfs(nums, 0, new ArrayList<>(), res);
//         return res;
//     }
    
//     private void dfs(int[] nums, int idx, List<Integer> perm, List<List<Integer>> res) {
//         if (idx == nums.length) {
//             res.add(new ArrayList<>(perm));
//             return;
//         }
        
//         for (int i = 0; i < nums.length; i++) {
//             if (perm.contains(nums[i])) {
//                 continue;
//             }
//             perm.add(nums[i]);
//             dfs(nums, idx + 1, perm, res);
//             perm.remove(perm.size() - 1);
//         }
//     }
// }


// O(n*n!)time O(n)space
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, res);
        return res;
    }
    
    private void dfs(int[] nums, int idx, List<List<Integer>> res) {
        if (idx == nums.length) {
            List<Integer> perm = new ArrayList<>();
            for (int num : nums) {
                perm.add(num);
            }
            res.add(new ArrayList<>(perm));
            return;
        }
        
        for (int i = idx; i < nums.length; i++) {
            swap(nums, idx, i);
            dfs(nums, idx + 1, res);
            swap(nums, idx, i);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}