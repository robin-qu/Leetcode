public class Solution {
    /*
     * @param nums: A list of integers
     * @return: nothing
     */
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int n = nums.length;
        // get the median via quickselecting(O(n))
        int median = quickSelect(0, n - 1, nums, n / 2);
        
        int left = 0;
        int right = n - 1;
        int curr = 0;
        // three way partitioning (similar to sort color)
        //  > median (start from first odd)   == median     < median (start from last even)
        while (curr <= right) {
            if (nums[indexMap(curr, n)] > median) {
                swap(indexMap(curr, n), indexMap(left, n), nums);
                left++;
                curr++;
            } else if (nums[indexMap(curr, n)] < median) {
                swap(indexMap(curr, n), indexMap(right, n), nums);
                right--;
            } else {
                curr++;
            }
        }
    }
    
    private int quickSelect(int start, int end, int[] nums, int k) {
        if (start == end) {
            return nums[start];
        }
        
        int left = start;
        int right = end;
        int pivot = nums[start + (end - start) / 2];
        
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            
            if (left <= right) {
                swap(left, right, nums);
                left++;
                right--;
            }
        }
        
        if (k <= right) {
            return quickSelect(start, right, nums, k);
        } else if (k >= left) {
            return quickSelect(left, end, nums, k);
        } else {
            return nums[k];
        }
    }
    
    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    // map indices < n / 2 to old indices and indices > n / 2 to even indices
    private int indexMap(int i, int n) {
        return (1 + 2 * i) % (n | 1);
    }
}