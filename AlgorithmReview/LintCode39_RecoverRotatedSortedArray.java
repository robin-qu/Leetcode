// public class Solution {
//     /**
//      * @param nums: An integer array
//      * @return: nothing
//      */
//     public void recoverRotatedSortedArray(List<Integer> nums) {
//         if (nums == null || nums.size() <= 1) {
//             return;
//         }
        
//         int count = nums.size();
//         while (count > 0 && nums.get(0) <= nums.get(1)) {
//             nums.add(nums.remove(0));
//             count--;
//         }
        
//         if (nums.get(0) > nums.get(1)) {
//             nums.add(nums.remove(0));
//         }
//     }
// }

public class Solution {
    /**
     * @param nums: An integer array
     * @return: nothing
     */
    public void recoverRotatedSortedArray(List<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return;
        }
        
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i - 1) > nums.get(i)) {
                reverse(nums, 0, i - 1);
                reverse(nums, i, nums.size() - 1);
                reverse(nums, 0, nums.size() - 1);
                return;
            }
        }
    }
    
    private void reverse(List<Integer> nums, int start, int end) {
        while (start < end) {
            int temp = nums.get(start);
            nums.set(start, nums.get(end));
            nums.set(end, temp);
            start++;
            end--;
        }
    }
}