// class Solution {
//     public List<List<Integer>> permute(int[] nums) {
//         List<List<Integer>> res = new ArrayList<>();
        
//         if (nums == null) {
//             return res;
//         }
        
//         if (nums.length == 0) {
//             res.add(new ArrayList<>());
//             return res;
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
//         boolean[] visited = new boolean[nums.length];
        
//         helper(nums, visited, permutation, res);
        
//         return res;
//     }
    
//     private void helper(int[] nums, 
//                         boolean[] visited,
//                         List<Integer> permutation, 
//                         List<List<Integer>> res) {
//         if (permutation.size() == nums.length) {
//             res.add(new ArrayList<>(permutation));
//             return;
//         }
        
//         for (int i = 0; i < nums.length; i++) {
//             if (visited[i]) {
//                 continue;
//             }
//             permutation.add(nums[i]);
//             visited[i] = true;
//             helper(nums, visited, permutation, res);
//             permutation.remove(permutation.size() - 1);
//             visited[i] = false;
//         }
//     }
// }


// Iterative
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (nums == null) {
            return res;
        }
        
        if (nums.length == 0) {
            res.add(new ArrayList<>());
        }
        
        Arrays.sort(nums);
        List<Integer> permutation = arrayToList(nums);
        
        while (!res.contains(permutation)) {
            res.add(permutation);
            nextPermutation(nums);
            permutation = arrayToList(nums);
        }
        
        return res;
    }
    
    private void nextPermutation(int[] nums) {
        List<Integer> nextPermutation = new ArrayList<>();
        
        if (nums == null || nums.length == 0) {
            return;
        }
        
        // Finds the last ascending position
        int idx = nums.length - 1;
        while (idx > 0 && nums[idx - 1] >= nums[idx]) {
            idx--;
        }
        
        if (idx == 0) {
            Arrays.sort(nums);
            return;
        }
        
        // Finds the first bigger value
        int i = nums.length - 1;
        while (i >= idx && nums[idx - 1] >= nums[i]) {
            i--;
        }
        
        int temp = nums[i];
        nums[i] =  nums[idx - 1];
        nums[idx - 1] = temp;
        Arrays.sort(nums, idx, nums.length);;
    }
    
    private List<Integer> arrayToList(int[] nums) {
        List<Integer> list = new ArrayList<>();
        
        for (int num : nums) {
            list.add(num);
        }
        
        return list;
    }
}