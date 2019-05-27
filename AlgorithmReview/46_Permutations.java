// class Solution {
//     public List<List<Integer>> permute(int[] nums) {
//         List<List<Integer>> res = new ArrayList<>();
        
//         if (nums == null || nums.length == 0) {
//             return res;
//         }
        
//         List<Integer> perm = new ArrayList<>();
        
//         helper(nums, perm, res);
        
//         return res;
//     }
    
//     private void helper(int[] nums, List<Integer> perm, 
//                         List<List<Integer>> res) {
//         if (perm.size() == nums.length) {
//             res.add(new ArrayList<>(perm));
//             return;
//         }
        
//         for (int i = 0; i < nums.length; i++) {
//             if (perm.contains(nums[i])) {
//                 continue;
//             }
//             perm.add(nums[i]);
//             helper(nums, perm, res);
//             perm.remove(perm.size() - 1);
//         }    
//     }
// }

// class Solution {
//     public List<List<Integer>> permute(int[] nums) {
//         List<List<Integer>> res = new ArrayList<>();
        
//         if (nums == null || nums.length == 0) {
//             return res;
//         }
        
//         List<Integer> perm = new ArrayList<>();
//         boolean[] visited = new boolean[nums.length];
        
//         helper(nums, visited, perm, res);
        
//         return res;
//     }
    
//     private void helper(int[] nums, boolean[] visited, List<Integer> perm,
//                         List<List<Integer>> res) {
//         if (perm.size() == nums.length) {
//             res.add(new ArrayList<>(perm));
//             return;
//         }
        
//         for (int i = 0; i < nums.length; i++) {
//             if (perm.contains(nums[i])) {
//                 continue;
//             }
//             perm.add(nums[i]);
//             visited[i] = true;
//             helper(nums, visited, perm, res);
//             perm.remove(perm.size() - 1);
//             visited[i] = false;
//         }    
//     }
// }

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        List<Integer> perm = new ArrayList<>();
        perm.add(nums[0]);
        res.add(new ArrayList<>(perm));
        
        for (int i = 1; i < nums.length; i++) {
            int size = res.size();
            for (int j = 0; j < size; j++) {
                List<Integer> temp = res.remove(0);
                int len = temp.size();
                for (int k = 0; k <= len; k++) {
                    temp.add(k, nums[i]);
                    res.add(new ArrayList<>(temp));
                    temp.remove(k);
                }
            }
        }
        
        return res;
    }
}