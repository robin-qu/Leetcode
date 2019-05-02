public class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length == 0) {
            return;
        }
        
        partition(colors, 0, colors.length - 1, 1, k);
    }
    
    private void partition(int[] nums, int left, int right, int lc, int rc) {
        if (lc == rc) {
            return;
        }
        
        int l = left;
        int r = right;
        int mc = lc + (rc - lc) / 2;
        
        while (l < r) {
            while (l < r && nums[l] >= lc && nums[l] <= mc) {
                l++;
            }
            while (l < r && nums[r] > mc && nums[r] <= rc) {
                r--;
            }
            if (l < r) {
                swap(nums, l, r);
                l++;
                r--;
            }
        }  // post: l(r)  r l
        
        int mid;
        if (nums[r] >= lc && nums[r] <= mc) {
            mid = r + 1;
        } else {
            mid = r;
        }
        
        partition(nums, left, mid - 1, lc, mc);
        partition(nums, mid, right, mc + 1, rc);
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}