// // Sort array
// class Solution {
//     public int findKthLargest(int[] nums, int k) {
//         Arrays.sort(nums);
//         return nums[nums.length - k];
//     }
// }

// // Min heap
// class Solution {
//     public int findKthLargest(int[] nums, int k) {
//         PriorityQueue<Integer> pq = new PriorityQueue<>();
//         for (int num : nums) {
//             pq.offer(num);
//             if (pq.size() > k) {
//                 pq.poll();
//             }
//         }
        
//         return pq.peek();
//     }
// }

// // Quick select recurrsive
// class Solution {
//     public int findKthLargest(int[] nums, int k) {
//         if (nums == null || nums.length == 0) {
//             return -1;
//         }
//         return partition(nums, 0, nums.length - 1, k - 1);
//     }
    
//     private int partition(int[] nums, int left, int right, int k) {
//         int pivot = nums[(left + right) / 2];
//         int l = left;
//         int r = right;
        
//         while (l <= r) {
//             while (nums[l] > pivot) {
//                 l++;
//             }
//             while (nums[r] < pivot) {
//                 r--;
//             }
//             if (l <= r) {
//                 swap(nums, l, r);
//                 l++;
//                 r--;
//             }
//         }
        
//         // k lies in the left part
//         if (k <= r && left < r) {
//             return partition(nums, left, r, k);
//         }
//         // k lies in the right part
//         if (k >= l && l < right) {
//             return partition(nums, l, right, k);
//         }
//         // k is found
//         return nums[k];
//     }
    
//     private void swap(int[] nums, int left, int right) {
//         int temp = nums[left];
//         nums[left] = nums[right];
//         nums[right] = temp;
//     }
// }

// Quick select iterative
class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int left = low;
            int right = high;
            int pivot = nums[low];
            
            while (left <= right) {
                while (left <= right && nums[left] >= pivot) {
                    left++;
                }
                while (left <= right && nums[right] < pivot) {
                    right--;
                }
                if (left <= right) {
                    swap(nums, left, right);
                    left++;
                    right--;
                }
            }
            swap(nums, low, right);
            
            if (right < k - 1) {
                low = right + 1;
            } else if (right > k - 1) {
                high = right - 1;
            } else {
                return nums[right];
            }
        }
        return -1;
        
    }
    
    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}