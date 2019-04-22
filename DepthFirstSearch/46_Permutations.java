// class Solution {
//     public List<List<Integer>> permute(int[] nums) {
//         List<List<Integer>> res = new ArrayList<>();
        
//         if (nums == null) {
//             return res;
//         }
        
//         if (nums.length == 0) {
//             res.add(new ArrayList<>());
//         }
        
//         List<Integer> permutation = new ArrayList<>();
        
//         helper(nums, permutation, res);
        
//         return res;
//     }
    
//     private void helper(int[] nums, 
//                         List<Integer> permutation, 
//                         List<List<Integer>> res) {
//         if (permutation.size() == nums.length) {
//             res.add(new ArrayList<>(permutation));
//             return;
//         }
        
//         for (int i = 0; i < nums.length; i++) {
//             if (permutation.contains(nums[i])) {
//                 continue;
//             }
//             permutation.add(nums[i]);
//             helper(nums, permutation, res);
//             permutation.remove(permutation.size() - 1);
//         }
//     }
// }

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (nums == null) {
            return res;
        }
        
        if (nums.length == 0) {
            res.add(new ArrayList<>());
        }
        
        List<Integer> permutation = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        
        helper(nums, visited, permutation, res);
        
        return res;
    }
    
    private void helper(int[] nums, 
                        boolean[] visited,
                        List<Integer> permutation, 
                        List<List<Integer>> res) {
        if (permutation.size() == nums.length) {
            res.add(new ArrayList<>(permutation));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            permutation.add(nums[i]);
            visited[i] = true;
            helper(nums, visited, permutation, res);
            permutation.remove(permutation.size() - 1);
            visited[i] = false;
        }
    }
}