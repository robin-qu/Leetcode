// // O(n)time O(n)space
// class Solution {
//     public int firstMissingPositive(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 1;
//         }
        
//         int n = nums.length;
        
//         Set<Integer> set = new HashSet<>();
        
//         for (int num : nums) {
//             if (num > 0) {
//                 set.add(num);
//             }
//         }
        
//         int res = 1;
//         while (set.contains(res)) {
//             res++;
//         }
        
//         return res;
//     }
// }


// O(n)time O(n)space
class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        
        int n = nums.length;
        
        // swap element nums[i] to index of nums[i] - 1
        for(int i = 0; i < n; ++ i) {
            while(nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                swap(i, nums[i] - 1, nums);
            }
        }    
        
        for(int i = 0; i < n; ++ i) {
            if(nums[i] != i + 1) {
                return i + 1;
            }
        }
            
        return n + 1;
    }
    
    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}